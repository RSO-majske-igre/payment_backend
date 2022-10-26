package team.marela.backend.database.entities.invoice;

import lombok.*;
import lombok.experimental.SuperBuilder;
import team.marela.backend.database.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
