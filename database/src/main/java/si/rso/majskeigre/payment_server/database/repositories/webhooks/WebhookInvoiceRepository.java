package si.rso.majskeigre.payment_server.database.repositories.webhooks;

import si.rso.majskeigre.payment_server.database.BaseRepository;
import si.rso.majskeigre.payment_server.database.entities.webhooks.WebhookInvoiceEntity;

import java.util.Optional;

public interface WebhookInvoiceRepository extends BaseRepository<WebhookInvoiceEntity> {
    Optional<WebhookInvoiceEntity> findByEndpointUrl(String endpointUrl);
}
