package controller;

import view.CampListView;
import view.CampDetailView;
import model.Camp;

import java.util.List;

import database.CampDB;

/**
 * Controller responsible for camp-related operations.
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-10-28
 */
public class CampController {

    private CampListView campListView = new CampListView();
    private CampDetailView campDetailView = new CampDetailView();
    private CampDB campDB = new CampDB();

    /**
     * Displays a list of all available camps.
     */
    public void viewAllCamps() {
        List<Camp> camps = campDB.getAllCamps();
        campListView.displayCamps(camps);
    }

    /**
     * Displays details of a specific camp.
     * @param campID The ID of the camp to be displayed.
     */
    public void displayCampDetails(int campID) {
        Camp camp = campDB.getCamp(campID);
        campDetailView.displayCampDetails(camp);
    }

    /**
     * Toggles the visibility of a specific camp.
     * @param campID The ID of the camp whose visibility is to be toggled.
     */
    public void toggleCampVisibility(int campID) {
        Camp camp = campDB.getCamp(campID);
        if (camp != null) {
            // Assuming Camp has a visibility attribute (boolean)
            camp.setVisibility(!camp.getVisibility());
            campDB.updateCamp(campID, camp);
        }
    }
}
