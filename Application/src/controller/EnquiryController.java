package controller;

import database.EnquiryDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.CampDB;
import database.UserDB;
import model.Camp;
import model.Enquiry;
import model.Student;
import model.User;
import view.EnquiriesView;

/**
 * The EnquiryController class is responsible for handling enquiries from users.
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-11-02
 */
public class EnquiryController implements BaseController {

    private EnquiryDB enquiryDB;
    private EnquiriesView enquiriesView;
    private CampDB campDB;
    private UserDB userDB;

    public EnquiryController() {
        setMasterVariables();
    }

    @Override
    public void setMasterVariables() {
        this.campDB = new CampDB();
        this.enquiryDB = new EnquiryDB();
        this.enquiriesView = new EnquiriesView();
    }

    /**
     * Allows a user to submit an enquiry.
     *
     * @param userID The ID of the user.
     * @param campID The ID of the camp.
     * @param enquiryText The text of the enquiry.
     */
    public void submitEnquiry(String userID, String campID, String enquiryText) {
        Camp camp = campDB.getCamp(campID);
        User user = userDB.getUser(userID);

        if (user instanceof Student) {
            Student student = (Student) user; // Safe downcast after checking with instanceof
            Enquiry enquiry = new Enquiry(camp, student, enquiryText);
            enquiryDB.addEnquiry(enquiry);
            System.out.println("Enquiry submitted successfully.");
        } else {
            System.out.println("The user with ID " + userID + " is not a student.");
        }
    }

    /**
     * Allows staff to view enquiries related to a camp.
     *
     * @param campID The ID of the camp.
     */
    public void viewEnquiries(String campID) {
        HashMap<String, Enquiry> enquiriesMap = enquiryDB.getEnquiries(campID);
        List<String> enquiriesList = new ArrayList<>();

        for (Enquiry enquiry : enquiriesMap.values()) {
            // Assuming Enquiry has a method to get a formatted string representation of the enquiry
            String enquiryDetails = enquiry.toString(); // Replace with actual method to get details if different
            enquiriesList.add(enquiryDetails);
        }

        enquiriesView.displayEnquiries(enquiriesList);
    }

    /**
     * Allows staff or committee members to reply to an enquiry.
     *
     * @param enquiryID The ID of the enquiry.
     * @param replyText The reply text.
     */
    public void replyToEnquiry(String enquiryID, String replyText) {
        enquiryDB.updateEnquiryReply(enquiryID, replyText);
        System.out.println("Reply sent successfully.");
    }
}
