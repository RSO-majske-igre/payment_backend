package si.rso.majskeigre.payment_server.core.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}
