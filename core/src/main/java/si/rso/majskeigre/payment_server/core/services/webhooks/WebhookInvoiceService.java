package si.rso.majskeigre.payment_server.core.services.webhooks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import si.rso.majskeigre.payment_server.core.mappers.DtoMapper;
import si.rso.majskeigre.payment_server.core.models.invoice.InvoiceDto;
import si.rso.majskeigre.payment_server.core.models.webhooks.WebhookDto;
import si.rso.majskeigre.payment_server.core.services.InvoiceService;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceEntity;
import si.rso.majskeigre.payment_server.database.entities.webhooks.WebhookInvoiceEntity;
import si.rso.majskeigre.payment_server.database.repositories.webhooks.WebhookInvoiceRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebhookInvoiceService {

    private final WebhookInvoiceRepository webhookInvoiceRepository;
    private final InvoiceService invoiceService;

    private final DtoMapper<InvoiceEntity, InvoiceDto> invoiceMapper =
            new DtoMapper<>(InvoiceEntity.class, InvoiceDto.class);

    public WebhookDto<InvoiceDto> upsertWebhook(WebhookDto<InvoiceDto> webhook) {
        if (webhook.getId() == null) {
            webhookInvoiceRepository.findByEndpointUrl(
                    webhook.getEndpointUrl()
            ).ifPresent(w -> webhook.setId(w.getId()));
        }

        return mapEntityToDto(
                webhookInvoiceRepository.save(
                        WebhookInvoiceEntity.builder()
                                .id(webhook.getId())
                                .endpointUrl(webhook.getEndpointUrl())
                                .secretKey(webhook.getSecretKey())
                                .build()
                )
        );
    }

    public Set<WebhookDto<InvoiceDto>> getWebhooksForEndpointUrl(String url) {
        return webhookInvoiceRepository.findByEndpointUrl(url).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toSet());
    }

    private WebhookDto<InvoiceDto> mapEntityToDto(WebhookInvoiceEntity entitiy) {
        return new WebhookDto<InvoiceDto>()
                .setId(entitiy.getId())
                .setEndpointUrl(entitiy.getEndpointUrl())
                .setSecretKey(entitiy.getSecretKey());
    }
}
