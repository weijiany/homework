package homework.taxi.strategy;

public class DriveAtNightPriceStrategy extends PriceStrategy {

    public DriveAtNightPriceStrategy(int second, double kilometer) {
        super(second, kilometer);
    }

    @Override
    public int cost() {
        return new TrafficCompensationPriceStrategy(second, kilometer).cost() + (int)Math.ceil(kilometer);
    }
}
