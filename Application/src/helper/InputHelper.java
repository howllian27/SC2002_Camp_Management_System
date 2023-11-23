package helper;

import model.Faculty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private static boolean isValidDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setLenient(false);

        try {
            Date parsedDate = simpleDateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

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
        scanner.nextLine();
        return nextInt;
    }

    public static boolean nextBoolean() {
        while(!scanner.hasNextBoolean()) {
            LoggerHelper.Warning(LoggerHelper.LOGGER_WARNING_BOOLEAN);
            scanner.nextLine();
        }
        boolean nextBoolean = scanner.nextBoolean();
        scanner.nextLine();
        return nextBoolean;
    }

    public static Date nextDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        while(true) {
            String dateString = scanner.nextLine();
            try {
                return dateFormat.parse(dateString);
            } catch (ParseException e) {
                LoggerHelper.Warning(LoggerHelper.LOGGER_WARNING_DATE);
            }
        }
    }

    public static Faculty nextFaculty() {
        do {
            String facultyString = scanner.nextLine();
            try {
                return Faculty.valueOf(facultyString);
            } catch (IllegalArgumentException e) {
                LoggerHelper.Warning(LoggerHelper.LOGGER_WARNING_FACULTY);
            }
        } while(true);
    }
}
