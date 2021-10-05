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

    public HintResultTest() {
        MockitoAnnotations.openMocks(this);
        when(mockBaseballAnswer.getIndex(anyInt())).thenReturn(-1);
        when(mockBaseballAnswer.getIndex(1)).thenReturn(0);
        when(mockBaseballAnswer.getIndex(2)).thenReturn(1);
        when(mockBaseballAnswer.getIndex(3)).thenReturn(2);

        when(mockBaseballAnswer.containsNumber(anyInt())).thenReturn(false);
        when(mockBaseballAnswer.containsNumber(1)).thenReturn(true);
        when(mockBaseballAnswer.containsNumber(2)).thenReturn(true);
        when(mockBaseballAnswer.containsNumber(3)).thenReturn(true);
    }

    @DisplayName("getStrikeCount 올바른 개수를 가져오는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"123, 3", "124, 2", "321, 1", "134, 1", "156, 1", "231, 0", "234, 0", "891, 0", "456, 0"})
    void getStrikeCount_success(String numbers, int count) {
        HintResult hintResult = new HintResult(mockBaseballAnswer, numbers);
        assertThat(hintResult.getStrikeCount()).isEqualTo(count);
    }

    @DisplayName("getBallCount 올바른 개수를 가져오는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"123, 0", "124, 0", "321, 2", "134, 1", "156, 0", "231, 3", "234, 2", "891, 1", "456, 0"})
    void getBallCount_success(String numbers, int count) {
        HintResult hintResult = new HintResult(mockBaseballAnswer, numbers);
        assertThat(hintResult.getBallCount()).isEqualTo(count);
    }

    @DisplayName("getNothingCount 올바른 개수를 가져오는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"123, 0", "124, 1", "321, 0", "134, 1", "156, 2", "231, 0", "234, 1", "891, 2", "456, 3"})
    void getNothingCount_success(String numbers, int count) {
        HintResult hintResult = new HintResult(mockBaseballAnswer, numbers);
        assertThat(hintResult.getNothingCount()).isEqualTo(count);
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
