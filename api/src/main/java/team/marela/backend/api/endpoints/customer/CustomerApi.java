package team.marela.backend.api.endpoints.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.marela.backend.core.models.customer.CustomerDto;
import team.marela.backend.core.services.CustomerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerApi {

    private final CustomerService customerService;

    @PostMapping
    public CustomerDto postCustomer(@RequestBody CustomerDto dto) {
        return customerService.upsertCustomer(dto);
    }
}
