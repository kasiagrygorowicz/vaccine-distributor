package exceptions;

public class WrongSupplyDemandRationException extends RuntimeException {
    public WrongSupplyDemandRationException(String errorMessage) {
        super(errorMessage);
    }
}
