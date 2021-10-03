package baseball.validator;

import java.util.HashSet;
import java.util.Set;

import baseball.exception.BaseBallNumberErrorCode;
import baseball.exception.BaseballNumberException;

public class BaseballNumberValidator {
    private static final int SIZE = 3;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;

    public void checkValidBaseball(String inputNumber) {
        checkBaseballNumberIsNotEmpty(inputNumber);
        checkBaseballNumberSize(inputNumber);
        for (char numChar : inputNumber.toCharArray()) {
            checkIsValidBaseballNumber(numChar);
        }
        checkNotContainsDuplicateNumber(inputNumber);
    }

    private void checkBaseballNumberIsNotEmpty(String inputNumber) {
        if (inputNumber == null || inputNumber.trim().isEmpty()) {
            throw new BaseballNumberException(BaseBallNumberErrorCode.EMPTY);
        }
    }

    private void checkBaseballNumberSize(String inputNumber) {
        if (inputNumber.length() != SIZE) {
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

    private void checkNotContainsDuplicateNumber(String inputNumber) {
        Set<Character> set = new HashSet<>();
        for (char numChar : inputNumber.toCharArray()) {
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
