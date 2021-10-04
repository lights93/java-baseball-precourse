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
        String numbers = getValidNumber();
        if (!computer.checkAnswer(numbers)) {
            play();
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
}
