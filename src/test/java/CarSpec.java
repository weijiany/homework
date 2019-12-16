import homework.taxi.exception.KilometerMusBeNonNegativeException;
import homework.taxi.module.Car;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarSpec {

    private final BigDecimal START_DISTANCE = BigDecimal.valueOf(2);
    private final BigDecimal OVER_SPEED = BigDecimal.valueOf(120.0 / 60 / 60);
    private LocalDateTime nightTime = LocalDateTime.parse("2019-11-26T23:00:00");
    private LocalDateTime dayTime = LocalDateTime.parse("2019-11-26T08:00:00");

    @Test
    public void should_return_start_price_when_car_run_two_kilometer_in_starting_distance() {
        assertEquals(3 , new Car(LocalDateTime.now(), new LinkedList<>(List.of(START_DISTANCE))).cost());
    }

    @Test
    public void should_include_traffic_compensation_given_not_over_speed_at_day_time_after_starting_distance() {
        BigDecimal notOverSpeed = OVER_SPEED.subtract(BigDecimal.valueOf(0.01d));
        assertEquals(6, new Car(dayTime, new LinkedList<>(List.of(START_DISTANCE, notOverSpeed))).cost());
    }

    @Test
    public void should_not_include_traffic_compensation_given_not_over_speed_at_day_time_after_starting_distance() {
        assertEquals(5, new Car(dayTime, new LinkedList<>(List.of(START_DISTANCE, OVER_SPEED))).cost());
    }

    @Test
    public void should_include_and_day_night_given_over_speed_at_day_time_after_starting_distance() {
        assertEquals(6, new Car(nightTime, new LinkedList<>(List.of(START_DISTANCE, OVER_SPEED))).cost());
    }

    @Test
    public void should_include_traffic_compensation_and_day_night_given_over_speed_at_day_time_after_starting_distance() {
        BigDecimal notOverSpeed = OVER_SPEED.subtract(BigDecimal.valueOf(0.01d));
        assertEquals(7, new Car(nightTime, new LinkedList<>(List.of(START_DISTANCE, notOverSpeed))).cost());
    }

    @Test
    public void should_throw_exception_when_car_run_given_negative_kilometer() {
        RuntimeException exception = assertThrows(KilometerMusBeNonNegativeException.class, () -> new Car(dayTime, new LinkedList<>(List.of(BigDecimal.valueOf(-1)))));
        assertEquals(KilometerMusBeNonNegativeException.MESSAGE, exception.getMessage());
    }
}
