package exception;

public class KilometerMusBeNonNegativeException extends RuntimeException {

    public static final String MESSAGE = "kilometer must be non negative";

    public KilometerMusBeNonNegativeException() {
        super(MESSAGE);
    }
}
