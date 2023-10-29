package view;

import java.util.List;
import model.Camp;

/**
 * The CampListView class is responsible for displaying a list of available camps.
 * It provides a method to display the names of camps within the provided list.
 */

public class CampListView {
    /**
     * Displays a list of all available camps by printing their names.
     *
     * @param camps A list of Camp objects containing the available camps to be displayed.
     */
    public void displayCamps(List<Camp> camps) {
        //Displays a list of all available camps.
        System.out.println("List of Camps:");
        for (Camp camp : camps) {
            System.out.println("Camp Name: " + camp.getName());
            System.out.println();
        }
    }
}
