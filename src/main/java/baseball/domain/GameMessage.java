package baseball.domain;

public enum GameMessage {
    ASK_NUMBER("숫자를 입력해주세요:"),
    CORRECT_ANSWER("개의 숫자를 모두 맞히셨습니다! 게임 끝"),
    ASK_RESTART_OR_END("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

    private final String message;

    GameMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
