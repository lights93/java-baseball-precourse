package baseball.domain;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import baseball.validator.BaseballNumberValidator;
import nextstep.utils.Randoms;

class GameTest {
    private final Player mockPlayer;
    private final Game game;

    public GameTest() {
        mockPlayer = mock(Player.class);
        game = new Game(mockPlayer, new BaseballNumberValidator());
    }

    @DisplayName("게임 시작부터 종료까지 제대로 작동하는지 확인")
    @Test
    public void init_success() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            givenInit(mockRandoms);

            game.init();

            thenVerifyInit();
        }
    }

    private void givenInit(MockedStatic<Randoms> mockRandoms) {
        mockRandoms
            .when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
            .thenReturn(1, 2, 3);

        when(mockPlayer.inputNumbers()).thenReturn("123");
        when(mockPlayer.inputRestartOrEnd()).thenReturn("2");
    }

    private void thenVerifyInit() {
        verify(mockPlayer, times(1)).inputNumbers();
        verify(mockPlayer, times(1)).inputRestartOrEnd();
    }
}
