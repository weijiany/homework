package homework.taxi.strategy;

import java.time.LocalDateTime;

public class DriveAtNightPriceStrategy extends PriceStrategy {

    public DriveAtNightPriceStrategy(LocalDateTime startAt, LocalDateTime endAt, double kilometer) {
        super(startAt, endAt, kilometer);
    }

    @Override
    public int cost() {
        return new TrafficCompensationPriceStrategy(startAt, endAt, kilometer).cost() + (int)Math.ceil(kilometer);
    }
}
