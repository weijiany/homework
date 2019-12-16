import homework.taxi.exception.KilometerMusBeNonNegativeException;
import homework.taxi.model.Journey;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarSpec {

    @Test
    public void should_return_start_price_when_car_run_in_starting_distance() {
        assertEquals(3 , new Journey(LocalDateTime.now(), new LinkedList<>(List.of(Constant.START_DISTANCE))).cost());
    }

    @Test
    public void should_include_traffic_compensation_given_not_over_speed_at_day_time_after_starting_distance() {
        BigDecimal notOverSpeed = Constant.OVER_SPEED_DISTANCE.subtract(BigDecimal.valueOf(0.01d));
        assertEquals(6, new Journey(Constant.DAY_TIME, new LinkedList<>(List.of(Constant.START_DISTANCE, notOverSpeed))).cost());
    }

    @Test
    public void should_not_include_traffic_compensation_given_not_over_speed_at_day_time_after_starting_distance() {
        assertEquals(5, new Journey(Constant.DAY_TIME, new LinkedList<>(List.of(Constant.START_DISTANCE, Constant.OVER_SPEED_DISTANCE))).cost());
    }

    @Test
    public void should_return_cost_when_drive_at_night_given_over_speed_at_day_time_after_starting_distance() {
        assertEquals(6, new Journey(Constant.NIGHT_TIME, new LinkedList<>(List.of(Constant.START_DISTANCE, Constant.OVER_SPEED_DISTANCE))).cost());
    }

    @Test
    public void should_include_traffic_compensation_and_at_night_given_over_speed_at_day_time_after_starting_distance() {
        BigDecimal notOverSpeed = Constant.OVER_SPEED_DISTANCE.subtract(BigDecimal.valueOf(0.01d));
        assertEquals(7, new Journey(Constant.NIGHT_TIME, new LinkedList<>(List.of(Constant.START_DISTANCE, notOverSpeed))).cost());
    }

    @Test
    public void should_throw_exception_when_car_run_given_negative_kilometer() {
        RuntimeException exception = assertThrows(KilometerMusBeNonNegativeException.class, () -> new Journey(Constant.DAY_TIME, new LinkedList<>(List.of(BigDecimal.valueOf(-1)))));
        assertEquals(KilometerMusBeNonNegativeException.MESSAGE, exception.getMessage());
    }
}
