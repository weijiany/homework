package homework.taxi.strategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Queue;

public class DriveAtNightPriceStrategy implements PriceStrategy {

    public DriveAtNightPriceStrategy() {
    }

    @Override
    public int cost(LocalDateTime startAt, Queue<BigDecimal> distancePerSecond) {
        Util.skipStartDistance(distancePerSecond);
        BigDecimal distance = Util.getInitDistance();

        int second = 0;
        while (!distancePerSecond.isEmpty()) {
            second ++;
            if (!Util.isAtNight(startAt, startAt.plusSeconds(second)))
                break;
            distance = distance.add(distancePerSecond.poll());
        }

        int priceDistance = (int) (Math.ceil(distance.doubleValue()) * StrategyConst.PER_KILOMETER_COST);

        int minute = (int) Math.ceil((double) second / 60);
        int speed = (int) Math.ceil(distance.doubleValue() * 60 * 60 / second);

        if (speed == 0)
            return 0;
        else if (speed < StrategyConst.AVERAGE_SPEED)
            return priceDistance + minute * StrategyConst.PER_MINUTE_COST + StrategyConst.FUEL_COST + (int)Math.ceil(distance.doubleValue());
        else
            return priceDistance + StrategyConst.FUEL_COST + (int)Math.ceil(distance.doubleValue());
    }
}
