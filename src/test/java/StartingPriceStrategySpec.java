import homework.taxi.strategy.StartingPriceStrategy;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartingPriceStrategySpec {

    @Test
    public void should_return_start_price_when_distance_in_starting_distance() {
        int cost = new StartingPriceStrategy().cost(LocalDateTime.now(), new ArrayDeque<>(List.of(BigDecimal.valueOf(1))));

        assertEquals(3 , cost);
    }
}
