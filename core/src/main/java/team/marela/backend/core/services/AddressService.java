package team.marela.backend.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.marela.backend.core.exceptions.DataNotFoundException;
import team.marela.backend.database.entities.customer.AddressEntity;
import team.marela.backend.database.repositories.customer.AddressRepository;

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
        if(address.getId() == null) {
            addressRepository.findByLine1AndLine2AndCityAndPostalCode(
                    address.getLine1(), address.getLine2(), address.getCity(), address.getPostalCode()
            ).ifPresent(addr -> address.setId(addr.getId()));
        }

        return addressRepository.save(address);
    }
}
