package si.rso.majskeigre.payment_server.core.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
