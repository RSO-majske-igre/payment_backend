package team.marela.backend.core.models.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private UUID id;
    private String name;
    private String email;
    private String phone;

    private UUID addressId;
    private String addressLine1;
    private String addressLine2;
    private String addressCity;
    private String addressPostalCode;
}
