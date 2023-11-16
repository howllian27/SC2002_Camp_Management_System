package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import database.CampDB;
import model.Camp;
import model.CampInformation;
import model.Staff;
import model.Student;
import view.CampListView;
import view.CampDetailView;
import view.EditCampView;

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
    private EditCampView editCampView;

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
        this.editCampView = new EditCampView();
    }

    /**
     * Verify if the camp exists
     * 
     * @param campID
     * @param staffID
    */
    public boolean verifyCampOwnership(String campID, Staff staff) {
        Camp camp = campDB.getCamp(campID);
        if (camp == null) {
            System.out.println("Camp does not exist.");
            return false;
        }

        HashMap<String, Camp> createdCamps = staff.getCamps();
        if (!createdCamps.containsKey(campID)) {
            System.out.println("You do not own this camp. \n");
            return false;
        }

        return true;
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
    public void editCamp(String campID) {
        if (campDB.getCamp(campID) == null) {
            System.out.println("Camp does not exist.");
            return;
        }
        Camp updatedCamp = campDB.getCamp(campID);
        updatedCamp = editCampView.editCampInfoView(updatedCamp);

        if (!updatedCamp.getName().equals(campID)){
            campDB.deleteCamp(campID);
            campDB.addCamp(updatedCamp.getName(), updatedCamp);
        } else {
            campDB.updateCamp(campID, updatedCamp);
        }
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
        }
        if (userType.equals("student")) {
            Map<String, Camp> campMap = campDB.getAllCamps();
            camps = campMap.values().stream()
                              .filter(Camp::getVisibility) // Keep only camps where getVisibility is true
                              .collect(Collectors.toList());          
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

    /** 
     * View registered students
     * @param campID The camp ID to be viewed.
    */
    public void viewRegisteredStudents(String campID){
        Camp camp = campDB.getCamp(campID);
        HashMap<String, Student> attendees = camp.getAttendees();

        for (Map.Entry<String, Student> set :
             attendees.entrySet()){
                System.out.println(set.getValue().getName());
        }
    }
}
