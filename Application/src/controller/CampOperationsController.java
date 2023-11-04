package controller;

import database.CampDB;
import model.Camp;
import model.CampInformation;
import view.CampListView;
import view.CampDetailView;

/**
 * The CampOperationsController class is responsible for handling operations related to camp information.
 * It interacts with the CampDB for database operations and uses CampListView and CampDetailView for displaying relevant information.
 */
public class CampOperationsController implements BaseController {

    private CampDB campDB;
    private CampListView campListView;
    private CampDetailView campDetailView;

    // Constructor
    public CampOperationsController() {
        setMasterVariables();
    }

    //
    @Override
    public void setMasterVariables() {
        this.campDB = new CampDB();
        this.campListView = new CampListView();
        this.campDetailView = new CampDetailView();
    }

    /** 
    * Create a new camp
    * @param campInformation The camp information to be created.
    */ 
    public void createCamp(CampInformation campInformation) {
        Camp camp = new Camp(campInformation); // Assuming Camp constructor takes CampInformation
        boolean success = campDB.addCamp(camp.getName(), camp);
        if (success) {
            campDetailView.displayCampDetails(camp);
        }
    }

    /** 
    * Edit a camp
    * @param campID The camp ID to be edited.
    * @param updatedDetails The updated details of the camp.
    */ 
    public void editCamp(String campID, CampInformation updatedDetails) {
        if (campDB.getCamp(campID) == null) {
            System.out.println("Camp does not exist.");
            return;
        }

       campDB.updateCamp(campID, updatedDetails);
    }

    /** 
    * Edit a camp
    * @param campID The camp ID to be edited.
    */ 
    public void deleteCamp(String campID) {
        if (!campDB.exists(campID)) {
            campDetailView.displayCampNotFound(campID);
            return;
        }

        campDB.deleteCamp(campID);
        campListView.displayDeletionSuccess(campID);
    }

    /** 
    * View all camp
    * @param userType The user type to be viewed.
    */ 
    public void viewCampsForUserType(Object userType) {
        var camps = campDB.getCampsForUserType(userType);
        if (camps.isEmpty()) {
            campListView.displayNoCampsFound();
        } else {
            campListView.displayCamps(camps);
        }
    }

    /** 
    * View camp details
    * @param campID The camp ID to be viewed.
    */ 
    public void viewCampDetails(String campID) {
        var camp = campDB.getCampDetails(campID);
        if (camp == null) {
            campDetailView.displayCampNotFound(campID);
        } else {
            campDetailView.displayCampDetails(camp);
        }
    }

    /** 
    * Toggle camp visibility
    * @param campID The camp ID to be viewed.
    */ 
    public void toggleCampVisibility(String campID) {
        var camp = campDB.getCamp(campID);
        if (camp == null) {
            campDetailView.displayCampNotFound(campID);
            return;
        }

        camp.setVisible(!camp.isVisible());
        campDB.updateCamp(campID, camp);
        campDetailView.displayVisibilityToggled(camp);
    }
}
