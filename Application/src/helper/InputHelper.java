package helper;

import java.util.Scanner;

/**
 * Utility class for handling user input.
 * This class provides methods for reading strings and integers from the console,
 * with error handling for integer inputs.
 *
 * @author Shun Jie
 * @version 1.0
 */
public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads the next line of text from the console.
     *
     * @return A String representing the next line of text entered by the user.
     */
    public static String nextLine() {
        return scanner.nextLine();
    }

    /**
     * Reads the next integer from the console, displaying a warning message
     * and prompting for input until a valid integer is entered.
     *
     * @return An integer entered by the user.
     */
    public static int nextInt() {
        while(!scanner.hasNextInt()) {
            LoggerHelper.Warning(LoggerHelper.LOGGER_WARNING_INTEGER);
            scanner.nextLine();
        }
        int nextInt = scanner.nextInt();
        return nextInt;
    }
}
