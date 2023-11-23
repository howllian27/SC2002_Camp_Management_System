
package helper;

/**
 * Utility class for logging messages with color-coded output.
 * Supports logging error, warning, and success messages in different colors.
 *
 * @author Shun Jie
 * @version 1.0
 */
public class LoggerHelper {
    public static final String LOGGER_WARNING_INTEGER = "Please enter a valid number.";
    public static final String LOGGER_WARNING_BOOLEAN = "Please enter a valid boolean.";
    public static final String LOGGER_WARNING_DATE = "Please enter a valid date.";
    public static final String LOGGER_WARNING_FACULTY = "Please enter a valid faculty.";

    // ANSI escape codes for text colors
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    /**
     * Logs an error message in red color.
     *
     * @param errorMessage The error message to be logged.
     */
    public static void Error(String errorMessage) {
        print(RED + errorMessage);
    }

    /**
     * Logs a warning message in yellow color.
     *
     * @param warningMessage The warning message to be logged.
     */
    public static void Warning(String warningMessage) {
        print(YELLOW + warningMessage);
    }

    /**
     * Logs a success message in green color.
     *
     * @param message The success message to be logged.
     */
    public static void Success(String message) {
        print(GREEN + message);
    }

    //Private method to print the message with color and reset
    private static void print(String message) {
        System.out.println(message + RESET);
    }

    // Clears the terminal based on the operating system
    public static void clearScreen() {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix-like systems (Linux, macOS)
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Error while trying to clear the terminal: " + e.getMessage());
        }
    }
}
