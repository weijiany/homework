import homework.taxi.strategy.DriveAtNightPriceStrategy;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DriveAtNightPriceStrategySpec {

    @Test
    public void should_include_traffic_compensation_and_drive_at_night_given_not_over_speed_after_starting_distance() {
        BigDecimal notOverSpeed = Constant.OVER_SPEED_DISTANCE.subtract(BigDecimal.valueOf(0.01d));
        int cost = new DriveAtNightPriceStrategy().cost(Constant.NIGHT_TIME,
                new ArrayDeque<>(List.of(Constant.START_DISTANCE, notOverSpeed)));

        assertEquals(4, cost);
    }

    @Test
    public void should_return_total_cost_when_input_120_kilometer_in_starting_distance_given_the_speed_equal_120() {
        int cost = new DriveAtNightPriceStrategy().cost(Constant.NIGHT_TIME,
                new ArrayDeque<>(List.of(Constant.START_DISTANCE, Constant.OVER_SPEED_DISTANCE)));

        assertEquals(3, cost);
    }
}
