package baseball.domain;

public class Game {
    private final Computer computer;
    private final Player player;
    private boolean isRunning = true;

    public Game(Computer computer, Player player) {
        this.computer = computer;
        this.player = player;
    }

    public void play() {
        computer.createBaseBallNumber();
        while (isRunning) {
            String numbers = getValidNumber();
            isRunning = !computer.checkAnswer(numbers);
        }
    }

    private String getValidNumber() {
        computer.askNumber();
        String numbers = player.inputNumbers();
        while (!computer.isValidBaseballNumber(numbers)) {
            computer.askNumber();
            numbers = player.inputNumbers();
        }

        return numbers;
    }
}
