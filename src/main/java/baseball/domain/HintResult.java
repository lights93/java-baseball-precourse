package baseball.domain;

import java.util.HashMap;
import java.util.Map;

public class HintResult {
    private final Map<Hint, Integer> hintCountMap;

    public HintResult(BaseballNumber baseballNumber, String inputNumber) {
        this.hintCountMap = initHintCountMap();

        char[] numChars = inputNumber.toCharArray();
        for (int index = 0; index < numChars.length; index++) {
            int num = numChars[index] - '0';
            Hint hint = findHint(baseballNumber, num, index);
            this.hintCountMap.compute(hint, (key, count) -> count + 1);
        }
    }

    private Map<Hint, Integer> initHintCountMap() {
        Map<Hint, Integer> hintCountMap = new HashMap<>();
        for (Hint value : Hint.values()) {
            hintCountMap.put(value, 0);
        }

        return hintCountMap;
    }

    private Hint findHint(BaseballNumber baseBallNumber, int num, int index) {
        if (baseBallNumber.getIndex(num) == index) {
            return Hint.STRIKE;
        }
        if (baseBallNumber.containsNumber(num)) {
            return Hint.BALL;
        }
        return Hint.NOTHING;
    }

    public int getStrikeCount() {
        return this.hintCountMap.get(Hint.STRIKE);
    }

    public int getBallCount() {
        return this.hintCountMap.get(Hint.BALL);
    }

    public int getNothingCount() {
        return this.hintCountMap.get(Hint.NOTHING);
    }
}
