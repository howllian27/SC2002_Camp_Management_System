package view;

import java.util.Arrays;
import java.util.List;
import model.Camp;
import model.Student;

/**
 * The {@code CampListView} class is responsible for displaying a list of available camps.
 * It provides a method to display the names of camps within the provided list.
 *
 * @author Cheng Lin
 * @version 1.0
 */

public class CampListView {
    /* 
     * Displays a list of all available camps by printing their names.
    */
    public static void displayCampsStudentMenu(){
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
    public static void displayCampsStaffMenu(){
        System.out.println("\n+------------------------------------------------------------+");
        System.out.println("|                                                             |");
        System.out.println("|                  LIST OF CAMPS You Manage                   |");
        System.out.println("|                                                             |");
        System.out.println("|                                                             |");
        System.out.println("|        To return to the main menu, simply enter '0'.        |");
        System.out.println("|                                                             |");
        System.out.println("+------------------------------------------------------------+\n");
    }


    public static void displayAllCampsStaffMenu(){
        System.out.println("\n+------------------------------------------------------------+");
        System.out.println("|                                                             |");
        System.out.println("|                      LIST OF ALL CAMPS                      |");
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
    public static void displayCampsForStudent(List<Camp> camps) {
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
     * Displays a list of all available camps by printing their names.
     * @param camps A list of Camp objects containing the camps they registered for.
     * @param student A student object to get role of student in their registered camps. 
     */
    public static void displayRegCampsForStudent(List<Camp> camps, Student student) {
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
            if (camp == student.getRegisteredCommitteeCamp()){
                System.out.println("    " + "Role: Camp Committee");
            }else{
                System.out.println("    " + "Role: Participant");
            }
            count++;
        }
    }

    /**
     * Displays a list of available camps for staff users.
     *
     * @param camps A list of {@code Camp} objects containing the available camps to be displayed.
     */
    public static void displayCampsForStaff(List<Camp> camps, boolean allCamps){
        if (allCamps){
            displayAllCampsStaffMenu();
        } else {
            displayCampsStaffMenu();
        }
        System.out.println("List of Camps:");
        System.out.println();
        int count = 1;
        for (Camp camp : camps) {
            System.out.println(count + ". " + "Camp Name: " + camp.getName());
            System.out.println("   " + "Total Slots: " + camp.getTotalSlots());
            System.out.println("   " + "Remaining Slots: " + camp.getRemainingSlots());
            System.out.println("   " + "Remaining Committee Slots: " + camp.getCommitteeSlots());
            count++;
        }
    }

    /**
     * Displays the details of a specific camp.
     * @param camp The Camp object containing the camp's information.
     */
    public static void displayCampDetails(Camp camp) {
        System.out.println("\n+------------------------------------------------------------+");
        System.out.println("|                                                             |");
        System.out.println("|                    CAMP DETAILS PORTAL                      |");
        System.out.println("|                                                             |");
        System.out.println("|                                                             |");
        System.out.println("|        To return to the main menu, simply enter '0'.        |");
        System.out.println("|                                                             |");
        System.out.println("+------------------------------------------------------------+\n");
        System.out.println("Camp Details");
        System.out.println("Camp Name: " + camp.getName());
        System.out.println("Dates: " + Arrays.toString(camp.getDates()));
        System.out.println("Registration closing date: " + camp.getClosingDate());
        System.out.println("Location: " + camp.getLocation());
        System.out.println("Total Slots: " + camp.getTotalSlots());
        System.out.println("Remaining Slots: " + camp.getRemainingSlots());
        System.out.println("Description: " + camp.getDescription());
        System.out.println("Staff In Charge: " + camp.getInCharge().getName());
        System.out.println("Visibility: " + camp.getVisibility() + "\n");
    }
}
