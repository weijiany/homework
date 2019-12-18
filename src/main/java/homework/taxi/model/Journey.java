package homework.taxi.model;

import homework.taxi.common.Const;
import homework.taxi.common.Util;
import homework.taxi.exception.KilometerMusBeNonNegativeException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Journey {

    private LocalDateTime startAt;
    private LinkedList<BigDecimal> distancePerSecond;

    public Journey(LocalDateTime startAt, LinkedList<BigDecimal> distancePerSecond) throws KilometerMusBeNonNegativeException {
        checkDistanceIsNonNegative(distancePerSecond);

        this.startAt = startAt;
        this.distancePerSecond = distancePerSecond;
    }

    private void checkDistanceIsNonNegative(LinkedList<BigDecimal> distancePerSecond) throws KilometerMusBeNonNegativeException {
        if (distancePerSecond.stream().anyMatch(d -> d.doubleValue() <= 0))
            throw new KilometerMusBeNonNegativeException();
    }

    public int cost() {
        int cost = Const.STRING_PRICE;
        int startSeconds = Util.skipStartDistance(distancePerSecond);

        boolean isNight = Util.isAtNight(startAt, startAt.plusSeconds(startSeconds));
        while (!distancePerSecond.isEmpty()) {
            if (isNight)
                cost += costForDriveAtNight();
            else
                cost += costForTrafficCompensation();
        }
        return cost;
    }

    private int costForTrafficCompensation() {
        Period period = new Period(second -> Util.isAtNight(startAt, startAt.plusSeconds(second)), distancePerSecond);

        int priceDistance = period.getPriceDistance();

        int minute = period.getMinute();
        int speed = period.getSpeed();

        if (speed == 0)
            return 0;
        else if (speed < Const.AVERAGE_SPEED)
            return priceDistance + minute * Const.PER_MINUTE_COST + Const.FUEL_COST;
        else
            return priceDistance + Const.FUEL_COST;
    }

    private int costForDriveAtNight() {
        Period period = new Period(second -> !Util.isAtNight(startAt, startAt.plusSeconds(second)), distancePerSecond);

        int priceDistance = period.getPriceDistance();

        int minute = period.getMinute();
        int speed = period.getSpeed();

        if (speed == 0)
            return 0;
        else if (speed < Const.AVERAGE_SPEED)
            return priceDistance + minute * Const.PER_MINUTE_COST + Const.FUEL_COST + (int)Math.ceil(period.getDistance().doubleValue());
        else
            return priceDistance + Const.FUEL_COST + (int)Math.ceil(period.getDistance().doubleValue());
    }
}

