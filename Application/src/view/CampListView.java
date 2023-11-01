package view;

import java.util.List;
import model.Camp;

/**
 * The CampListView class is responsible for displaying a list of available camps.
 * It provides a method to display the names of camps within the provided list.
 *
 * @author Cheng Lin
 * @version 1.0
 */

public class CampListView {

    /**
     * Displays a list of all available camps by printing their names.
     * @param camps A list of Camp objects containing the available camps to be displayed.
     */
    public void displayCamps(List<Camp> camps) {
        //Displays a list of all available camps.
                System.out.println("\n+------------------------------------------------------------+");
                System.out.println("|                                                             |");
                System.out.println("|                    LIST OF CAMPS YOU ARE IN                 |");
                System.out.println("|                                                             |");
                System.out.println("|                                                             |");
                System.out.println("|        To return to the main menu, simply enter '0'.        |");
                System.out.println("|                                                             |");
                System.out.println("+------------------------------------------------------------+\n");
        System.out.println("List of Camps:");
        for (Camp camp : camps) {
            System.out.println("Camp Name: " + camp.getName());
            System.out.println();
        }
    }
}
