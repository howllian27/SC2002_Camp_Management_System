package controller;

import model.Student;
import database.CampDB;
import database.EnquiryDB;

/**
 * Controller responsible for student-related operations.
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-10-28
 */
public class StudentController {

    private CampDB campDB = new CampDB();
    private EnquiryDB enquiryDB = new EnquiryDB();

    /**
     * Allows a student to register for a camp.
     * @param userID The student's ID.
     * @param campID The ID of the camp to register for.
     * @param role The role (attendee or committee) the student is registering for.
     */
    public void registerForCamp(String userID, int campID, String role) {
        Camp camp = campDB.getCamp(campID);
        if (camp != null) {
            camp.registerUser(userID, role);
            campDB.updateCamp(campID, camp);
        }
    }

    /**
     * Allows a student to withdraw from a camp.
     * @param userID The student's ID.
     * @param campID The ID of the camp to withdraw from.
     */
    public void withdrawFromCamp(String userID, int campID) {
        Camp camp = campDB.getCamp(campID);
        if (camp != null) {
            camp.withdrawUser(userID);
            campDB.updateCamp(campID, camp);
        }
    }

    /**
     * Allows a student to submit an enquiry for a camp.
     * @param userID The student's ID.
     * @param campID The ID of the camp to submit an enquiry for.
     * @param enquiryText The text of the enquiry.
     */
    public void submitEnquiry(String userID, int campID, String enquiryText) {
        Enquiry enquiry = new Enquiry(userID, campID, enquiryText);
        enquiryDB.addEnquiry(enquiry);
    }
}