package exception;

public class DistanceIsMoreThanStartDistanceException extends RuntimeException {

    public static final String MESSAGE = "Distance is more than start distance";

    public DistanceIsMoreThanStartDistanceException() {
        super(MESSAGE);
    }
}
