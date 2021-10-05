package baseball.utils;

public class View {
    private static final String ERROR = "[ERROR]";

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR + " " + message);
    }
}
