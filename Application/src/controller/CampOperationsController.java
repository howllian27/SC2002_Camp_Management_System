package controller;

import database.CampDB;
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

    public CampOperationsController() {
        setMasterVariables();
    }

    @Override
    public void setMasterVariables() {
        this.campDB = new CampDB();
        this.campListView = new CampListView();
        this.campDetailView = new CampDetailView();

    public void createCamp(CampInformation campInformation) {
        if (campInformation.isValid()) {
            campDB.addCamp(campInformation);
            campListView.displayCamps(campInformation);
        } else {
            System.out.println("Invalid camp details.");
        }
    }

    public void editCamp(String campID, CampInformation updatedDetails) {
        if (!campDB.exists(campID)) {
            System.out.println("Camp does not exist.");
            return;
        }

        if (updatedDetails.isValid()) {
            campDB.updateCamp(campID, updatedDetails);
            campDetailView.displayEditSuccess(updatedDetails);
        } else {
            campDetailView.displayEditError("Invalid updated details.");
        }
    }

    public void deleteCamp(String campID) {
        if (!campDB.exists(campID)) {
            campDetailView.displayCampNotFound(campID);
            return;
        }

        campDB.deleteCamp(campID);
        campListView.displayDeletionSuccess(campID);
    }

    public void viewCampsForUserType(Object userType) {
        var camps = campDB.getCampsForUserType(userType);
        if (camps.isEmpty()) {
            campListView.displayNoCampsFound();
        } else {
            campListView.displayCamps(camps);
        }
    }

    public void viewCampDetails(String campID) {
        var camp = campDB.getCampDetails(campID);
        if (camp == null) {
            campDetailView.displayCampNotFound(campID);
        } else {
            campDetailView.displayCampDetails(camp);
        }
    }

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
