package baseball.exception;

public enum BaseballGameErrorCode {
    EMPTY("입력된 값이 없습니다."),
    INVALID_SIZE("잘못된 길이의 입력입니다."),
    INVALID_NUMBER("입력 가능한 숫자가 아닙니다."),
    DUPLICATE_NUMBER("중복된 숫자가 존재합니다.");

    private final String message;
    private static final String ERROR = "[ERROR]";

    BaseballGameErrorCode(String message) {
        this.message = ERROR + " " + message;
    }

    public String getMessage() {
        return message;
    }
}
