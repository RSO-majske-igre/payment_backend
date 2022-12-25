package si.rso.majskeigre.payment_server.core.services.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Address;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import si.rso.majskeigre.payment_server.database.entities.customer.CustomerEntity;

@Service
@RequiredArgsConstructor
public class CustomerStripeService {

    @Value("${stripe.api-key}")
    private String apiKey;

    public CustomerEntity createStripeCustomer(CustomerEntity entity) throws StripeException {
        Stripe.apiKey = apiKey;
        var addressEntity = entity.getAddress();

        CustomerCreateParams ccp = new CustomerCreateParams.Builder()
            .setName(entity.getName())
            .setEmail(entity.getEmail())
            .setPhone(entity.getPhone())
            .setAddress(CustomerCreateParams.Address.builder()
                    .setCity(addressEntity.getCity())
                    .setCountry("sl")
                    .setLine1(addressEntity.getLine1())
                    .setLine2(addressEntity.getLine2())
                    .setPostalCode(addressEntity.getPostalCode())
                    .build())
            .build();

        var stripeCustomer =  Customer.create(ccp);
        return entity.setStripeCustomerId(stripeCustomer.getId());
    }
}
