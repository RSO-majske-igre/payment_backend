package si.rso.majskeigre.payment_server.database.repositories.invoice;

import si.rso.majskeigre.payment_server.database.BaseRepository;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceEntity;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceStatusHistoryEntity;

import java.util.List;

public interface InvoiceStatusHistoryRepository extends BaseRepository<InvoiceStatusHistoryEntity> {
    List<InvoiceStatusHistoryEntity> findByInvoiceOrderByCreatedDesc(InvoiceEntity invoice);
}
