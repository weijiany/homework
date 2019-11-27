import org.junit.Before;
import org.junit.Test;
import strategy.DriveAtNightPriceStrategy;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DriveAtNightPriceStrategySpec {

    private LocalDateTime now;

    @Before
    public void before() {
        now = LocalDateTime.parse("2019-11-26T11:00:00");
    }

    @Test
    public void should_return_total_cost_when_input_two_point_nine_kilometer_has_fuel_cost_given_the_speed_less_than_120_time_in_night() {
        int minutes = 10;
        int cost = new DriveAtNightPriceStrategy(now, now.plusMinutes(minutes), 2.9).cost();

        assertEquals(17, cost);
    }

    @Test
    public void should_return_total_cost_when_input_120_kilometer_in_starting_distance_given_the_speed_equal_120() {
        int cost = new DriveAtNightPriceStrategy(now, now.plusHours(1), 120).cost();

        assertEquals(241, cost);
    }
}
