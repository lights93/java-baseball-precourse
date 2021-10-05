package baseball.domain;

import java.util.HashMap;
import java.util.Map;

public class HintResult {
    private final Map<Hint, Integer> hintCountMap;
    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";

    public HintResult(BaseballAnswer baseballAnswer, String numbers) {
        hintCountMap = initHintCountMap();

        char[] numChars = numbers.toCharArray();
        for (int index = 0; index < numChars.length; index++) {
            int num = numChars[index] - '0';
            Hint hint = findHint(baseballAnswer, num, index);
            hintCountMap.compute(hint, (key, count) -> count + 1);
        }
    }

    private Map<Hint, Integer> initHintCountMap() {
        Map<Hint, Integer> hintCountMap = new HashMap<>();
        for (Hint value : Hint.values()) {
            hintCountMap.put(value, 0);
        }

        return hintCountMap;
    }

    private Hint findHint(BaseballAnswer baseBallAnswer, int num, int index) {
        if (baseBallAnswer.getIndex(num) == index) {
            return Hint.STRIKE;
        }
        if (baseBallAnswer.containsNumber(num)) {
            return Hint.BALL;
        }
        return Hint.NOTHING;
    }

    public boolean isAllStrike(int size) {
        return hintCountMap.get(Hint.STRIKE) == size;
    }

    public String makeHintString() {
        StringBuilder sb = new StringBuilder();
        for (Hint hint : Hint.values()) {
            sb.append(makeHintString(hint)).append(SPACE);
        }
        if (sb.toString().trim().isEmpty()) {
            return Hint.NOTHING.getFieldName();
        }
        return sb.toString().trim();
    }

    private String makeHintString(Hint hint) {
        int count = hintCountMap.get(hint);
        if (count > 0 && hint != Hint.NOTHING) {
            return count + hint.getFieldName();
        }
        return EMPTY_STRING;
    }

}
