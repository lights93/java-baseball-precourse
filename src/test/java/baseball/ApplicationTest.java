package baseball;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import nextstep.test.NSTest;
import nextstep.utils.Randoms;

public class ApplicationTest extends NSTest {
    @BeforeEach
    void beforeEach() {
        super.setUp();
    }

    @Test
    void 낫싱() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms
                .when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(1, 3, 5);
            running("246");
            verify("낫싱");
        }
    }

    @Test
    void 게임종료_후_재시작() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(7, 1, 3)
                .thenReturn(5, 8, 9);
            run("713", "1", "597", "589", "2");
            verify("3스트라이크", "게임 끝", "1스트라이크 1볼");
        }
    }

    @Test
    void 잘못된입력값_후_종료() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(1, 2, 3);
            run("", "12", "1234", "12a", "120", "121", "123", "2");
            verify("입력된 값이 없습니다.", "잘못된 길이의 입력입니다.", "잘못된 길이의 입력입니다.",
                "입력 가능한 숫자가 아닙니다.", "입력 가능한 숫자가 아닙니다.", "중복된 숫자가 존재합니다.", "3스트라이크", "게임 끝");
        }
    }

    @Test
    void 모든힌트_확인_후_종료() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(1, 2, 3);
            run("456", "345", "234", "231", "145", "134", "132", "124", "123", "2");
            verify("낫싱", "1볼", "2볼", "3볼", "1스트라이크", "1스트라이크 1볼", "1스트라이크 2볼",
                "2스트라이크", "3스트라이크", "게임 끝");
        }
    }

    @Test
    void 재시작또종료_입력시_잘못된값_입력_후_종료() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(1, 2, 3);
            run("123", "3", "0", "12", "", "2");
            verify("3스트라이크", "게임 끝", "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.",
                "입력 가능한 숫자가 아닙니다.", "입력 가능한 숫자가 아닙니다.", "입력 가능한 숫자가 아닙니다.", "입력 가능한 숫자가 아닙니다.");
        }
    }

    @AfterEach
    void tearDown() {
        outputStandard();
    }

    @Override
    public void runMain() {
        Application.main(new String[] {});
    }
}
