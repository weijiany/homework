package exception;

public class InvalidTimeException extends DomainException {

    public static final String MESSAGE = "End date must be more than start date.";

    public InvalidTimeException() {
        super(MESSAGE);
    }
}
