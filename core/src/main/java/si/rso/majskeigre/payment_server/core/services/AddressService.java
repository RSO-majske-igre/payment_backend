package si.rso.majskeigre.payment_server.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import si.rso.majskeigre.payment_server.core.exceptions.DataNotFoundException;
import si.rso.majskeigre.payment_server.database.entities.customer.AddressEntity;
import si.rso.majskeigre.payment_server.database.repositories.customer.AddressRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressEntity getAddress(AddressEntity address) {
        return getAddress(address.getId());
    }

    public AddressEntity getAddress(UUID addressId) {
        return addressRepository.findById(addressId).orElseThrow(DataNotFoundException::new);
    }

    public AddressEntity upsert(AddressEntity address) {
        if (address.getId() == null) {
            addressRepository.findByLine1AndLine2AndCityAndPostalCode(
                    address.getLine1(), address.getLine2(), address.getCity(), address.getPostalCode()
            ).ifPresent(addr -> address.setId(addr.getId()));
        }

        return addressRepository.save(address);
    }
}