package exceptions;

public class NotEnoughDataException extends RuntimeException {
    public NotEnoughDataException(String errorMessage) {
        super(errorMessage);
    }
}
