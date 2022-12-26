package si.rso.majskeigre.payment_server.core.external.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {
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