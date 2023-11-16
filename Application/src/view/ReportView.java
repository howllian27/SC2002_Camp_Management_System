package view;

import java.util.Scanner;

/**
 * The ReportView class is responsible for displaying the camp performance report to staff.
 * It provides a method to display a performance report to the user.
 *
 * @author Cheng Lin
 * @version 1.0
 */

public class ReportView {

    /**
     * Displays a performance report to the user.
     * @param report A string containing the performance report to be displayed.
     */
    public void displayReport() {
                System.out.println("\n+------------------------------------------------------------+");
                System.out.println("|                                                             |");
                System.out.println("|                       GENERATE REPORT                       |");
                System.out.println("|                                                             |");
                System.out.println("|                                                             |");
                System.out.println("|        To return to the main menu, simply enter '0'.        |");
                System.out.println("|                                                             |");
                System.out.println("+------------------------------------------------------------+\n");
        System.out.println("Select the type of report you wish to generate:");
        System.out.println("1. Camp Report");
        System.out.println("2. Camp Performance Report");
        System.out.println("3. Student Enquiry Report");
        
    }
}
