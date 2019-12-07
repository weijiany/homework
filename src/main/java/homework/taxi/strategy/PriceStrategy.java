package homework.taxi.strategy;

import homework.taxi.exception.InvalidTimeException;
import homework.taxi.exception.KilometerMusBeNonNegativeException;

import java.time.LocalDateTime;

public abstract class PriceStrategy {

    protected int second;
    protected double kilometer;

    public PriceStrategy(int second, double kilometer) {
        checkKilometerNonNegative(kilometer);
        checkTimeIsValid(second);

        this.second = second;
        this.kilometer = kilometer;
    }

    private void checkKilometerNonNegative(double kilometer) {
        if (kilometer < 0)
            throw new KilometerMusBeNonNegativeException();
    }

    private void checkTimeIsValid(int second) {
        if (second < 0)
            throw new InvalidTimeException();
    }

    public abstract int cost();
}
