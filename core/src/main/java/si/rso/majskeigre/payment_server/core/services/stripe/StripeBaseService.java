package si.rso.majskeigre.payment_server.core.services.stripe;

import com.stripe.Stripe;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class StripeBaseService {

    @Value("${stripe.api-key}")
    private String stripeApiKey;

    @PostConstruct
    private void setStripeApi() {
        Stripe.apiKey = stripeApiKey;
    }
}
