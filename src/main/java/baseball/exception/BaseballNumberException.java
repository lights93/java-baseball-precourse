package baseball.exception;

public class BaseballNumberException extends RuntimeException {
    private final BaseBallNumberErrorCode errorCode;

    public BaseballNumberException(String message, BaseBallNumberErrorCode errorCode) {
        super(errorCode.getMessage() + " " + message);
        this.errorCode = errorCode;
    }

    public BaseballNumberException(BaseBallNumberErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseBallNumberErrorCode getErrorCode() {
        return errorCode;
    }
}
