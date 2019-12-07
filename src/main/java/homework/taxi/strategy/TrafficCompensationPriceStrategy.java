package homework.taxi.strategy;

public class TrafficCompensationPriceStrategy extends PriceStrategy {

    public TrafficCompensationPriceStrategy(int second, double kilometer) {
        super(second, kilometer);
    }

    @Override
    public int cost() {
        int priceDistance = (int) (Math.ceil(kilometer) * StrategyConst.PER_KILOMETER_COST);

        // second may be less than 60
        int minute = (int) Math.ceil((double) second / 60);
        int speed = (int) Math.ceil(kilometer * 60 * 60 / second);

        return (speed < StrategyConst.AVERAGE_SPEED)
                ? priceDistance + minute * StrategyConst.PER_MINUTE_COST + StrategyConst.FUEL_COST
                : priceDistance + StrategyConst.FUEL_COST;
    }
}
