package exceptions;

public class WrongNumericArgumentException extends RuntimeException {
    public WrongNumericArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
