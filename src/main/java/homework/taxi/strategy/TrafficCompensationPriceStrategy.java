package homework.taxi.strategy;

public class TrafficCompensationPriceStrategy extends PriceStrategy {

    public TrafficCompensationPriceStrategy(int second, double kilometer) {
        super(second, kilometer);
    }

    @Override
    public int cost() {
        int priceDistance = (int) (Math.ceil(kilometer) * StrategyConst.PER_KILOMETER_COST);

        int minute = second / 60;
        double speed = kilometer * 60 / minute;

        return (speed < StrategyConst.AVERAGE_SPEED)
                ? priceDistance + minute * StrategyConst.PER_MINUTE_COST + StrategyConst.FUEL_COST
                : priceDistance + StrategyConst.FUEL_COST;
    }
}
