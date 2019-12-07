package homework.taxi.module;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Car {

    private Distance distance;

    public Car(LocalDateTime startAt, LinkedList<BigDecimal> distancePerSecond) {
        distance = new Distance(startAt, distancePerSecond);
    }

    public int run() {
        return distance.merge().stream().map(PeriodDistance::cost).mapToInt(Integer::intValue).sum();
    }
}
