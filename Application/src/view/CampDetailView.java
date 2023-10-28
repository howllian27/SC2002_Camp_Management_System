package view;
import model.Camp;
/**
 * The CampDetailView class is responsible for displaying the details of a specific camp.
 * It provides a method to display information such as camp ID, name, dates, location, slots, and more.
 */

public class CampDetailView {
    /**
     * Displays the details of a specific camp.
     *
     * @param camp The Camp object containing the camp's information.
     */

    public void displayCampDetails(Camp camp) {
        
        System.out.println("Camp Details");
        System.out.println("Camp ID: " + camp.getCampID());
        System.out.println("Camp Name: " + camp.getName());
        System.out.println("Dates: " + camp.getDates());
        System.out.println("Registration closing date: " + camp.getregistrationClosingDate());
        System.out.println("Location: " + camp.getLocation());
        System.out.println("Total Slots: " + camp.getTotalSlots());
        System.out.println("Remaining Slots: " + camp.getRemainingSlots());
        System.out.println("Description: " + camp.getDescription());
        System.out.println("Staff In Charge: " + camp.getstaffInCharge());
        
    }
}
