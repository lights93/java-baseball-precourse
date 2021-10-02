package baseball.domain;

import baseball.exception.BaseballNumberException;
import baseball.validator.BaseballNumberValidator;

public class Computer {
    private static final int MAX_SIZE = 3;
    private final BaseballNumberValidator validator;
    private BaseBallNumber baseBallNumber;

    public Computer(BaseballNumberValidator validator) {
        this.validator = validator;
    }

    public void createBaseBallNumber() {
        this.baseBallNumber = new BaseBallNumber(MAX_SIZE);
    }

    public void askNumber() {
        System.out.println("숫자를 입력해주세요:");
    }

    public boolean isValidBaseballNumber(String baseballNumber) {
        try {
            validator.checkValidBaseball(baseballNumber);
        } catch (BaseballNumberException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
