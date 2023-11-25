package controller;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import database.CampDB;
import helper.LoggerHelper;
import model.Camp;
import model.CampInformation;
import model.Staff;
import model.Student;
import model.User;
import view.CampListView;
import view.CampDetailView;
import view.EditCampView;

/**
 * The {@code CampOperationsController } class is responsible for handling operations related to camp information.
 * It interacts with the CampDB for database operations and uses CampListView and CampDetailView for displaying relevant information.
 *
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-10-28
 */
public class CampOperationsController implements BaseController {

    CampDB campDB;
    private EditCampView editCampView;

    // Constructor
    public CampOperationsController() {
        setMasterVariables();
    }

    //
    @Override
    public void setMasterVariables() {   
        this.campDB = CampDB.getInstance();
        this.editCampView = new EditCampView();
    }

    /**
     * Verify if the camp exists and is owned by the staff.
     *
     * @param campID The ID of the camp to verify.
     * @param staff The staff attempting the verification.
     * @return True if the camp exists and is owned by the staff; false otherwise.
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
     * Create a new camp.
     *
     * @param campInformation The camp information to be created.
     * @param staff The staff creating the camp.
     */
    public void createCamp(CampInformation campInformation, Staff staff) {
        Camp camp = new Camp(campInformation); // Assuming Camp constructor takes CampInformation
        boolean success = campDB.addCamp(camp.getName(), camp);
        if (success) {
            staff.addCamp(camp.getName(), camp);
            CampDetailView.displayCampDetails(camp);
        }
    }
    /**
     * Edit a camp.
     *
     * @param campID The camp ID to be edited.
     */
    public void editCamp(String campID, Staff staff) {
        if (campDB.getCamp(campID) == null) {
            System.out.println("Camp does not exist.");
            return;
        }
        Camp updatedCamp = campDB.getCamp(campID);

        if (updatedCamp.getInCharge() != staff) {
            System.out.println("You do not own this camp.");
            return;
        }
        updatedCamp = editCampView.editCampInfoView(updatedCamp);

        if (!updatedCamp.getName().equals(campID)){
            campDB.deleteCamp(campID);
            campDB.addCamp(updatedCamp.getName(), updatedCamp);
        } else {
            campDB.updateCamp(campID, updatedCamp);
        }
    }

    /**
     * Delete a camp.
     *
     * @param campID The camp ID to be deleted.
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
     * View all camps based on user type.
     *
     * @param user The user type to be viewed ("staff" or "student").
     */
    public void viewCampsForUserType(User user) {
        LoggerHelper.clearScreen();
        List<Camp> camps = null;
        if (user instanceof Staff staff) {
            Map<String, Camp> campMap = campDB.getAllCamps();
            camps = campMap.values().stream().toList();
            CampListView.displayCampsForStaff(camps, true);
        }
        if (user instanceof Student student) {
            Map<String, Camp> campMap = campDB.getAllCamps();
            camps = campMap.values().stream()
                              .filter(Camp::getVisibility) // Keep only camps where getVisibility is true
                              .filter(Camp -> Camp.getFaculty() == student.getFaculty()) // Filter camps with correct faculty
                              .collect(Collectors.toList());

            CampListView.displayCampsForStudent(camps);      
        }


        if (camps.isEmpty()) {
            System.out.println("Camp does not exist.");
        } else {        
        }
    }

    /**
     * View specific camps for staff.
     *
     * @param userType The user type to be viewed ("staff" or "student").
     */
    public boolean viewIndivCamps(Staff staff) {
        LoggerHelper.clearScreen();
        List <Camp> camps = staff.getCamps().values().stream().toList();
        if (camps.isEmpty()) {
            System.out.println("You do not own any camps!");
            return false;
        } else {
            CampListView.displayCampsForStaff(camps, false);
            return true;
        }
    }

    /** 
    * View camp details.
     *
    * @param campID The camp ID to be viewed.
    */ 
    public void viewCampDetails(String campID) {
        Camp camp = campDB.getCamp(campID);
        if (camp == null) {
            System.out.println("Camp does not exist.");
        } else {
            CampDetailView.displayCampDetails(camp);
        }
    }

    /**
     * Toggle camp visibility.
     *
     * @param campID The camp ID to be toggled.
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
     * View registered students for a camp.
     *
     * @param campID The camp ID to view registered students.
     */
    public void viewRegisteredStudents(String campID){
        Camp camp = campDB.getCamp(campID);

        if(camp == null) {
            LoggerHelper.Error("Camp not found.");
            return;
        }

        List<Student> attendees = camp.getAttendees().values().stream()
                              .filter(student -> !student.getCampCommitteeMemberStatus())
                              .collect(Collectors.toList());

        List <Student> campCommitteeMembers = camp.getCommittee().values().stream().toList();

        System.out.println("\nList of Camp Committee Members:");

        int campCommitteeCount = 1;
        for (Student student : campCommitteeMembers) {
            System.out.println(campCommitteeCount + ". " + student.getName());
            campCommitteeCount++;
        }

        System.out.println("\nList of Other Camp Attendees:");

        int attendeeCount = 1;
        for (Student attendee : attendees) {
            System.out.println(attendeeCount + ". " + attendee.getName());
            attendeeCount++;
        }
    }
}
