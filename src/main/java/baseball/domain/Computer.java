package baseball.domain;

import java.util.Objects;

import baseball.exception.BaseballNumberException;
import baseball.validator.BaseballNumberValidator;

public class Computer {
    private static final int MAX_SIZE = 3;
    private final BaseballNumberValidator validator;
    private BaseballNumber baseballNumber;

    public Computer(BaseballNumberValidator validator) {
        this.validator = validator;
    }

    public void createBaseBallNumber() {
        this.baseballNumber = new BaseballNumber(MAX_SIZE);
    }

    public void askNumber() {
        System.out.println("숫자를 입력해주세요:");
    }

    public boolean isValidBaseballNumber(String inputNumber) {
        try {
            validator.checkValidBaseball(inputNumber);
        } catch (BaseballNumberException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean checkAnswer(String inputNumber) {
        Objects.requireNonNull(baseballNumber, "숫자 생성이 안 되어 있습니다.");
        HintResult hintResult = new HintResult(baseballNumber, inputNumber);
        if (hintResult.getStrikeCount() == MAX_SIZE) {
            this.notifyEnd();
            return true;
        }
        giveHint(hintResult);
        return false;
    }

    private void notifyEnd() {
        System.out.println(MAX_SIZE + "개의 숫자를 모두 맞히셨습니다! 게임끝");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    }

    private void giveHint(HintResult hintResult) {
        System.out.println(hintResult.makeHintString());
    }
}
