package si.rso.majskeigre.payment_server.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import si.rso.majskeigre.payment_server.core.mappers.DtoMapper;
import si.rso.majskeigre.payment_server.core.models.customer.CustomerDto;
import si.rso.majskeigre.payment_server.database.entities.customer.CustomerEntity;
import si.rso.majskeigre.payment_server.database.repositories.customer.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressService addressService;

    private final DtoMapper<CustomerEntity, CustomerDto> customerMapper = new DtoMapper<>(CustomerEntity.class, CustomerDto.class);

    public CustomerDto upsertCustomer(CustomerDto dto) {
        return customerMapper.toDto(
                upsertCustomer(customerMapper.toEntity(
                        dto
                ))
        );
    }

    public CustomerEntity upsertCustomer(CustomerEntity customer) {
        customer.setAddress(
                addressService.upsert(customer.getAddress())
        );

        if (customer.getId() == null) {
            customerRepository.findByEmail(customer.getEmail())
                    .ifPresent(c -> customer.setId(c.getId()));
        }

        return customerRepository.save(customer);
    }

}
