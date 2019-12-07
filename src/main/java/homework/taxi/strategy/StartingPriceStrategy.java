package homework.taxi.strategy;

import homework.taxi.exception.DistanceIsMoreThanStartDistanceException;

public class StartingPriceStrategy extends PriceStrategy {

    public StartingPriceStrategy(int second, double kilometer) {
        super(second, kilometer);
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
