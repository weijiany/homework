package exception;

public class KilometerMusBeNonNegativeException extends DomainException {

    public static final String MESSAGE = "kilometer must be non negative";

    public KilometerMusBeNonNegativeException() {
        super(MESSAGE);
    }
}
