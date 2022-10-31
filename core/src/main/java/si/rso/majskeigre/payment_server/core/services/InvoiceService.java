package si.rso.majskeigre.payment_server.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import si.rso.majskeigre.payment_server.core.exceptions.DataNotFoundException;
import si.rso.majskeigre.payment_server.core.mappers.DtoMapper;
import si.rso.majskeigre.payment_server.core.models.invoice.InvoiceDto;
import si.rso.majskeigre.payment_server.core.services.webhooks.WebhookTriggerService;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceEntity;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceLineEntity;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceStatusHistoryEntity;
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
    private final InvoiceLineRepository invoiceLineRepository;
    private final InvoiceStatusHistoryRepository invoiceStatusHistoryRepository;

    private final CustomerService customerService;
    private final WebhookTriggerService webhookTriggerService;

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

    //todo: invoice status
    public InvoiceDto upsertInvoice(InvoiceDto dto) {
        return invoiceMapper.toDto(upsertInvoice(invoiceMapper.toEntity(dto)));
    }

    public InvoiceEntity upsertInvoice(InvoiceEntity invoice) {
        invoice = invoiceRepository.save(
                invoice.setCustomer(
                        customerService.upsertCustomer(invoice.getCustomer())
                )
        ).setInvoiceLines(
                saveInvoiceLines(invoice)
        );

        webhookTriggerService.triggerInvoiceWebhook(invoiceMapper.toDto(invoice));
        return invoice;
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
