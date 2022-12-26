package si.rso.majskeigre.payment_server.core.services.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.PaymentIntent;
import com.stripe.param.InvoiceCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import si.rso.majskeigre.payment_server.core.external.models.ParticipantDto;
import si.rso.majskeigre.payment_server.database.entities.customer.CustomerEntity;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceEntity;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class InvoiceStripeService extends StripeBaseService{

    private final CustomerStripeService customerStripeService;

    public InvoiceEntity createInvoice(InvoiceEntity entity, CustomerEntity customer) throws StripeException {
        var pi = PaymentIntent.create(PaymentIntentCreateParams.builder()
                .setCustomer(customer.getStripeCustomerId())
                .setAmount(entity.getSumAmount())
                .setCurrency("eur")
                .build());
        return entity
                .setStripeInvoiceId(pi.getId())
                .setCustomer(customer);
    }
}
