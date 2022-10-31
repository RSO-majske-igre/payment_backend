package si.rso.majskeigre.payment_server.core.services.stripe;

import com.stripe.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerStripeService {

    public Customer createStripeCustomer() {
        return null;
    }
}
