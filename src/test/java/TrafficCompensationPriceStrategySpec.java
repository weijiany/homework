import homework.taxi.strategy.TrafficCompensationPriceStrategy;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrafficCompensationPriceStrategySpec {

    @Test
    public void should_include_traffic_compensation_given_not_over_speed_at_day_time_after_starting_distance() {
        BigDecimal notOverSpeed = Constant.OVER_SPEED_DISTANCE.subtract(BigDecimal.valueOf(0.01d));
        assertEquals(3, new TrafficCompensationPriceStrategy().cost(Constant.DAY_TIME,
                new LinkedList<>(List.of(Constant.START_DISTANCE, notOverSpeed))));
    }

    @Test
    public void should_not_include_traffic_compensation_given_not_over_speed_at_day_time_after_starting_distance() {
        assertEquals(2, new TrafficCompensationPriceStrategy().cost(Constant.DAY_TIME,
                new LinkedList<>(List.of(Constant.START_DISTANCE, Constant.OVER_SPEED_DISTANCE))));
    }
}
