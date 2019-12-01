package homework.taxi.strategy;

import java.time.LocalDateTime;

public class PriceStrategyFactory {

    private PriceStrategyFactory() {}

    public static PriceStrategy create(LocalDateTime startAt, LocalDateTime endAt, double kilometer, StrategyType type) {
        switch (type) {
            case STARING:
                return new StartingPriceStrategy(startAt, endAt, kilometer);
            case TRAFFIC_COMPENSATION:
                return new TrafficCompensationPriceStrategy(startAt, endAt, kilometer);
            case DRIVE_AT_NIGHT:
                return new DriveAtNightPriceStrategy(startAt, endAt, kilometer);
            default:
                throw new UnsupportedOperationException();
        }
    }
}
