package team.marela.backend.database.repositories.customer;

import team.marela.backend.database.BaseRepository;
import team.marela.backend.database.entities.customer.AddressEntity;

import java.util.Optional;

public interface AddressRepository extends BaseRepository<AddressEntity> {

    Optional<AddressEntity> findByLine1AndLine2AndCityAndPostalCode(String line1, String line2, String city, String postalCode);
}
