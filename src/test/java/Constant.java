import homework.taxi.strategy.StrategyConst;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Constant {
    public static final BigDecimal START_DISTANCE = BigDecimal.valueOf(StrategyConst.STRING_DISTANCE);
    public static final BigDecimal OVER_SPEED_DISTANCE = BigDecimal.valueOf(120.0 / 60 / 60);
    public static final LocalDateTime NIGHT_TIME = LocalDateTime.parse("2019-11-26T23:00:00");
    public static final LocalDateTime DAY_TIME = LocalDateTime.parse("2019-11-26T08:00:00");
}
