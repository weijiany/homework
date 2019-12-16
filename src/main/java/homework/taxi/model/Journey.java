package homework.taxi.model;

import homework.taxi.exception.KilometerMusBeNonNegativeException;
import homework.taxi.strategy.DriveAtNightPriceStrategy;
import homework.taxi.strategy.PriceStrategy;
import homework.taxi.strategy.StartingPriceStrategy;
import homework.taxi.strategy.TrafficCompensationPriceStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Journey {

    private LocalDateTime startAt;
    private LinkedList<BigDecimal> distancePerSecond;
    private List<PriceStrategy> strategies = List.of(
            new StartingPriceStrategy(),
            new TrafficCompensationPriceStrategy(),
            new DriveAtNightPriceStrategy()
    );

    public Journey(LocalDateTime startAt, LinkedList<BigDecimal> distancePerSecond) {
        checkDistanceIsNonNegative(distancePerSecond);

        this.startAt = startAt;
        this.distancePerSecond = distancePerSecond;
    }

    private void checkDistanceIsNonNegative(LinkedList<BigDecimal> distancePerSecond) {
        if (distancePerSecond.stream().anyMatch(d -> d.doubleValue() <= 0))
            throw new KilometerMusBeNonNegativeException();
    }

    public int cost() {
        return strategies.stream().map(s -> s.cost(startAt, Queue.class.cast(distancePerSecond.clone()))).mapToInt(Integer::intValue).sum();
    }
}
