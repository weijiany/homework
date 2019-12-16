package homework.taxi.strategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Queue;

public class StartingPriceStrategy implements PriceStrategy {

    public StartingPriceStrategy() {
    }

    @Override
    public int cost(LocalDateTime startAt, Queue<BigDecimal> distancePerSecond) {
        return StrategyConst.STRING_PRICE;
    }
}
