package si.rso.majskeigre.payment_server.database.entities.invoice;

import lombok.*;
import lombok.experimental.SuperBuilder;
import si.rso.majskeigre.payment_server.database.BaseEntity;

import javax.persistence.*;

@Entity(name = "invoice_status_history")
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class InvoiceStatusHistoryEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoice;

    @Enumerated(EnumType.STRING)
    private InvoiceStatusEnum invoiceStatus;
}
