package view;

import java.util.Arrays;
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
    /* 
     * Displays a list of all available camps by printing their names.
    */
    public void displayCampsStudentMenu(){
        System.out.println("\n+------------------------------------------------------------+");
        System.out.println("|                                                             |");
        System.out.println("|                    LIST OF CAMPS YOU ARE IN                 |");
        System.out.println("|                                                             |");
        System.out.println("|                                                             |");
        System.out.println("|        To return to the main menu, simply enter '0'.        |");
        System.out.println("|                                                             |");
        System.out.println("+------------------------------------------------------------+\n");
    }

    /***
     * Displays a list of all available camps by printing their names.
     */
    public void displayCampsStaffMenu(){
        System.out.println("\n+------------------------------------------------------------+");
        System.out.println("|                                                             |");
        System.out.println("|                  LIST OF CAMPS You Manage                   |");
        System.out.println("|                                                             |");
        System.out.println("|                                                             |");
        System.out.println("|        To return to the main menu, simply enter '0'.        |");
        System.out.println("|                                                             |");
        System.out.println("+------------------------------------------------------------+\n");
    }
    
    /**
     * Displays a list of all available camps by printing their names.
     * @param camps A list of Camp objects containing the available camps to be displayed.
     */
    public void displayCampsForStudent(List<Camp> camps) {
        //Displays a list of all available camps.
        displayCampsStudentMenu();
        System.out.println("List of Camps:");
        System.out.println();
        int count = 1;
        for (Camp camp : camps) {
            System.out.println(count + ". " + "Camp Name: " + camp.getName());
            System.out.println("    " + "Total Slots: " + camp.getTotalSlots());
            System.out.println("    " + "Remaining Slots: " + camp.getRemainingSlots());
            System.out.println("    " + "Remaining Committee Slots: " + camp.getCommitteeSlots());
            count++;
        }
    }

    /**
     * Displays a list of available camps for staff users.
     *
     * @param camps A list of {@code Camp} objects containing the available camps to be displayed.
     */
    public void displayCampsForStaff(List<Camp> camps){
        displayCampsStaffMenu();
        System.out.println("List of Camps:");
        System.out.println();
        int count = 1;
        for (Camp camp : camps) {
            System.out.println(count + ". " + "Camp Name: " + camp.getName());
            System.out.println("    " + "Total Slots: " + camp.getTotalSlots());
            System.out.println("    " + "Remaining Slots: " + camp.getRemainingSlots());
            System.out.println("    " + "Remaining Committee Slots: " + camp.getCommitteeSlots());
            count++;
        }
    }

    /**
     * Displays a detailed list of camps for users, including additional information.
     *
     * @param camps A list of {@code Camp} objects containing the camps to be displayed in detail.
     */
    public void displayDetailedCamps(List<Camp> camps) {
                System.out.println("\n+------------------------------------------------------------+");
                System.out.println("|                                                             |");
                System.out.println("|         LIST OF CAMPS (with details) YOU ARE IN             |");
                System.out.println("|                                                             |");
                System.out.println("|                                                             |");
                System.out.println("|        To return to the main menu, simply enter '0'.        |");
                System.out.println("|                                                             |");
                System.out.println("+------------------------------------------------------------+\n");  
            System.out.println("List of Camps:");
            System.out.println();
            int count = 1;
            for (Camp camp : camps){
                System.out.println(count + ". " + "Camp Name: " + camp.getName()); 
                System.out.println("    " + "Dates: " + Arrays.toString(camp.getDates()));
                System.out.println("    " + "Registration closing date: " + camp.getClosingDate());
                System.out.println("    " + "Location: " + camp.getLocation());
                System.out.println("    " + "Total Slots: " + camp.getTotalSlots());
                System.out.println("    " + "Remaining Slots: " + camp.getRemainingSlots());
                System.out.println("    " + "Description: " + camp.getDescription());
                System.out.println("    " + "Staff In Charge: " + camp.getInCharge().getName());
            }   
                
    }
}
