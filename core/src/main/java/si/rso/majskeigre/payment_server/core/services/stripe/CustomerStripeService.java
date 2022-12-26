package si.rso.majskeigre.payment_server.core.services.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Address;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import si.rso.majskeigre.payment_server.core.external.models.ParticipantDto;
import si.rso.majskeigre.payment_server.database.entities.customer.CustomerEntity;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CustomerStripeService extends StripeBaseService{

    public CustomerEntity createStripeCustomer(CustomerEntity customer, ParticipantDto participant) throws StripeException {
        var stripeCustomer =  Customer.create(new CustomerCreateParams.Builder()
                .setName(participant.getName())
                .setEmail(participant.getEmail())
                .setPhone(participant.getPhone())
                .setAddress(CustomerCreateParams.Address.builder()
                        .setCity(participant.getAddressCity())
                        .setCountry("sl")
                        .setLine1(participant.getAddressLine1())
                        .setLine2(participant.getAddressLine2())
                        .setPostalCode(participant.getAddressPostalCode())
                        .build())
                .build());
        return customer.setStripeCustomerId(stripeCustomer.getId());
    }
}
