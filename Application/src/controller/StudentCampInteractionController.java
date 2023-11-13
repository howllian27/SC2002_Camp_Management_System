package controller;

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
        if (student != null && camp != null) {
            // Logic to register the student for the camp
            student.addCamp(campID, camp);
            if (role.equals("committee")) {
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
        if (student != null && camp != null) {
            // Logic to withdraw the student from the camp
            System.out.println("You have withdrawn!");
        }
    }
}
