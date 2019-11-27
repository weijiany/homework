package strategy;

import exception.DistanceIsMoreThanStartDistanceException;

import java.time.LocalDateTime;

public class StartingPriceStrategy extends PriceStrategy {

    public StartingPriceStrategy(LocalDateTime startAt, LocalDateTime endAt, double kilometer) {
        super(startAt, endAt, kilometer);
        checkKilometerExceedStartDistance(kilometer);
    }

    private void checkKilometerExceedStartDistance(double kilometer) {
        if (kilometer > StrategyConst.STRING_DISTANCE)
            throw new DistanceIsMoreThanStartDistanceException();
    }

    @Override
    public int cost() {
        return StrategyConst.STRING_PRICE;
    }
}
