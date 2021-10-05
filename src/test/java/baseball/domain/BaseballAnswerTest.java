package baseball.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

import nextstep.utils.Randoms;

class BaseballAnswerTest {
    private BaseballAnswer baseBallAnswer;

    @BeforeEach
    void setUp() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms
                .when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(1, 2, 2, 1, 9);

            baseBallAnswer = new BaseballAnswer(3);
        }
    }

    @DisplayName("containsNumber 메소드 성공 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1, true", "2, true", "9, true", "8, false", "3, false"})
    void containsNumber_success(int num, boolean expected) {
        assertThat(baseBallAnswer.containsNumber(num)).isEqualTo(expected);
    }

    @DisplayName("getIndex 메소드 성공 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1, 0", "2, 1", "9, 2", "8, -1", "3, -1"})
    void getIndex_success(int num, int expected) {
        assertThat(baseBallAnswer.getIndex(num)).isEqualTo(expected);
    }
}