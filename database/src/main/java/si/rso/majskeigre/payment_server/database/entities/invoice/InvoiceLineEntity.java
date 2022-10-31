package si.rso.majskeigre.payment_server.database.entities.invoice;

import lombok.*;
import lombok.experimental.SuperBuilder;
import si.rso.majskeigre.payment_server.database.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "invoice_line")
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class InvoiceLineEntity extends BaseEntity {

    @Column(nullable = false)
    private String lineName;

    @Column(nullable = false)
    private Long price;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoice;
}
