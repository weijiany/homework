import homework.taxi.exception.InvalidTimeException;
import homework.taxi.exception.KilometerMusBeNonNegativeException;
import homework.taxi.module.Car;
import org.junit.Before;
import org.junit.Test;
import homework.taxi.strategy.StrategyType;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarSpec {

    private Car car;
    private LocalDateTime now;

    @Before
    public void before() {
        car = new Car();
        now = LocalDateTime.parse("2019-11-26T08:00:00");
    }

    @Test
    public void should_return_start_price_when_car_run_two_kilometer_in_starting_distance() {
        int minutes = 10;
        int cost = car.run(now, now.plusMinutes(minutes), 2, StrategyType.STARING);

        assertEquals(3 , cost);
    }

    @Test
    public void should_return_total_cost_when_car_run_two_point_nine_kilometer_out_of_starting_distance_and_has_fuel_cost_given_the_speed_less_than_120() {
        int minutes = 10;
        int cost = car.run(now, now.plusMinutes(minutes), 2.9, StrategyType.TRAFFIC_COMPENSATION);

        assertEquals(14, cost);
    }

    @Test
    public void should_throw_KilometerMusBeNonNegativeException_when_car_run_given_negative_kilometer() {
        RuntimeException exception = assertThrows(KilometerMusBeNonNegativeException.class, () -> car.run(now, now.plusMinutes(1), -1, StrategyType.STARING));
        assertEquals(KilometerMusBeNonNegativeException.MESSAGE, exception.getMessage());
    }

    @Test
    public void should_throw_InvalidTimeException_when_car_run_give_end_time_less_than_start_time() {
        RuntimeException exception = assertThrows(InvalidTimeException.class, () -> car.run(now, now.plusMinutes(-1), 1, StrategyType.STARING));
        assertEquals(InvalidTimeException.MESSAGE, exception.getMessage());
    }

    @Test
    public void should_throw_InvalidTimeException_when_car_run_give_end_time_equal_start_time() {
        RuntimeException exception = assertThrows(InvalidTimeException.class, () -> car.run(now, now, 1, StrategyType.STARING));
        assertEquals(InvalidTimeException.MESSAGE, exception.getMessage());
    }

    @Test
    public void should_return_total_cost_when_input_120_kilometer_in_starting_distance_given_the_speed_equal_120() {
        int cost = car.run(now, now.plusHours(1), 120, StrategyType.DRIVE_AT_NIGHT);

        assertEquals(241, cost);
    }
}
