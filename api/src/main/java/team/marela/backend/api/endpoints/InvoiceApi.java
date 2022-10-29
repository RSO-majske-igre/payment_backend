package team.marela.backend.api.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import team.marela.backend.api.interfaces.InvoiceApiInterface;
import team.marela.backend.core.models.invoice.InvoiceDto;
import team.marela.backend.core.services.InvoiceService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InvoiceApi implements InvoiceApiInterface {

    private final InvoiceService invoiceService;

    @Override
    public InvoiceDto postInvoice(InvoiceDto invoice) {
        return invoiceService.upsertInvoice(invoice);
    }

    @Override
    public InvoiceDto getInvoiceById(UUID id) {
        return invoiceService.getInvoice(id);
    }

    @Override
    public Page<InvoiceDto> getInvoices(Integer page, Integer perPage, String sortBy) {
        return invoiceService.getInvoices(page, perPage, sortBy);
    }
}
