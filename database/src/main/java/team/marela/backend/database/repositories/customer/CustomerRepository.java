package team.marela.backend.database.repositories.customer;

import team.marela.backend.database.BaseRepository;
import team.marela.backend.database.entities.customer.CustomerEntity;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<CustomerEntity> {
    Optional<CustomerEntity> findByEmail(String email);
}
