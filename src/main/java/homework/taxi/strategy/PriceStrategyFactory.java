package homework.taxi.strategy;

public class PriceStrategyFactory {

    private PriceStrategyFactory() {}

    public static PriceStrategy create(int second, double kilometer, StrategyType type) {
        switch (type) {
            case STARING:
                return new StartingPriceStrategy(second, kilometer);
            case TRAFFIC_COMPENSATION:
                return new TrafficCompensationPriceStrategy(second, kilometer);
            case DRIVE_AT_NIGHT:
                return new DriveAtNightPriceStrategy(second, kilometer);
            default:
                throw new UnsupportedOperationException();
        }
    }
}
