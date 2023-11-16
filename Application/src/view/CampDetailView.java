package view;
import model.Camp;


import java.util.Arrays;

/**
 * The CampDetailView class is responsible for displaying the details of a specific camp.
 * It provides a method to display information such as camp ID, name, dates, location, slots, and more.
 *
 * @author Cheng lin
 * @version 1.0
 */
public class CampDetailView {

    /**
     * Displays the details of a specific camp.
     * @param camp The Camp object containing the camp's information.
     */
    public void displayCampDetails(Camp camp) {
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
        System.out.println("Staff In Charge: " + camp.getInCharge().getName() + "\n");
        
    }
}
