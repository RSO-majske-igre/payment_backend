package si.rso.majskeigre.payment_server.database.entities.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import si.rso.majskeigre.payment_server.database.BaseEntity;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceEntity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "customer")
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    @Type(type = "uuid-char")
    private UUID participantId;

    @Column(unique = true)
    private String stripeCustomerId;
}
