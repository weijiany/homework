import homework.taxi.exception.DistanceIsMoreThanStartDistanceException;
import org.junit.Test;
import homework.taxi.strategy.StartingPriceStrategy;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StartingPriceStrategySpec {

    @Test
    public void should_return_start_price_when_input_two_kilometer_in_starting_distance() {
        int cost = new StartingPriceStrategy(LocalDateTime.now(), LocalDateTime.now().plusMinutes(1), 2).cost();

        assertEquals(3 , cost);
    }

    @Test
    public void should_throw_DistanceIsMoreThanStartDistanceException_when_input_kilometer_more_than_start_distance() {
        RuntimeException exception = assertThrows(DistanceIsMoreThanStartDistanceException.class,
                () -> new StartingPriceStrategy(LocalDateTime.now(), LocalDateTime.now().plusMinutes(1), 3));
        assertEquals(DistanceIsMoreThanStartDistanceException.MESSAGE, exception.getMessage());
    }

    @Test
    public void should_return_start_price_when_car_run_one_point_one_kilometer_in_starting_distance() {
        int cost = new StartingPriceStrategy(LocalDateTime.now(), LocalDateTime.now().plusMinutes(1), 1.1).cost();

        assertEquals(3 , cost);
    }
}
