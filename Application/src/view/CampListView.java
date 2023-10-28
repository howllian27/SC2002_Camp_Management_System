package view;

import java.util.List;


public class CampListView {
    public void displayCamps(List<Camp> camps) {
        //Displays a list of all available camps.
    
        System.out.println("List of Camps:");
        for (Camp camp : camps) {
            System.out.println("Camp Name: " + camp.getName());
            System.out.println();
        }
    }
}
