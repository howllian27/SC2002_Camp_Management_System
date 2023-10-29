package controller;

import database.CampDB;
import database.EnquiryDB;
import model.Student;
import view.UserProfileView;
import view.EnquiriesView;

/**
 * The StudentController class is responsible for handling student-related operations.
 * It provides methods for students to register for camps, withdraw from camps, and submit enquiries.
 * This class interacts with the CampDB and EnquiryDB for database operations and uses UserProfileView and EnquiriesView for displaying relevant student information.
 * 
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-10-28
 */
public class StudentController {

    /** The CampDB object for interacting with the camp database. */
    private CampDB campDB = new CampDB();

    /** The EnquiryDB object for interacting with the enquiries database. */
    private EnquiryDB enquiryDB = new EnquiryDB();

    /** The UserProfileView object for displaying student profile information. */
    private UserProfileView userProfileView = new UserProfileView();

    /** The EnquiriesView object for displaying and prompting for student enquiries. */
    private EnquiriesView enquiriesView = new EnquiriesView();

    /**
     * Allows a student to register for a camp.
     * 
     * @param userID The ID of the student registering.
     * @param campID The ID of the camp to be registered for.
     * @param role The role (e.g., attendee or camp committee) the student is registering for.
     */
    public void registerForCamp(String userID, String campID, String role) {
        // Placeholder implementation. Actual implementation would involve adding the student to the camp's registration list in the database.
        // For now, we'll assume there's a method in CampDB to handle this.
        campDB.registerStudentForCamp(userID, campID, role);
    }

    /**
     * Allows a student to withdraw from a camp.
     * 
     * @param userID The ID of the student withdrawing.
     * @param campID The ID of the camp to be withdrawn from.
     */
    public void withdrawFromCamp(String userID, String campID) {
        // Placeholder implementation. Actual implementation would involve removing the student from the camp's registration list in the database.
        // For now, we'll assume there's a method in CampDB to handle this.
        campDB.withdrawStudentFromCamp(userID, campID);
    }

    /**
     * Allows a student to submit an enquiry related to a camp.
     * 
     * @param userID The ID of the student submitting the enquiry.
     * @param campID The ID of the camp the enquiry is related to.
     * @param enquiryText The text content of the enquiry.
     */
    public void submitEnquiry(String userID, String campID, String enquiryText) {
        // Placeholder implementation. Actual implementation would involve adding the enquiry to the enquiries database.
        // For now, we'll assume there's a method in EnquiryDB to handle this.
        enquiryDB.addEnquiry(userID, campID, enquiryText);
    }
}
