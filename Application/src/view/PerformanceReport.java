package view;

/**
 * The PerformanceReport class is responsible for displaying the camp performance report to staff.
 * It provides a method to display a performance report to the user.
 *
 * @author Cheng Lin
 * @version 1.0
 */

public class PerformanceReport {

    /**
     * Displays a performance report to the user.
     * @param report A string containing the performance report to be displayed.
     */
    public void displayReport(String report) {
                System.out.println("\n+------------------------------------------------------------+");
                System.out.println("|                                                             |");
                System.out.println("|                       PERFORMANCE REPORT                    |");
                System.out.println("|                                                             |");
                System.out.println("|                                                             |");
                System.out.println("|        To return to the main menu, simply enter '0'.        |");
                System.out.println("|                                                             |");
                System.out.println("+------------------------------------------------------------+\n");
        System.out.println("Displaying performance report:");
        System.out.println(report);
    }
}
