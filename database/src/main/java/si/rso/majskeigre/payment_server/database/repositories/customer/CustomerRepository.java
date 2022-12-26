package si.rso.majskeigre.payment_server.database.repositories.customer;

import si.rso.majskeigre.payment_server.database.BaseRepository;
import si.rso.majskeigre.payment_server.database.entities.customer.CustomerEntity;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends BaseRepository<CustomerEntity> {

    Optional<CustomerEntity> findByParticipantId(UUID id);
    Optional<CustomerEntity> findByStripeCustomerId(String id);
}
