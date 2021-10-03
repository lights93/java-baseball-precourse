package baseball;

import baseball.domain.Computer;
import baseball.domain.Game;
import baseball.domain.Player;
import baseball.validator.BaseballNumberValidator;

public class Application {
    public static void main(String[] args) {
        Computer computer = new Computer(new BaseballNumberValidator());
        Player player = new Player();
        Game game = new Game(computer, player);
        game.play();
    }
}
