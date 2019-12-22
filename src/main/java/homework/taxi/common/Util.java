package homework.taxi.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Util {

    public static BigDecimal getDecimalWithPrecision(int precision) {
        BigDecimal distance = new BigDecimal(0);
        distance = distance.setScale(precision, RoundingMode.HALF_UP);
        return distance;
    }
}
