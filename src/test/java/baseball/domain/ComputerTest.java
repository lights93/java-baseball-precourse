package baseball.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

import baseball.validator.BaseballNumberValidator;
import nextstep.utils.Randoms;

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
    void isValidBaseballNumber_success(String inputNumber, boolean expected) {
        assertThat(computer.isValidBaseballNumber(inputNumber)).isEqualTo(expected);
    }

    @DisplayName("정답을 확인했을 때 올바른 결과를 출력하는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"123, true",
        "124, false", "321, false", "134, false", "156, false", "231, false", "234, false", "891, false", "456, false"})
    void checkAnswer_success(String inputNumber, boolean expected) {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms
                .when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(1, 2, 3);

            computer.createBaseBallNumber();
            assertThat(computer.checkAnswer(inputNumber)).isEqualTo(expected);
        }
    }

    @Test
    void checkAnswer_throwsNullPointerException() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> computer.checkAnswer("123"))
            .withMessage("숫자 생성이 안 되어 있습니다.");
    }

    @DisplayName("재시작 또는 종료 입력값 유효성 검증 확인")
    @ParameterizedTest
    @CsvSource(value = {"1, true", "2, true", "3, false", "0, false", "12, false", ", false"})
    void isValidGameStatusInput_success(String input, boolean expected) {
        assertThat(computer.isValidGameStatusInput(input)).isEqualTo(expected);
    }

    @DisplayName("재시작인지 종료인지 제대로 확인하는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1, true", "2, false"})
    void isRestart(String input, boolean expected) {
        assertThat(computer.isRestart(input)).isEqualTo(expected);
    }
}
