import org.junit.Before;
import org.junit.Test;
import strategy.TrafficCompensationPriceStrategy;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrafficCompensationPriceStrategySpec {

    private LocalDateTime now;

    @Before
    public void before() {
        now = LocalDateTime.parse("2019-11-26T08:00:00");
    }

    @Test
    public void should_return_total_cost_when_input_two_point_nine_kilometer_has_fuel_cost_given_the_speed_less_than_120() {
        int minutes = 10;
        int cost = new TrafficCompensationPriceStrategy(now, now.plusMinutes(minutes), 2.9).cost();

        assertEquals(14, cost);
    }

    @Test
    public void should_return_total_cost_when_input_three_kilometer_has_fuel_cost_given_the_speed_less_than_120() {
        int minutes = 10;
        int cost = new TrafficCompensationPriceStrategy(now, now.plusMinutes(minutes), 3).cost();

        assertEquals(14, cost);
    }

    @Test
    public void should_return_total_cost_when_input_120_kilometer_in_starting_distance_given_the_speed_equal_120() {
        int cost = new TrafficCompensationPriceStrategy(now, now.plusHours(1), 120).cost();

        assertEquals(121, cost);
    }
}
