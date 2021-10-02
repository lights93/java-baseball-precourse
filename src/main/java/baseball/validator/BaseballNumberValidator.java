package baseball.validator;

import java.util.HashSet;
import java.util.Set;

import baseball.exception.BaseBallNumberErrorCode;
import baseball.exception.BaseballNumberException;

public class BaseballNumberValidator {
    private static final int SIZE = 3;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;

    public void checkValidBaseball(String baseballNumber) {
        checkBaseballNumberIsNotEmpty(baseballNumber);
        checkBaseballNumberSize(baseballNumber);
        for (char numChar : baseballNumber.toCharArray()) {
            checkIsValidBaseballNumber(numChar);
        }
        checkNotContainsDuplicateNumber(baseballNumber);
    }

    private void checkBaseballNumberIsNotEmpty(String baseballNumber) {
        if (baseballNumber == null || baseballNumber.trim().isEmpty()) {
            throw new BaseballNumberException(BaseBallNumberErrorCode.EMPTY);
        }
    }

    private void checkBaseballNumberSize(String baseballNumber) {
        if (baseballNumber.length() != SIZE) {
            String description = SIZE + "개의 숫자를 입력해주세요.";
            throw new BaseballNumberException(description, BaseBallNumberErrorCode.INVALID_SIZE);
        }
    }

    private void checkIsValidBaseballNumber(char numChar) {
        int num = numChar - '0';
        if (num < MIN_NUMBER || num > MAX_NUMBER) {
            String description = MIN_NUMBER + "에서 " + MAX_NUMBER + " 사이의 숫자를 입력해주세요.";
            throw new BaseballNumberException(description, BaseBallNumberErrorCode.INVALID_NUMBER);
        }
    }

    private void checkNotContainsDuplicateNumber(String baseballNumber) {
        Set<Character> set = new HashSet<>();
        for (char numChar : baseballNumber.toCharArray()) {
            checkDuplicateNumber(set, numChar);
            set.add(numChar);
        }
    }

    private void checkDuplicateNumber(Set<Character> set, char numChar) {
        if (set.contains(numChar)) {
            String description = "중복된 숫자: " + numChar;
            throw new BaseballNumberException(description, BaseBallNumberErrorCode.DUPLICATE_NUMBER);
        }
    }
}
