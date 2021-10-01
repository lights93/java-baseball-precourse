package nextstep;

import java.util.HashMap;
import java.util.Map;

import nextstep.utils.Randoms;

public class BaseBallNumber {
    private Map<Integer, Integer> numberIndexMap;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;

    public BaseBallNumber(int maxSize) {
        this.numberIndexMap = new HashMap<>();
        while (numberIndexMap.size() < maxSize) {
            pickNumberIfUnique();
        }
    }

    private void pickNumberIfUnique() {
        int num = Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
        if (numberIndexMap.containsKey(num)) {
            return;
        }

        numberIndexMap.put(num, numberIndexMap.size());
    }

    public boolean containsNumber(int number) {
        return numberIndexMap.containsKey(number);
    }

    public int getIndex(int number) {
        return numberIndexMap.getOrDefault(number, -1);
    }
}
