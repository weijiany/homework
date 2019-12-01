package exception;

public class DistanceIsMoreThanStartDistanceException extends DomainException {

    public static final String MESSAGE = "Distance is more than start distance";

    public DistanceIsMoreThanStartDistanceException() {
        super(MESSAGE);
    }
}
