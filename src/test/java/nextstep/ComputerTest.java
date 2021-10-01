package nextstep;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ComputerTest {

    @DisplayName("Computer의 createBaseBallNumber 메소드 테스트")
    @Test
    void createBaseBallNumber_success() {
        Computer computer = new Computer();
        computer.createBaseBallNumber();
    }
}