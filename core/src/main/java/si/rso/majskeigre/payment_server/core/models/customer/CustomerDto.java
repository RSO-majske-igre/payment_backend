package si.rso.majskeigre.payment_server.core.models.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private UUID id;
    private UUID participantId;
    private String stripeCustomerId;
}
