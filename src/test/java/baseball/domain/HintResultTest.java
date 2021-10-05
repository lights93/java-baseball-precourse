package baseball.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class HintResultTest {
    @Mock
    private BaseballAnswer mockBaseballAnswer;
    private static final int MAX_SIZE = 3;

    public HintResultTest() {
        MockitoAnnotations.openMocks(this);
        when(mockBaseballAnswer.getIndex(anyInt())).thenReturn(-1);
        when(mockBaseballAnswer.containsNumber(anyInt())).thenReturn(false);
        int[] numbersArr = {1, 2, 3};
        for (int number : numbersArr) {
            when(mockBaseballAnswer.getIndex(number)).thenReturn(number - 1);
            when(mockBaseballAnswer.containsNumber(number)).thenReturn(true);
        }
    }

    @DisplayName("getBallCount 올바른 개수를 가져오는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"123, true",
        "124, false", "321, false", "134, false", "156, false", "231, false", "234, false", "891, false", "456, false"})
    void isAllStrike_success(String numbers, boolean expected) {
        HintResult hintResult = new HintResult(mockBaseballAnswer, numbers);
        assertThat(hintResult.isAllStrike(MAX_SIZE)).isEqualTo(expected);
    }

    @DisplayName("각 결과에 맞는 힌트 문자열 생성는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"123, 3스트라이크",
        "124, 2스트라이크",
        "321, 1스트라이크 2볼", "134, 1스트라이크 1볼", "156, 1스트라이크",
        "231, 3볼", "234, 2볼", "891, 1볼",
        "456, 낫싱"})
    void makeHintString_success(String numbers, String hintString) {
        HintResult hintResult = new HintResult(mockBaseballAnswer, numbers);
        assertThat(hintResult.makeHintString()).isEqualTo(hintString);
    }
}
