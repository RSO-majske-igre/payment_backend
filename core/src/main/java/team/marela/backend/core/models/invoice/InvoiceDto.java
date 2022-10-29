package team.marela.backend.core.models.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.marela.backend.core.models.customer.CustomerDto;
import team.marela.backend.database.entities.invoice.InvoiceStatusEnum;

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
