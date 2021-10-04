package baseball.domain;

import java.util.HashMap;
import java.util.Map;

import baseball.exception.BaseballGameErrorCode;
import baseball.exception.BaseballGameException;

public enum GameStatus {
    RESTART("1"), END("2");

    private final String number;
    private static final Map<String, GameStatus> numberGameStatusMap;

    static {
        numberGameStatusMap = new HashMap<>();
        for (GameStatus gameStatus : values()) {
            numberGameStatusMap.put(gameStatus.number, gameStatus);
        }

    }

    GameStatus(String number) {
        this.number = number;
    }

    public static GameStatus findByNumber(String number) {
        if (!numberGameStatusMap.containsKey(number)) {
            throw new BaseballGameException(BaseballGameErrorCode.INVALID_NUMBER);
        }

        return numberGameStatusMap.get(number);
    }
}
