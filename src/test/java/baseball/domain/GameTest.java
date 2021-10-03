package baseball.domain;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GameTest {
    @Mock
    private Computer mockComputer;

    @Mock
    private Player mockPlayer;

    @InjectMocks
    private Game game;

    public GameTest() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("게임 시작 메서드 제대로 실행되는지 확인")
    @Test
    void play_success() {
        String numbers = "123";

        playGiven(numbers);
        game.play();
        playThenVerify(numbers);
    }

    private void playGiven(String numbers) {
        doNothing().when(mockComputer).createBaseBallNumber();
        doNothing().when(mockComputer).askNumber();

        when(mockPlayer.inputNumbers()).thenReturn(numbers);
        when(mockComputer.isValidBaseballNumber(numbers)).thenReturn(true);
        when(mockComputer.checkAnswer(numbers)).thenReturn(true);
    }

    private void playThenVerify(String numbers) {
        verify(mockComputer).createBaseBallNumber();
        verify(mockComputer).askNumber();
        verify(mockPlayer).inputNumbers();
        verify(mockComputer).isValidBaseballNumber(numbers);
        verify(mockComputer).checkAnswer(numbers);
    }
}