package homework.taxi.strategy;

public abstract class PriceStrategy {

    protected int second;
    protected double kilometer;

    public PriceStrategy(int second, double kilometer) {
        this.second = second;
        this.kilometer = kilometer;
    }

    public abstract int cost();
}
