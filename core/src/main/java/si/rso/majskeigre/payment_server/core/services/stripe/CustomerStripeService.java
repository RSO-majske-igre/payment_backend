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

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CustomerStripeService extends StripeBaseService{

    public CustomerEntity createStripeCustomer(CustomerEntity entity) throws StripeException {
        var addressEntity = entity.getAddress();

        var stripeCustomer =  Customer.create(new CustomerCreateParams.Builder()
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
                .build());
        return entity.setStripeCustomerId(stripeCustomer.getId());
    }
}
