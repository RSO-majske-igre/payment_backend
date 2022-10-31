package si.rso.majskeigre.payment_server.core.models.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import si.rso.majskeigre.payment_server.core.models.customer.CustomerDto;
import si.rso.majskeigre.payment_server.database.entities.invoice.InvoiceStatusEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private UUID id;

    @NotNull(message = "Amount shound not be null")
    @Min(value = 0, message = "Amount shold be larger then 0")
    private Long sumAmount;

    @NotNull(message = "Customer should not be null")
    private CustomerDto customer;

    @NotNull(message = "Status should not be null")
    private InvoiceStatusEnum status;

    private Set<InvoiceLineDto> invoiceLines;

}
