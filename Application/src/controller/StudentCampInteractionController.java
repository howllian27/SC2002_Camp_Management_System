package controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import database.CampDB;
import database.UserDB;
import model.Camp;
import model.Student;

/**
 * The StudentCampInteractionController class is responsible for managing student interactions with camps.
 * It provides methods for students to register for and withdraw from camps.
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-11-02
 */
public class StudentCampInteractionController implements BaseController {

    CampDB campDB;
    UserDB userDB;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public StudentCampInteractionController() {
        setMasterVariables();
    }

    @Override
    public void setMasterVariables() {
        this.campDB = CampDB.getInstance();
        this.userDB = UserDB.getInstance();
    }

    /**
     * Allows a student to register for a camp.
     *
     * @param userID The ID of the student.
     * @param campID The ID of the camp.
     * @param role The role of the student in the camp.
     */
    public void registerForCamp(String userID, String campID, String role) {
        Student student = (Student) userDB.getUser(userID, true);
        Camp camp = campDB.getCamp(campID);
        // Get the current date
        String currentDateStr = dateFormat.format(Calendar.getInstance().getTime());
        Date newCurrentDate = null;
        try{
            newCurrentDate = dateFormat.parse(currentDateStr);
        } catch(ParseException e){
            e.printStackTrace();
        }

        Date campStartDate = camp.getCampInformation().registrationClosingDate;

        // Check if newCurrentDate is before campStartDate
        if (newCurrentDate.before(campStartDate)) {
            HashMap<String, Camp> registeredCamps = student.getRegisteredCamps();
            if (!registeredCamps.containsKey(campID)){
                if (student != null && camp != null) {
                    // Logic to register the student for the camp
                    student.addCamp(campID, camp);
                    if (role.equals("committee") && student.getCampCommitteeMemberStatus() == false) {
                        student.setCampCommitteeMember();
                        student.addCommitteeCamp(camp);
                        camp.addAttendee(userID, student);
                        camp.addCommitteeMember(userID, student);
                        camp.getCampInformation().committeeSlots--;
                    } else {
                        student.addCamp(campID, camp);
                        camp.addAttendee(userID, student);
                    }
                    System.out.println("You are registered!");
                }
            } else {
                System.out.println("You are already registered!");
            }
        } else {
            System.out.println("The camp's registration deadline has passed!");
        }
         
    }

    /**
     * Allows a student to withdraw from a camp.
     *
     * @param userID The ID of the student.
     * @param campID The ID of the camp.
     */
    public void withdrawFromCamp(String userID, String campID) {
        Student student = (Student) userDB.getUser(userID, true);
        Camp camp = campDB.getCamp(campID);
        HashMap<String, Camp> registeredCamps = student.getRegisteredCamps();
        boolean role = student.getCampCommitteeMemberStatus();

        if (registeredCamps.containsKey(campID)){
            if (student != null && camp != null) {
                // Logic to register the student for the camp
                student.removeCamp(campID);
                camp.removeAttendee(userID);
                camp.removeCommitteeMember(userID);
                camp.getCampInformation().committeeSlots++;
            
                System.out.println("You have withdrawn!");
            }
        } else {
            System.out.println("You are not registered!");
        }
    }
}
