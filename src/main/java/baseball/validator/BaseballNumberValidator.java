package baseball.validator;

import java.util.HashSet;
import java.util.Set;

import baseball.exception.BaseballGameErrorCode;
import baseball.exception.BaseballGameException;

public class BaseballNumberValidator {
    private static final int SIZE = 3;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;

    public void checkValidBaseballNumber(String numbers) {
        checkBaseballNumberIsNotEmpty(numbers);
        checkBaseballNumberSize(numbers);
        for (char numChar : numbers.toCharArray()) {
            checkIsValidBaseballNumber(numChar);
        }
        checkNotContainsDuplicateNumber(numbers);
    }

    private void checkBaseballNumberIsNotEmpty(String numbers) {
        if (numbers == null || numbers.trim().isEmpty()) {
            throw new BaseballGameException(BaseballGameErrorCode.EMPTY);
        }
    }

    private void checkBaseballNumberSize(String numbers) {
        if (numbers.length() != SIZE) {
            String description = SIZE + "개의 숫자를 입력해주세요.";
            throw new BaseballGameException(description, BaseballGameErrorCode.INVALID_SIZE);
        }
    }

    private void checkIsValidBaseballNumber(char numChar) {
        int num = numChar - '0';
        if (num < MIN_NUMBER || num > MAX_NUMBER) {
            String description = MIN_NUMBER + "에서 " + MAX_NUMBER + " 사이의 숫자를 입력해주세요.";
            throw new BaseballGameException(description, BaseballGameErrorCode.INVALID_NUMBER);
        }
    }

    private void checkNotContainsDuplicateNumber(String numbers) {
        Set<Character> set = new HashSet<>();
        for (char numChar : numbers.toCharArray()) {
            checkDuplicateNumber(set, numChar);
            set.add(numChar);
        }
    }

    private void checkDuplicateNumber(Set<Character> set, char numChar) {
        if (set.contains(numChar)) {
            String description = "중복된 숫자: " + numChar;
            throw new BaseballGameException(description, BaseballGameErrorCode.DUPLICATE_NUMBER);
        }
    }
}
