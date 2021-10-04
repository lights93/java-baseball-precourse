package baseball.domain;

public class Game {
    private final Computer computer;
    private final Player player;

    public Game(Computer computer, Player player) {
        this.computer = computer;
        this.player = player;
    }

    public void init() {
        computer.createBaseBallNumber();
        this.play();
    }

    private void play() {
        if (!computer.checkAnswer(getValidNumber())) {
            play();
            return;
        }

        if (computer.isRestart(getValidGameStatus())) {
            init();
        }
    }

    private String getValidNumber() {
        computer.askNumber();
        String numbers = player.inputNumbers();
        if (computer.isValidBaseballNumber(numbers)) {
            return numbers;
        }

        return getValidNumber();
    }

    private String getValidGameStatus() {
        computer.askRestartOrEnd();
        String restartOrEnd = player.inputRestartOrEnd();
        if (computer.isValidGameStatusInput(restartOrEnd)) {
            return restartOrEnd;
        }

        return getValidGameStatus();
    }
}
