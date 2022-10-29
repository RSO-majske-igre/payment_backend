package team.marela.backend.core.models.invoice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceLineDto {

    private UUID id;

    @NotNull(message = "Name should be set")
    @NotEmpty(message = "Name should be set")
    private String name;


    @NotNull(message = "Amount should be set")
    @Min(value = 0, message = "Amount should be greter then 0")
    private Long price;
}
