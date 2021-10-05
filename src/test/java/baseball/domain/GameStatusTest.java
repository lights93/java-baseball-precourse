package baseball.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import baseball.exception.BaseballGameErrorCode;
import baseball.exception.BaseballGameException;

class GameStatusTest {

    @DisplayName("게임상태값 올바른 값 입력시 성공하는지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    void findByNumber_success(String input) {
        assertThat(GameStatus.findByNumber(input));
    }

    @DisplayName("게임상태값 잘못된 값 입력시 에러 던지는지 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"0", "3", "12", "21", ""})
    void findByNumber_throwsBaseballGameException(String input) {
        assertThatExceptionOfType(BaseballGameException.class).isThrownBy(() -> GameStatus.findByNumber(input))
            .withMessageContaining(BaseballGameErrorCode.INVALID_NUMBER.getMessage());
    }
}