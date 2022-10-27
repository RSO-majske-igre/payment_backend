package team.marela.backend.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.marela.backend.core.models.invoice.InvoiceDto;
import team.marela.backend.database.entities.invoice.InvoiceEntity;
import team.marela.backend.database.entities.invoice.InvoiceStatusHistoryEntity;
import team.marela.backend.database.repositories.invoice.InvoiceRepository;
import team.marela.backend.database.repositories.invoice.InvoiceStatusHistoryRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerService customerService;
    private final InvoiceStatusHistoryRepository invoiceStatusHistoryRepository;


    public InvoiceDto getInvoice(UUID invoiceId) {
    }

    private InvoiceStatusHistoryEntity getLastInvoiceStatus(InvoiceEntity invoice) {
        var history = invoiceStatusHistoryRepository.findByInvoiceOrderByCreatedDesc(invoice);
        return history == null || history.size() == 0 ? null : history.get(0);
    }
}
