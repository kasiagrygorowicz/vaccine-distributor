package exceptions;

public class WrongNumberOfConnectionsException extends RuntimeException {
    public WrongNumberOfConnectionsException(String errorMessage) {
        super(errorMessage);
    }

}
