package module;

import strategy.PriceStrategyFactory;
import strategy.StrategyType;

import java.time.LocalDateTime;

public class Car {

    public int run(LocalDateTime startAt, LocalDateTime endAt, double kilometer, StrategyType type) {
        return PriceStrategyFactory.create(startAt, endAt, kilometer, type).cost();
    }
}
