package baseball.domain;

import baseball.exception.BaseballGameException;
import baseball.utils.View;
import baseball.validator.BaseballNumberValidator;

public class Game {
    private static final int MAX_SIZE = 3;
    private final Player player;
    private final BaseballNumberValidator validator;
    private BaseballNumber baseballNumber;

    public Game(Player player, BaseballNumberValidator validator) {
        this.player = player;
        this.validator = validator;
    }

    public void init() {
        this.baseballNumber = new BaseballNumber(MAX_SIZE);
        this.play();
    }

    private void play() {
        findAnswer();

        View.printMessage(MAX_SIZE + GameMessage.CORRECT_ANSWER.getMessage());
        processRestartOrEnd();
    }

    private void findAnswer() {
        HintResult hintResult = new HintResult(baseballNumber, getValidNumber());
        View.printMessage(hintResult.makeHintString());
        if (hintResult.getStrikeCount() != MAX_SIZE) {
            findAnswer();
        }
    }

    private String getValidNumber() {
        View.printMessage(GameMessage.ASK_NUMBER.getMessage());
        String numbers = player.inputNumbers();
        try {
            validator.checkValidBaseball(numbers);
        } catch (BaseballGameException e) {
            View.printErrorMessage(e.getMessage());
            return getValidNumber();
        }
        return numbers;
    }

    private void processRestartOrEnd() {
        GameStatus gameStatus = GameStatus.findByNumber(getValidGameStatus());
        if (GameStatus.RESTART == gameStatus) {
            init();
        }
    }

    private String getValidGameStatus() {
        View.printMessage(GameMessage.ASK_RESTART_OR_END.getMessage());
        String restartOrEnd = player.inputRestartOrEnd();
        try {
            GameStatus.findByNumber(restartOrEnd);
        } catch (BaseballGameException e) {
            View.printErrorMessage(e.getMessage());
            return getValidGameStatus();
        }
        return restartOrEnd;
    }
}
