package view;

import java.util.Scanner;

/**
 * The {@code ReportView} class is responsible for displaying the camp performance report to staff.
 * It provides a method to display a menu for selecting the type of report to be generated.
 *
 * <p>The user can choose from various report options, such as a general camp report, a camp performance report,
 * and a student enquiry report. The method prompts the user to select the desired report type.
 * To return to the main menu, the user can enter '0'.
 *
 * @author Cheng Lin
 * @version 1.0
 */
public class ReportView {

    /**
     * Displays a menu for selecting the type of report to be generated.
     * The user can choose from options like camp report, camp performance report,
     * and student enquiry report.
     * To return to the main menu, the user can enter '0'.
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
