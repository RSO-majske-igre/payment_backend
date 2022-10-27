package team.marela.backend.database.repositories.invoice;

import team.marela.backend.database.BaseRepository;
import team.marela.backend.database.entities.invoice.InvoiceEntity;
import team.marela.backend.database.entities.invoice.InvoiceStatusHistoryEntity;

import java.util.List;
import java.util.Set;

public interface InvoiceStatusHistoryRepository extends BaseRepository<InvoiceStatusHistoryEntity> {
    List<InvoiceStatusHistoryEntity> findByInvoiceOrderByCreatedDesc(InvoiceEntity invoice);
}
