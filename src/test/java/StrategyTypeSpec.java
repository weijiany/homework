import exception.StrategyTypeValueNotExistException;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import strategy.StrategyType;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StrategyTypeSpec {

    @ParameterizedTest
    @MethodSource("params")
    public void should_return_correct_strategy_type(Map.Entry<Integer, StrategyType> entry) throws StrategyTypeValueNotExistException {
        StrategyType strategyType = StrategyType.fromValue(entry.getKey());
        assertEquals(entry.getValue(), strategyType);
    }

    private static Iterator<Map.Entry<Integer, StrategyType>> params() {
        return List.of(Map.entry(1, StrategyType.STARING),
                Map.entry(2, StrategyType.DRIVE_AT_NIGHT),
                Map.entry(3, StrategyType.TRAFFIC_COMPENSATION)).iterator();
    }

    @Test
    public void should_throw_StrategyTypeValueNotExistException_given_value_is_invalid() {
        Exception exception = assertThrows(StrategyTypeValueNotExistException.class, () -> StrategyType.fromValue(Integer.MAX_VALUE));
        assertEquals(StrategyTypeValueNotExistException.MESSAGE, exception.getMessage());
    }
}
