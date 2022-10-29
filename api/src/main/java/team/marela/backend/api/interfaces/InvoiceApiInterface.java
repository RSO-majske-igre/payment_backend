package team.marela.backend.api.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.marela.backend.core.models.invoice.InvoiceDto;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/invoice")
public interface InvoiceApiInterface {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates new invoice or updates existing one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Invoice created", content = @Content)
    })
    InvoiceDto postInvoice(
            @RequestBody @Valid
            @Parameter(description = "InvoiceDto to be saved")
            InvoiceDto invoice
    );

    @GetMapping("/{id}")
    @Operation(summary = "Returns invoice with given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice found nad returned", content = @Content)
    })
    InvoiceDto getInvoiceById(
            @PathVariable
            @Parameter(description = "Id of invoice to be found")
            UUID id
    );

    @GetMapping
    @Operation(summary = "Returns invoices with pagination and sorting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned invoices with given pagination and sorting parameters", content = @Content)
    })
    Page<InvoiceDto> getInvoices(
            @RequestParam(required = false, defaultValue = "0")
            @Parameter(description = "Page of data, starts with 0")
            Integer page,
            @RequestParam(required = false, defaultValue = "25")
            @Parameter(description = "How many objects per response")
            Integer perPage,
            @RequestParam(required = false, defaultValue = "id")
            @Parameter(description = "Sorting by")
            String sortBy
    );
}
