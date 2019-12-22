package homework.taxi.model;

import homework.taxi.common.Const;
import homework.taxi.common.Util;
import homework.taxi.exception.KilometerMusBeNonNegativeException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Queue;

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
        int startSeconds = skipStartDistance(distancePerSecond);

        cost = AddFuelCost(cost);

        boolean isNight = isAtNight(startAt, startAt.plusSeconds(startSeconds));
        while (!distancePerSecond.isEmpty()) {
            if (isNight)
                cost += costForDriveAtNight();
            else
                cost += costForTrafficCompensation();
        }
        return cost;
    }

    private int AddFuelCost(int cost) {
        if (!distancePerSecond.isEmpty())
            cost += Const.FUEL_COST;
        return cost;
    }

    private int costForTrafficCompensation() {
        Period period = new Period(second -> isAtNight(startAt, startAt.plusSeconds(second)), distancePerSecond);

        return costForTrafficCompensation(period);
    }

    private int costForDriveAtNight() {
        Period period = new Period(second -> !isAtNight(startAt, startAt.plusSeconds(second)), distancePerSecond);

        int drivingNightCost = (int) Math.ceil(period.getDistance().doubleValue());
        return costForTrafficCompensation(period) + drivingNightCost;
    }

    private int costForTrafficCompensation(Period period) {
        int minute = period.getMinute();
        int speed = period.getSpeed();

        return speed < Const.AVERAGE_SPEED
                ? period.getPriceDistance() + minute * Const.PER_MINUTE_COST
                : period.getPriceDistance();
    }

    private boolean isAtNight(LocalDateTime startAt, LocalDateTime targetTime) {
        LocalDateTime leftTime = LocalDateTime.of(startAt.toLocalDate(), LocalTime.of(23, 0));
        LocalDateTime rightTime = LocalDateTime.of(startAt.toLocalDate().plusDays(1), LocalTime.of(6, 0));
        return targetTime.isAfter(leftTime) && targetTime.isBefore(rightTime);
    }

    private int skipStartDistance(Queue<BigDecimal> distancePerSecond) {
        int second = 0;
        BigDecimal distance = Util.getDecimalWithPrecision(2);
        while (!distancePerSecond.isEmpty()) {
            second ++;
            BigDecimal space = distancePerSecond.poll();
            distance = distance.add(space);
            if (distance.doubleValue() >= Const.STRING_DISTANCE)
                break;
        }
        return second;
    }
}

