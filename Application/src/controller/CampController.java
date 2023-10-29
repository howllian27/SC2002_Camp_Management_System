package controller;

import database.CampDB;
import model.Camp;
import view.CampListView;
import view.CampDetailView;

/**
 * The CampController class is responsible for handling camp-related operations.
 * It provides methods for viewing all camps, viewing specific camp details, and toggling the visibility of a camp.
 * This class interacts with the CampDB for database operations and uses CampListView and CampDetailView for displaying relevant camp information.
 * 
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-10-28
 */
public class CampController {

    /** The CampDB object for interacting with the camp database. */
    private CampDB campDB = new CampDB();

    /** The CampListView object for displaying a list of camps. */
    private CampListView campListView = new CampListView();

    /** The CampDetailView object for displaying details of a specific camp. */
    private CampDetailView campDetailView = new CampDetailView();

    /**
     * Retrieves and displays a list of all camps from the database.
     */
    public void viewAllCamps() {
        // Assuming CampDB's getAllCamps() returns a List<Camp>
        campListView.displayCamps(campDB.getAllCamps());
    }

    /**
     * Retrieves and displays details of a specific camp.
     * 
     * @param campID The ID of the camp to be viewed.
     */
    public void viewCampDetails(String campID) {
        // Assuming CampDB's getCamp() returns a Camp object
        Camp camp = campDB.getCamp(campID);
        if (camp != null) {
            campDetailView.displayCampDetails(camp);
        } else {
            // Handle error or display a message indicating the camp was not found.
            // This can be done using another method in the CampDetailView or using a general error view.
        }
    }

    /**
     * Toggles the visibility of a camp.
     * 
     * @param campID The ID of the camp whose visibility is to be toggled.
     */
    public void toggleCampVisibility(String campID) {
        // Retrieve the camp from the database
        Camp camp = campDB.getCamp(campID);

        // Toggle the visibility of the camp
        // Placeholder: Assuming Camp model has a boolean attribute 'isVisible'
        camp.isVisible = !camp.isVisible;

        // Update the camp in the database
        campDB.updateCamp(campID, camp);

        // Display the updated camp details
        campDetailView.displayCampDetails(camp);
    }
}