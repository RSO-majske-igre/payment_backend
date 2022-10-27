package team.marela.backend.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.marela.backend.core.mappers.DtoMapper;
import team.marela.backend.core.models.customer.CustomerDto;
import team.marela.backend.database.entities.customer.AddressEntity;
import team.marela.backend.database.entities.customer.CustomerEntity;
import team.marela.backend.database.repositories.customer.AddressRepository;
import team.marela.backend.database.repositories.customer.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    private final DtoMapper<CustomerEntity, CustomerDto> customerMapper = new DtoMapper<>(CustomerEntity.class, CustomerDto.class);

    public CustomerDto upsertCustomer(CustomerDto dto) {
        var customer = customerMapper.toEntity(dto);;
        return customerMapper.toDto(customerRepository.save(
                customer.withAddress(saveAddress(customer.getAddress()))
        ));
    }

    private AddressEntity saveAddress(AddressEntity address) {
        return addressRepository.save(address);
    }
}
