package homework.taxi.module;

import homework.taxi.exception.KilometerMusBeNonNegativeException;
import homework.taxi.strategy.StrategyConst;
import homework.taxi.strategy.StrategyType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class Distance {

    private LocalDateTime startAt;

    private LinkedList<BigDecimal> distancePerSecond;

    public Distance(LocalDateTime startAt, LinkedList<BigDecimal> distancePerSecond) {
        this.startAt = startAt;
        this.distancePerSecond = distancePerSecond;

        if (distancePerSecond.stream().anyMatch(decimal -> decimal.doubleValue() < 0))
            throw new KilometerMusBeNonNegativeException();
    }

    public List<PeriodDistance> merge() {
        List<PeriodDistance> result = new ArrayList<>();
        addPeriodDistanceAndPlusTime(result, this::startPart);

        boolean isAtNight = isAtNight(startAt);
        while (!distancePerSecond.isEmpty()) {
            if (isAtNight)
                addPeriodDistanceAndPlusTime(result, this::driveAtNight);
            else
                addPeriodDistanceAndPlusTime(result, this::trafficCompensation);
            isAtNight = !isAtNight;
        }

        return result;
    }

    private void addPeriodDistanceAndPlusTime(List<PeriodDistance> result, Supplier<PeriodDistance> supplier) {
        PeriodDistance periodDistance = supplier.get();
        if (!periodDistance.isEmpty()) {
            result.add(periodDistance);
            startAt = startAt.plusSeconds(periodDistance.getSecond());
        }
    }

    private PeriodDistance startPart() {
        BigDecimal distance = getIncrementDistance();
        int partTimeSecond = 0;

        while (!distancePerSecond.isEmpty()) {
            partTimeSecond ++;
            BigDecimal space = distancePerSecond.pop();
            distance = distance.add(space);
            if (distance.doubleValue() >= StrategyConst.STRING_DISTANCE)
                break;
        }
        return new PeriodDistance(partTimeSecond, distance, StrategyType.STARING);
    }

    private PeriodDistance trafficCompensation() {
        BigDecimal distance = getIncrementDistance();
        int partTimeSecond = 0;

        while (!distancePerSecond.isEmpty()) {
            int tempTime = partTimeSecond + 1;
            if (isAtNight(startAt.plusSeconds(tempTime)))
                break;
            partTimeSecond ++;
            BigDecimal space = distancePerSecond.pop();
            distance = distance.add(space);
        }
        return new PeriodDistance(partTimeSecond, distance, StrategyType.TRAFFIC_COMPENSATION);
    }

    private PeriodDistance driveAtNight() {
        BigDecimal distance = getIncrementDistance();
        int partTimeSecond = 0;

        while (!distancePerSecond.isEmpty()) {
            int tempTime = partTimeSecond + 1;
            if (!isAtNight(startAt.plusSeconds(tempTime)))
                break;
            partTimeSecond ++;
            BigDecimal space = distancePerSecond.pop();
            distance = distance.add(space);
        }
        return new PeriodDistance(partTimeSecond, distance, StrategyType.DRIVE_AT_NIGHT);
    }

    private BigDecimal getIncrementDistance() {
        BigDecimal distance = new BigDecimal(0);
        distance = distance.setScale(2, RoundingMode.HALF_UP);
        return distance;
    }

    private boolean isAtNight(LocalDateTime dateTime) {
        LocalDateTime leftTime = LocalDateTime.of(startAt.toLocalDate(), LocalTime.of(23, 0));
        LocalDateTime rightTime = LocalDateTime.of(startAt.toLocalDate().plusDays(1), LocalTime.of(6, 0));
        return dateTime.isAfter(leftTime) && dateTime.isBefore(rightTime);
    }
}
