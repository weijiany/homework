package homework.taxi.module;

import homework.taxi.strategy.PriceStrategyFactory;
import homework.taxi.strategy.StrategyType;

import java.math.BigDecimal;
import java.util.Objects;

public class PeriodDistance {

    private int second;
    private BigDecimal distance;
    private StrategyType type;

    public PeriodDistance(int second, BigDecimal distance, StrategyType type) {
        this.second = second;
        this.distance = distance;
        this.type = type;
    }

    public boolean isEmpty() {
        return second == 0 || distance.equals(new BigDecimal(0));
    }

    public int getSecond() {
        return second;
    }

    public int cost() {
        return PriceStrategyFactory.create(second, distance.doubleValue(), type).cost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeriodDistance)) return false;
        PeriodDistance that = (PeriodDistance) o;
        return second == that.second &&
                Objects.equals(distance, that.distance) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(second, distance, type);
    }

    @Override
    public String toString() {
        return "PeriodDistance{" +
                "second=" + second +
                ", distance=" + distance +
                ", type=" + type +
                '}';
    }
}
