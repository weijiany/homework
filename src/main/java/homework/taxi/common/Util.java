package homework.taxi.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Queue;

public abstract class Util {

    public static boolean isAtNight(LocalDateTime startAt, LocalDateTime targetTime) {
        LocalDateTime leftTime = LocalDateTime.of(startAt.toLocalDate(), LocalTime.of(23, 0));
        LocalDateTime rightTime = LocalDateTime.of(startAt.toLocalDate().plusDays(1), LocalTime.of(6, 0));
        return targetTime.isAfter(leftTime) && targetTime.isBefore(rightTime);
    }

    public static int skipStartDistance(Queue<BigDecimal> distancePerSecond) {
        int second = 0;
        BigDecimal distance = getInitDistance();
        while (!distancePerSecond.isEmpty()) {
            second ++;
            BigDecimal space = distancePerSecond.poll();
            distance = distance.add(space);
            if (distance.doubleValue() >= Const.STRING_DISTANCE)
                break;
        }
        return second;
    }

    public static BigDecimal getInitDistance() {
        BigDecimal distance = new BigDecimal(0);
        distance = distance.setScale(2, RoundingMode.HALF_UP);
        return distance;
    }
}
