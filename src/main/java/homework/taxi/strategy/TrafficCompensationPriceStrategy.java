package homework.taxi.strategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class TrafficCompensationPriceStrategy extends PriceStrategy {

    public TrafficCompensationPriceStrategy(LocalDateTime startAt, LocalDateTime endAt, double kilometer) {
        super(startAt, endAt, kilometer);
    }

    @Override
    public int cost() {
        int priceDistance = (int) (Math.ceil(kilometer) * StrategyConst.PER_KILOMETER_COST);

        int minute = (int) (Duration.between(startAt, endAt).getSeconds() / 60);
        double speed = kilometer * 60 / minute;

        return (speed < StrategyConst.AVERAGE_SPEED)
                ? priceDistance + minute * StrategyConst.PER_MINUTE_COST + StrategyConst.FUEL_COST
                : priceDistance + StrategyConst.FUEL_COST;
    }
}
