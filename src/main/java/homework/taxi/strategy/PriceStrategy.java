package homework.taxi.strategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Queue;

public interface PriceStrategy {

    int cost(LocalDateTime startAt, Queue<BigDecimal> distancePerSecond);
}
