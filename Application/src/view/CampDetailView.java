package view;
import model.Camp;

public class CampDetailView {
    public void displayCampDetails(Camp camp) {
        // Display the details of a specific camp
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
