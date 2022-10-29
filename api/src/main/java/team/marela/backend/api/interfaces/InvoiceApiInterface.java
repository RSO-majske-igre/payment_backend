package team.marela.backend.api.interfaces;

import io.swagger.v3.oas.annotations.Operation;
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
    InvoiceDto postInvoice(@RequestBody @Valid InvoiceDto invoice);

    @GetMapping("/{id}")
    InvoiceDto getInvoiceById(@PathVariable UUID id);

    @GetMapping
    Page<InvoiceDto> getInvoices(
            @RequestParam(required = false, defaultValue = "0")Integer page,
            @RequestParam(required = false, defaultValue = "25") Integer perPage,
            @RequestParam(required = false, defaultValue = "id") String sortBy
    );
}
