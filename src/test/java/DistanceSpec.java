import homework.taxi.module.Distance;
import homework.taxi.module.PeriodDistance;
import homework.taxi.strategy.StrategyType;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceSpec {

    @Test
    public void should_return_start_period_when_distance_less_than_starting_distance() {
        Distance distance = new Distance(LocalDateTime.now(), new LinkedList<>(List.of(new BigDecimal(".1"), new BigDecimal(".1"))));

        assertEquals(List.of(new PeriodDistance(2, new BigDecimal(".20"), StrategyType.STARING)), distance.merge());
    }

    @Test
    public void should_return_start_period_when_distance_more_than_starting_distance_given_time_is_not_at_night() {
        LinkedList<BigDecimal> distanceSecond = new LinkedList<>();
        int second = 20;
        IntStream.range(0, second).forEach(i -> distanceSecond.add(new BigDecimal(".2")));
        Distance distance = new Distance(
                LocalDateTime.parse("2019-12-07T21:17:00"), distanceSecond
        );

        assertEquals(List.of(
                new PeriodDistance(10, new BigDecimal("2.00"), StrategyType.STARING),
                new PeriodDistance(10, new BigDecimal("2.00"), StrategyType.TRAFFIC_COMPENSATION)
        ), distance.merge());
    }

    @Test
    public void should_return_start_period_when_distance_more_than_starting_distance_given_time_is_at_night() {
        LinkedList<BigDecimal> distanceSecond = new LinkedList<>();
        int second = 20;
        IntStream.range(0, second).forEach(i -> distanceSecond.add(new BigDecimal(".2")));
        Distance distance = new Distance(
                LocalDateTime.parse("2019-12-07T23:17:00"), distanceSecond
        );

        assertEquals(List.of(
                new PeriodDistance(10, new BigDecimal("2.00"), StrategyType.STARING),
                new PeriodDistance(10, new BigDecimal("2.00"), StrategyType.DRIVE_AT_NIGHT)
        ), distance.merge());
    }

    @Test
    public void should_return_start_period_when_distance_more_than_starting_distance_given_time_through_11_pm_night() {
        LinkedList<BigDecimal> distanceSecond = new LinkedList<>();
        int second = 30;
        IntStream.range(0, second).forEach(i -> distanceSecond.add(new BigDecimal(".2")));
        Distance distance = new Distance(
                LocalDateTime.parse("2019-12-07T22:59:40"), distanceSecond
        );

        assertEquals(List.of(
                new PeriodDistance(10, new BigDecimal("2.00"), StrategyType.STARING),
                new PeriodDistance(10, new BigDecimal("2.00"), StrategyType.TRAFFIC_COMPENSATION),
                new PeriodDistance(10, new BigDecimal("2.00"), StrategyType.DRIVE_AT_NIGHT)
        ), distance.merge());
    }
}
