import homework.taxi.strategy.DriveAtNightPriceStrategy;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DriveAtNightPriceStrategySpec {

    @Test
    public void should_return_total_cost_when_input_two_point_nine_kilometer_has_fuel_cost_given_the_speed_less_than_120_time_in_night() {
        int cost = new DriveAtNightPriceStrategy(600, 2.9).cost();

        assertEquals(17, cost);
    }

    @Test
    public void should_return_total_cost_when_input_120_kilometer_in_starting_distance_given_the_speed_equal_120() {
        int cost = new DriveAtNightPriceStrategy(3600, 120).cost();

        assertEquals(241, cost);
    }
}
