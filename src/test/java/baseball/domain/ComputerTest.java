package baseball.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import baseball.validator.BaseballNumberValidator;

class ComputerTest {
    private Computer computer = new Computer(new BaseballNumberValidator());

    @DisplayName("Computer의 createBaseBallNumber 메소드 테스트")
    @Test
    void createBaseBallNumber_success() {
        computer.createBaseBallNumber();
    }

    @DisplayName("야구게임숫자를 입력받았을 때 알맞은 검증결과를 반환하는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"129, true", ", false", "12, false", "1234, false", "012, false", "12a, false", "121, false"})
    void isValidBaseballNumber_success(String baseballNumber, boolean expected) {
        assertThat(computer.isValidBaseballNumber(baseballNumber)).isEqualTo(expected);
    }
}