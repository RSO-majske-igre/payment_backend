package team.marela.backend.core.services.stripe;

import com.stripe.model.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceStripeService {

    @Value("{stripe.api-key}")
    private String stripeApi;

    public Invoice createInvoice() {
//        InvoiceCreateParams
        return null;
    }
}
