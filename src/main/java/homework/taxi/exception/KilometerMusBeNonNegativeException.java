package homework.taxi.exception;

public class KilometerMusBeNonNegativeException extends Exception {

    public static final String MESSAGE = "kilometer must be non negative";

    public KilometerMusBeNonNegativeException() {
        super(MESSAGE);
    }
}
