package si.rso.majskeigre.payment_server.core.models.webhooks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebhookDto<T> {
    private UUID id;

    @NotNull
    @NotEmpty
    private String endpointUrl;

    private String secretKey;
    private T body;
}
