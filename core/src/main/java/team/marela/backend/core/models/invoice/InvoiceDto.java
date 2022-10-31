package team.marela.backend.core.models.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.marela.backend.core.models.customer.CustomerDto;
import team.marela.backend.database.entities.invoice.InvoiceStatusEnum;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private UUID id;
    private CustomerDto customer;
    private InvoiceStatusEnum invoiceStatus;
    private Set<InvoiceItemDto> items;
}
