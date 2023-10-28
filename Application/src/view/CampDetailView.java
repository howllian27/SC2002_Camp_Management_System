package view;
import controller.CampController;

public class CampDetailView {
    public void displayCampDetails(Camp camp) {
        // Display the details of a specific camp
        System.out.println("Camp Details");
        System.out.println("Camp Name: " + camp.getName());
        System.out.println("Dates: " + camp.getDates());
        System.out.println("Location: " + camp.getLocation());
        System.out.println("Remaining Slots: " + camp.getRemainingSlots());
        
    }
}
