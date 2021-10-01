package nextstep;

public class Computer {
    private BaseBallNumber baseBallNumber;
    private static final int MAX_SIZE = 3;

    public void createBaseBallNumber() {
        this.baseBallNumber = new BaseBallNumber(MAX_SIZE);
    }
}
