package si.rso.majskeigre.payment_server.database.entities.invoice;

import lombok.*;
import lombok.experimental.SuperBuilder;
import si.rso.majskeigre.payment_server.database.BaseEntity;
import si.rso.majskeigre.payment_server.database.entities.customer.CustomerEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "invoice")
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class InvoiceEntity extends BaseEntity {

    //    @Column(nullable = false, unique = true)
    private String stripeInvoiceId;

    private Long sumAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    private Set<InvoiceStatusHistoryEntity> invoiceStatusHistory;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    private Set<InvoiceLineEntity> invoiceLines;
}
