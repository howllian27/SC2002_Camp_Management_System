package controller;

import database.CampDB;
import database.EnquiryDB;
import model.Camp;
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
        // Retrieve the student and camp from the database
        Student student = (Student) UserDB.getUser(userID);
        Camp camp = campDB.getCamp(campID);

        // Register the student for the camp
        student.registeredCamps.add(campID);
        if (role.equals("campCommittee")) {
            camp.campCommitteeSlots--;
        } else {
            camp.totalSlots--;
        }

        // Update the camp in the database
        campDB.updateCamp(campID, camp);
    }

    /**
     * Allows a student to withdraw from a camp.
     * 
     * @param userID The ID of the student withdrawing.
     * @param campID The ID of the camp to be withdrawn from.
     */
    public void withdrawFromCamp(String userID, String campID) {
        // Retrieve the student and camp from the database
        Student student = (Student) UserDB.getUser(userID);
        Camp camp = campDB.getCamp(campID);

        // Withdraw the student from the camp
        student.registeredCamps.remove(campID);
        camp.totalSlots++;

        // Update the camp in the database
        campDB.updateCamp(campID, camp);
    }

    /**
     * Allows a student to submit an enquiry related to a camp.
     * 
     * @param userID The ID of the student submitting the enquiry.
     * @param campID The ID of the camp the enquiry is related to.
     * @param enquiryText The text content of the enquiry.
     */
    public void submitEnquiry(String userID, String campID, String enquiryText) {
        // Create a new enquiry
        Enquiry enquiry = new Enquiry(userID, campID, enquiryText);

        // Add the enquiry to the database
        enquiryDB.addEnquiry(enquiry);

        // Display the enquiry
        enquiriesView.displayEnquiries(new Enquiry[]{enquiry});
    }
}
