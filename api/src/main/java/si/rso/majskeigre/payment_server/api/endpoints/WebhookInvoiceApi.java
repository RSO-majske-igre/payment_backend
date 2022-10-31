package si.rso.majskeigre.payment_server.api.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import si.rso.majskeigre.payment_server.api.interfaces.WebhookInvoiceApiInterface;
import si.rso.majskeigre.payment_server.core.models.invoice.InvoiceDto;
import si.rso.majskeigre.payment_server.core.models.webhooks.WebhookDto;
import si.rso.majskeigre.payment_server.core.services.webhooks.WebhookInvoiceService;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class WebhookInvoiceApi implements WebhookInvoiceApiInterface {
    private final WebhookInvoiceService webhookInvoiceService;

    @Override
    public WebhookDto<InvoiceDto> postWebhook(WebhookDto<InvoiceDto> webhook) {
        return webhookInvoiceService.upsertWebhook(webhook);
    }

    @Override
    public Set<WebhookDto<InvoiceDto>> getWebhooksForUrl(String endpointUrl) {
        return webhookInvoiceService.getWebhooksForEndpointUrl(endpointUrl);
    }
}
