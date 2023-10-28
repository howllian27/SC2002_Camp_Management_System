package view;

import java.util.List;
import controller.CampController;
import controller.CommitteeController;

public class CampListView {
    public void displayCamps(List<Camp> camps) {
        
        System.out.println("List of Camps:");
        for (Camp camp : camps) {
            System.out.println("Camp Name: " + camp.getName());
            System.out.println();
        }
    }
}
