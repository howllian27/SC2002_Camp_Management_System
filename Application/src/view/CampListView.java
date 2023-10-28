package view;

import java.util.List;

public class CampListView {
    public void displayCamps(List<Camp> camps) {
        
        System.out.println("List of Camps:");
        for (Camp camp : camps) {
            System.out.println("Camp Name: " + camp.getName());
            System.out.println("Dates: " + camp.getDates());
            System.out.println("Location: " + camp.getLocation());
            System.out.println("Remaining Slots: " + camp.getRemainingSlots());
            System.out.println();
        }
    }
}
