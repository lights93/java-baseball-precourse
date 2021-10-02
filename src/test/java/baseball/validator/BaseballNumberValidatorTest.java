package baseball.validator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import baseball.exception.BaseBallNumberErrorCode;
import baseball.exception.BaseballNumberException;

class BaseballNumberValidatorTest {
    private BaseballNumberValidator baseballNumberValidator = new BaseballNumberValidator();

    @DisplayName("올바른 야구게임숫자를 입력 받았을 때 성공하는지 테스트")
    @Test
    void checkValidBaseball_success() {
        baseballNumberValidator.checkValidBaseball("129");
    }

    @DisplayName("잘못된 야구게임숫자를 입력 받았을 때 적절한 에러를 던지는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {
        ", EMPTY",
        "12, INVALID_SIZE", "1234, INVALID_SIZE",
        "012, INVALID_NUMBER", "12a, INVALID_NUMBER",
        "121, DUPLICATE_NUMBER"
    })
    void checkValidBaseball_throwsException(String baseballNumber, String errorCode) {
        BaseBallNumberErrorCode baseBallNumberErrorCode = BaseBallNumberErrorCode.valueOf(errorCode);

        assertThatThrownBy(() -> baseballNumberValidator.checkValidBaseball(baseballNumber))
            .isInstanceOf(BaseballNumberException.class)
            .hasMessageContaining(baseBallNumberErrorCode.getMessage());
    }
}