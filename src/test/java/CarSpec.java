import homework.taxi.exception.KilometerMusBeNonNegativeException;
import homework.taxi.module.Car;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarSpec {

    private LocalDateTime now;
    private LinkedList<BigDecimal> distancePerSecond;

    @Before
    public void before() {
        now = LocalDateTime.parse("2019-11-26T08:00:00");
        distancePerSecond = buildStaringDistance();
    }

    @Test
    public void should_return_start_price_when_car_run_two_kilometer_in_starting_distance() {
        assertEquals(3 , new Car(now, distancePerSecond).run());
    }

    @Test
    public void should_return_total_cost_when_car_run_two_point_nine_kilometer_out_of_starting_distance_and_has_fuel_cost_given_the_speed_less_than_120_not_at_night() {
        int second = 60 * 10;
        IntStream.range(0, second).forEach(i -> distancePerSecond.add(BigDecimal.valueOf(2.9 / second)));

        assertEquals(17, new Car(now, distancePerSecond).run());
    }

    @Test
    public void should_return_total_cost_given_the_speed_less_than_120_not_at_night_more_than_staring_distance_not_enough_1() {
        distancePerSecond.add(BigDecimal.valueOf(0.03));

        assertEquals(6, new Car(now, distancePerSecond).run());
    }

    @Test
    public void should_throw_KilometerMusBeNonNegativeException_when_car_run_given_negative_kilometer() {
        RuntimeException exception = assertThrows(KilometerMusBeNonNegativeException.class, () -> new Car(now, new LinkedList<>(List.of(BigDecimal.valueOf(-1)))));
        assertEquals(KilometerMusBeNonNegativeException.MESSAGE, exception.getMessage());
    }

    @Test
    public void should_return_total_cost_when_input_120_kilometer_in_starting_distance_given_the_speed_equal_120() {
        int second = 60 * 60;
        IntStream.range(0, second).forEach(i -> distancePerSecond.add(BigDecimal.valueOf(120.0 / second)));

        assertEquals(244, new Car(LocalDateTime.parse("2019-12-07T23:00:00"), distancePerSecond).run());
    }

    private LinkedList<BigDecimal> buildStaringDistance() {
        LinkedList<BigDecimal> distancePerSecond = new LinkedList<>();
        IntStream.range(0, 10).forEach(i -> distancePerSecond.add(new BigDecimal(".2")));
        return distancePerSecond;
    }
}
