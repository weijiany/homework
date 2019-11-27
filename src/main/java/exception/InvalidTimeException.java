package exception;

public class InvalidTimeException extends RuntimeException {

    public static final String MESSAGE = "End date must be more than start date.";

    public InvalidTimeException() {
        super(MESSAGE);
    }
}
