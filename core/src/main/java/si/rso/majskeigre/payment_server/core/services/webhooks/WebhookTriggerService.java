package si.rso.majskeigre.payment_server.core.services.webhooks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import si.rso.majskeigre.payment_server.core.models.invoice.InvoiceDto;
import si.rso.majskeigre.payment_server.core.models.webhooks.WebhookDto;
import si.rso.majskeigre.payment_server.database.repositories.webhooks.WebhookInvoiceRepository;

@Service
@RequiredArgsConstructor
public class WebhookTriggerService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final WebhookInvoiceRepository webhookInvoiceRepository;

    public void triggerInvoiceWebhook(InvoiceDto invoice) {
        webhookInvoiceRepository.findAll().forEach(e ->
                restTemplate.postForEntity(e.getEndpointUrl(),
                        new WebhookDto<InvoiceDto>()
                                .setId(e.getId())
                                .setEndpointUrl(e.getEndpointUrl())
                                .setSecretKey(e.getSecretKey())
                                .setBody(invoice),
                        Boolean.class
                )
        );
    }
}
