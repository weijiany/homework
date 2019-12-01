package strategy;

import exception.StrategyTypeValueNotExistException;

public enum StrategyType {
    STARING(1), DRIVE_AT_NIGHT(2), TRAFFIC_COMPENSATION(3);

    public int value;

    StrategyType(int value) {
        this.value = value;
    }

    public static StrategyType fromValue(int value) throws StrategyTypeValueNotExistException {
        switch (value) {
            case 1:
                return StrategyType.STARING;
            case 2:
                return StrategyType.DRIVE_AT_NIGHT;
            case 3:
                return StrategyType.TRAFFIC_COMPENSATION;
            default:
                throw new StrategyTypeValueNotExistException();
        }
    }
}
