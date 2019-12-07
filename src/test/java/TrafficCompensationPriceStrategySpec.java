import homework.taxi.strategy.TrafficCompensationPriceStrategy;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrafficCompensationPriceStrategySpec {

    @Test
    public void should_return_total_cost_when_input_two_point_nine_kilometer_has_fuel_cost_given_the_speed_less_than_120() {
        int cost = new TrafficCompensationPriceStrategy(600, 2.9).cost();

        assertEquals(14, cost);
    }

    @Test
    public void should_return_total_cost_when_input_three_kilometer_has_fuel_cost_given_the_speed_less_than_120() {
        int cost = new TrafficCompensationPriceStrategy(600, 3).cost();

        assertEquals(14, cost);
    }

    @Test
    public void should_return_total_cost_when_input_120_kilometer_in_starting_distance_given_the_speed_equal_120() {
        int cost = new TrafficCompensationPriceStrategy(3600, 120).cost();

        assertEquals(121, cost);
    }
}
