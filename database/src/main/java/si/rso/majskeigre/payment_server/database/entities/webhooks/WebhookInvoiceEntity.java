package si.rso.majskeigre.payment_server.database.entities.webhooks;

import lombok.*;
import lombok.experimental.SuperBuilder;
import si.rso.majskeigre.payment_server.database.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "webhook_invoice")
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WebhookInvoiceEntity extends BaseEntity {

    @Column(unique = true)
    private String endpointUrl;
    private String secretKey;

//    @ManyToOne
//    @JoinColumn(name = "invoice_id")
//    private InvoiceEntity invoice;
}
