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
    private BaseBallNumber mockBaseBallNumber;

    public HintResultTest() {
        MockitoAnnotations.openMocks(this);
        when(mockBaseBallNumber.getIndex(anyInt())).thenReturn(-1);
        when(mockBaseBallNumber.getIndex(1)).thenReturn(0);
        when(mockBaseBallNumber.getIndex(2)).thenReturn(1);
        when(mockBaseBallNumber.getIndex(3)).thenReturn(2);

        when(mockBaseBallNumber.containsNumber(anyInt())).thenReturn(false);
        when(mockBaseBallNumber.containsNumber(1)).thenReturn(true);
        when(mockBaseBallNumber.containsNumber(2)).thenReturn(true);
        when(mockBaseBallNumber.containsNumber(3)).thenReturn(true);
    }

    @DisplayName("getStrikeCount 올바른 개수를 가져오는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"123, 3", "124, 2", "321, 1", "134, 1", "156, 1", "231, 0", "234, 0", "891, 0", "456, 0"})
    void getStrikeCount_success(String inputNumber, int count) {
        HintResult hintResult = new HintResult(mockBaseBallNumber, inputNumber);
        assertThat(hintResult.getStrikeCount()).isEqualTo(count);
    }

    @DisplayName("getBallCount 올바른 개수를 가져오는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"123, 0", "124, 0", "321, 2", "134, 1", "156, 0", "231, 3", "234, 2", "891, 1", "456, 0"})
    void getBallCount_success(String inputNumber, int count) {
        HintResult hintResult = new HintResult(mockBaseBallNumber, inputNumber);
        assertThat(hintResult.getBallCount()).isEqualTo(count);
    }

    @DisplayName("getNothingCount 올바른 개수를 가져오는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"123, 0", "124, 1", "321, 0", "134, 1", "156, 2", "231, 0", "234, 1", "891, 2", "456, 3"})
    void getNothingCount_success(String inputNumber, int count) {
        HintResult hintResult = new HintResult(mockBaseBallNumber, inputNumber);
        assertThat(hintResult.getNothingCount()).isEqualTo(count);
    }
}
