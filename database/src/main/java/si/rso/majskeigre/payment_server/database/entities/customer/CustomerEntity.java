package si.rso.majskeigre.payment_server.database.entities.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;
import si.rso.majskeigre.payment_server.database.BaseEntity;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "customer")
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerEntity extends BaseEntity {
    @Column(unique = true)
    private String stripeCustomerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(mappedBy = "customer")
    private Set<InvoiceEntity> invoices;
}
