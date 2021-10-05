package baseball;

import baseball.domain.Game;
import baseball.domain.Player;
import baseball.validator.BaseballNumberValidator;

public class Application {
    public static void main(String[] args) {
        BaseballNumberValidator validator = new BaseballNumberValidator();
        Player player = new Player();
        Game game = new Game(player, validator);
        game.init();
    }
}
