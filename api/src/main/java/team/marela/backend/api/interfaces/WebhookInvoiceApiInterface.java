package team.marela.backend.api.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.marela.backend.core.models.invoice.InvoiceDto;
import team.marela.backend.core.models.webhooks.WebhookDto;

import java.util.Set;

@RequestMapping("/webhook/invoice")
public interface WebhookInvoiceApiInterface {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates webhook for invoice upsert operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Webhook created")
    })
    WebhookDto<InvoiceDto> postWebhook(
            @RequestBody
            @Parameter(description = "Webhook details - secret key and url endpoint")
            WebhookDto<InvoiceDto> webhook
    );

    @GetMapping
    @Operation(summary = "Returns all webhooks for given endpoint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Webhooks returned")
    })
    Set<WebhookDto<InvoiceDto>> getWebhooksForUrl(
            @RequestParam
            @Parameter(description = "Url for requests")
            String endpointUrl
    );
}
