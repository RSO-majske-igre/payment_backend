package si.rso.majskeigre.payment_server.core.services;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import si.rso.majskeigre.payment_server.core.exceptions.DataNotFoundException;
import si.rso.majskeigre.payment_server.core.external.services.ParticipantsExternalServices;
import si.rso.majskeigre.payment_server.core.mappers.DtoMapper;
import si.rso.majskeigre.payment_server.core.models.invoice.InvoiceDto;
import si.rso.majskeigre.payment_server.core.services.stripe.CustomerStripeService;
import si.rso.majskeigre.payment_server.core.services.stripe.InvoiceStripeService;
import si.rso.majskeigre.payment_server.core.services.webhooks.WebhookTriggerService;
import si.rso.majskeigre.payment_server.database.entities.customer.CustomerEntity;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceEntity;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceLineEntity;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceStatusHistoryEntity;
import si.rso.majskeigre.payment_server.database.repositories.customer.CustomerRepository;
import si.rso.majskeigre.payment_server.database.repositories.invoice.InvoiceLineRepository;
import si.rso.majskeigre.payment_server.database.repositories.invoice.InvoiceRepository;
import si.rso.majskeigre.payment_server.database.repositories.invoice.InvoiceStatusHistoryRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final InvoiceLineRepository invoiceLineRepository;
    private final InvoiceStatusHistoryRepository invoiceStatusHistoryRepository;

    private final CustomerService customerService;
    private final WebhookTriggerService webhookTriggerService;
    private final InvoiceStripeService invoiceStripeService;
    private final CustomerStripeService customerStripeService;
    private final ParticipantsExternalServices participantsExternalServices;

    private final DtoMapper<InvoiceEntity, InvoiceDto> invoiceMapper = new DtoMapper<>(InvoiceEntity.class, InvoiceDto.class);

    public InvoiceDto getInvoiceDto(UUID id) {
        return invoiceMapper.toDto(getInvoice(id));
    }

    public InvoiceEntity getInvoice(UUID id) {
        return invoiceRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    public Page<InvoiceDto> getInvoices(Integer page, Integer perPage, String sortBy) {
        return invoiceRepository.findAll(PageRequest.of(page, perPage, Sort.by(sortBy))).map(invoiceMapper::toDto);
    }

    public InvoiceDto upsertInvoice(InvoiceDto dto) throws StripeException {
        var participant = participantsExternalServices.getParticipant(dto.getParticipantId());

        CustomerEntity customer = customerRepository.findByParticipantId(dto.getParticipantId()).orElseGet(CustomerEntity::new);
        if (customer.getId() == null || customer.getStripeCustomerId() == null) {
            customer = customerRepository.save(
                    customerStripeService.createStripeCustomer(
                            CustomerEntity.builder().participantId(participant.getId()).build(), participant
                    ));
        }

        var invoice = invoiceRepository.save(
                invoiceStripeService.createInvoice(invoiceMapper.toEntity(dto), customer)
                        .setCustomer(
                                customerRepository.save(
                                        customerService.upsertCustomer(customer)
                                )
                        )
        );

        invoice.setInvoiceLines(saveInvoiceLines(invoice));

        webhookTriggerService.triggerInvoiceWebhook(invoiceMapper.toDto(invoice));
        return invoiceMapper.toDto(invoice);
    }

    private Set<InvoiceLineEntity> saveInvoiceLines(InvoiceEntity invoice) {
        return new HashSet<>(
                invoiceLineRepository.saveAll(
                        invoice.getInvoiceLines().stream()
                                .map(e -> e.setInvoice(invoice))
                                .collect(Collectors.toSet())
                )
        );
    }

    private InvoiceStatusHistoryEntity getLastStatus(InvoiceEntity invoice) {
        var history = invoiceStatusHistoryRepository.findByInvoiceOrderByCreatedDesc(invoice);
        if (history == null || history.size() == 0) {
            throw new DataNotFoundException();
        } else {
            return history.get(0);
        }
    }
}
