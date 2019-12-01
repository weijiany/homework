package homework.taxi.exception;

public class StrategyTypeValueNotExistException extends Exception {

    public static final String MESSAGE = "Strategy type value is not exist.";

    public StrategyTypeValueNotExistException() {
        super(MESSAGE);
    }
}
