package baseball.domain;

public enum Hint {
    STRIKE("스트라이크"), BALL("볼"), NOTHING("낫싱");

    private final String fieldName;

    Hint(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
