package homework.taxi.strategy;

import homework.taxi.exception.InvalidTimeException;
import homework.taxi.exception.KilometerMusBeNonNegativeException;

import java.time.LocalDateTime;

public abstract class PriceStrategy {

    protected LocalDateTime startAt;
    protected LocalDateTime endAt;
    protected double kilometer;

    public PriceStrategy(LocalDateTime startAt, LocalDateTime endAt, double kilometer) {
        checkKilometerNonNegative(kilometer);
        checkTimeIsValid(startAt, endAt);

        this.startAt = startAt;
        this.endAt = endAt;
        this.kilometer = kilometer;
    }

    private void checkKilometerNonNegative(double kilometer) {
        if (kilometer < 0)
            throw new KilometerMusBeNonNegativeException();
    }

    private void checkTimeIsValid(LocalDateTime startAt, LocalDateTime endAt) {
        if (!startAt.isBefore(endAt))
            throw new InvalidTimeException();
    }

    public abstract int cost();
}
