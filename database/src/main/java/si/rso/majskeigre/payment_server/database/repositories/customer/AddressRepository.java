package si.rso.majskeigre.payment_server.database.repositories.customer;

import si.rso.majskeigre.payment_server.database.BaseRepository;
import si.rso.majskeigre.payment_server.database.entities.customer.AddressEntity;

import java.util.Optional;

public interface AddressRepository extends BaseRepository<AddressEntity> {

    Optional<AddressEntity> findByLine1AndLine2AndCityAndPostalCode(String line1, String line2, String city, String postalCode);
}
