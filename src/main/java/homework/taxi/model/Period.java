package homework.taxi.model;

import homework.taxi.common.Const;
import homework.taxi.common.Util;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.function.Function;

class Period {
    private BigDecimal distance;
    private int second;

    public Period(Function<Integer, Boolean> isNight, LinkedList<BigDecimal> distancePerSecond) {
        BigDecimal distance = Util.getDecimalWithPrecision(2);

        int second = 0;
        while (!distancePerSecond.isEmpty()) {
            second ++;
            if (isNight.apply(second))
                break;
            distance = distance.add(distancePerSecond.pop());
        }

        this.second = second;
        this.distance = distance;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public int getMinute() {
        return (int) Math.ceil((double) second / 60);
    }

    public int getSpeed() {
        return (int) Math.ceil(distance.doubleValue() * 60 * 60 / second);
    }

    public int getPriceDistance() {
        return (int) (Math.ceil(distance.doubleValue()) * Const.PER_KILOMETER_COST);
    }
}
