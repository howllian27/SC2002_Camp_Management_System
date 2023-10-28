package controller;

import model.Staff;

import java.util.List;

import database.CampDB;
import database.EnquiryDB;
import view.EnquiriesView;

/**
 * Controller responsible for staff-related operations.
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-10-28
 */
public class StaffController {

    private CampDB campDB = new CampDB();
    private EnquiryDB enquiryDB = new EnquiryDB();

    /**
     * Allows staff to create a new camp.
     * @param campDetails The details of the camp to be created.
     */
    public void createCamp(Camp campDetails) {
        campDB.addCamp(campDetails);
    }

    /**
     * Allows staff to edit the details of a camp.
     * @param campID The ID of the camp to be edited.
     * @param updatedDetails The updated details of the camp.
     */
    public void editCamp(int campID, Camp updatedDetails) {
        campDB.updateCamp(campID, updatedDetails);
    }

    /**
     * Allows staff to delete a camp.
     * @param campID The ID of the camp to be deleted.
     */
    public void deleteCamp(int campID) {
        campDB.deleteCamp(campID);
    }

    /**
     * Allows staff to view enquiries related to a camp.
     * @param campID The ID of the camp to view enquiries for.
     */
    public void viewEnquiries(int campID) {
        List<Enquiry> enquiries = enquiryDB.getEnquiries(campID);
        // Display enquiries
        Enqu
    }

    /**
     * Allows staff to reply to an enquiry.
     * @param enquiryID The ID of the enquiry to reply to.
     * @param replyText The text of the reply.
     */
    public void replyToEnquiry(int enquiryID, String replyText) {
        Enquiry enquiry = enquiryDB.getEnquiry(enquiryID);
        if (enquiry != null) {
            enquiry.setReply(replyText);
            enquiryDB.updateEnquiryReply(enquiryID, replyText);
        }
    }
}
