package controller;

import java.util.List;
import java.util.Map;

import database.CampDB;
import model.Camp;
import model.CampInformation;
import view.CampListView;
import view.CampDetailView;

/**
 * The CampOperationsController class is responsible for handling operations related to camp information.
 * It interacts with the CampDB for database operations and uses CampListView and CampDetailView for displaying relevant information.
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-10-28
 */
public class CampOperationsController implements BaseController {

    CampDB campDB;
    private CampListView campListView;
    private CampDetailView campDetailView;

    // Constructor
    public CampOperationsController() {
        setMasterVariables();
    }

    //
    @Override
    public void setMasterVariables() {   
        this.campDB = CampDB.getInstance();
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
        Camp updatedCamp = campDB.getCamp(campID);
        updatedCamp.setName(updatedDetails.campName);
        updatedCamp.setDates(updatedDetails.dates);
        updatedCamp.setClosingDate(updatedDetails.registrationClosingDate);
        updatedCamp.setFaculty(updatedDetails.faculty);
        updatedCamp.setLocation(updatedDetails.location);
        updatedCamp.setCommitteeSlots(updatedDetails.committeeSlots);
        updatedCamp.setDescription(updatedDetails.description);
        updatedCamp.setInCharge(updatedDetails.inCharge);
        campDB.updateCamp(campID, updatedCamp);
    }

    /** 
    * Edit a camp
    * @param campID The camp ID to be edited.
    */ 
    public void deleteCamp(String campID) {
        if (campDB.getCamp(campID) != null) {
            System.out.println("Camp does not exist.");
            return;
        }

        campDB.deleteCamp(campID);
        System.out.println("Camp successfully deleted!");
    }

    /** 
    * View all camp
    * @param userType The user type to be viewed.
    */ 
    public void viewCampsForUserType(String userType) {
        List<Camp> camps = null;
        if (userType.equals("staff")) {
            Map<String, Camp> campMap = campDB.getAllCamps();
            camps = campMap.values().stream().toList();
            campListView.displayCamps(camps);
            return;
        }
        if (camps.isEmpty()) {
            System.out.println("Camp does not exist.");
        } else {
            campListView.displayCamps(camps);
        }
    }

    /** 
    * View camp details
    * @param campID The camp ID to be viewed.
    */ 
    public void viewCampDetails(String campID) {
        Camp camp = campDB.getCamp(campID);
        if (camp == null) {
            System.out.println("Camp does not exist.");
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
            System.out.println("Camp does not exist.");
            return;
        }

        camp.setVisibility(true);
        campDB.updateCamp(campID, camp);
        System.out.println("Camp visibility toggled!");
    }
}
