package si.rso.majskeigre.payment_server.core.exceptions;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
        super("Data not found 404");
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
