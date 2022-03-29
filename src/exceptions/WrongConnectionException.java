package exceptions;

public class WrongConnectionException extends RuntimeException {
    public WrongConnectionException(String errorMessage) {
        super(errorMessage);
    }
}
