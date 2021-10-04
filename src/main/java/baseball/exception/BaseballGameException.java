package baseball.exception;

public class BaseballGameException extends RuntimeException {
    private final BaseballGameErrorCode errorCode;

    public BaseballGameException(String message, BaseballGameErrorCode errorCode) {
        super(errorCode.getMessage() + " " + message);
        this.errorCode = errorCode;
    }

    public BaseballGameException(BaseballGameErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseballGameErrorCode getErrorCode() {
        return errorCode;
    }
}
