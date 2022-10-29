package team.marela.backend.database.repositories.webhooks;

import team.marela.backend.database.BaseRepository;
import team.marela.backend.database.entities.webhooks.WebhookInvoiceEntity;

import java.util.Optional;

public interface WebhookInvoiceRepository extends BaseRepository<WebhookInvoiceEntity> {
    Optional<WebhookInvoiceEntity> findByEndpointUrl(String endpointUrl);
}
