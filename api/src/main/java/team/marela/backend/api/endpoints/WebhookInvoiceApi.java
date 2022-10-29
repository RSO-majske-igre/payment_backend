package team.marela.backend.api.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import team.marela.backend.api.interfaces.WebhookInvoiceApiInterface;
import team.marela.backend.core.models.invoice.InvoiceDto;
import team.marela.backend.core.models.webhooks.WebhookDto;
import team.marela.backend.core.services.webhooks.WebhookInvoiceService;

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
