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
        HintResult hintResult = new HintResult(baseballAnswer, inputUntilNumbersIsValid());
        View.printMessage(hintResult.makeHintString());
        if (!hintResult.isAllStrike(MAX_SIZE)) {
            findAnswer();
        }
    }

    private String inputUntilNumbersIsValid() {
        String numbers = inputNumbers();
        try {
            validator.checkValidBaseballNumber(numbers);
        } catch (BaseballGameException e) {
            View.printErrorMessage(e.getMessage());
            return inputUntilNumbersIsValid();
        }
        return numbers;
    }

    private String inputNumbers() {
        View.printMessage(GameMessage.ASK_NUMBER.getMessage());
        return player.inputNumbers();
    }

    private void processRestartOrEnd() {
        GameStatus gameStatus = inputUntilGameStatusIsValid();
        if (gameStatus.isRestart()) {
            init();
        }
    }

    private GameStatus inputUntilGameStatusIsValid() {
        try {
            return GameStatus.findByNumber(inputGameStatus());
        } catch (BaseballGameException e) {
            View.printErrorMessage(e.getMessage());
            return inputUntilGameStatusIsValid();
        }
    }

    private String inputGameStatus() {
        View.printMessage(GameMessage.ASK_RESTART_OR_END.getMessage());
        return player.inputRestartOrEnd();
    }
}
