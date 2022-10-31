package si.rso.majskeigre.payment_server.database.repositories.customer;

import si.rso.majskeigre.payment_server.database.BaseRepository;
import si.rso.majskeigre.payment_server.database.entities.customer.CustomerEntity;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<CustomerEntity> {
    Optional<CustomerEntity> findByEmail(String email);
}
