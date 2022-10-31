package si.rso.majskeigre.payment_server.api.exceptions;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionApiModel {
    private String exception;
    private String exceptionMessage;
    private Integer exceptionNo;
    private Object body;
}
