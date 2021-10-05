package baseball;

import baseball.domain.BaseballAnswer;
import baseball.domain.GameMessage;
import baseball.domain.GameStatus;
import baseball.domain.HintResult;
import baseball.domain.Player;
import baseball.exception.BaseballGameException;
import baseball.utils.View;
import baseball.validator.BaseballNumberValidator;

public class Game {
    private static final int MAX_SIZE = 3;
    private final Player player;
    private final BaseballNumberValidator validator;
    private BaseballAnswer baseballAnswer;

    public Game(Player player, BaseballNumberValidator validator) {
        this.player = player;
        this.validator = validator;
    }

    public void init() {
        baseballAnswer = new BaseballAnswer(MAX_SIZE);
        play();
    }

    private void play() {
        findAnswer();

        View.printMessage(MAX_SIZE + GameMessage.CORRECT_ANSWER.getMessage());
        processRestartOrEnd();
    }

    private void findAnswer() {
        HintResult hintResult = new HintResult(baseballAnswer, getValidNumber());
        View.printMessage(hintResult.makeHintString());
        if (!hintResult.isAllStrike(MAX_SIZE)) {
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
        if (gameStatus.isRestart()) {
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
