package si.rso.majskeigre.payment_server.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import si.rso.majskeigre.payment_server.core.exceptions.DataNotFoundException;
import si.rso.majskeigre.payment_server.core.mappers.DtoMapper;
import si.rso.majskeigre.payment_server.core.models.customer.CustomerDto;
import si.rso.majskeigre.payment_server.database.entities.customer.CustomerEntity;
import si.rso.majskeigre.payment_server.database.repositories.customer.CustomerRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final DtoMapper<CustomerEntity, CustomerDto> customerMapper = new DtoMapper<>(CustomerEntity.class, CustomerDto.class);


    public CustomerEntity upsertCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public CustomerEntity getCustomer(UUID id) {
        return customerRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    public CustomerEntity getCustomerByParticipantId(UUID id) {
        return customerRepository.findByParticipantId(id).orElseThrow(DataNotFoundException::new);
    }

}
