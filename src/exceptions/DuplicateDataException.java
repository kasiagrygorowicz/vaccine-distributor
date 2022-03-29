package exceptions;

public class DuplicateDataException extends RuntimeException {
    public DuplicateDataException(String errorMessage) {
        super(errorMessage);
    }

}
