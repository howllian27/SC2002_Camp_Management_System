package controller;

import database.EnquiryDB;
import database.CampDB;
import model.Camp;
import model.Enquiry;
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
        Enquiry enquiry = new Enquiry(camp, userID, enquiryText);
        enquiryDB.addEnquiry(enquiry);
        System.out.println("Enquiry submitted successfully.");
    }

    /**
     * Allows staff to view enquiries related to a camp.
     *
     * @param campID The ID of the camp.
     */
    public void viewEnquiries(String campID) {
        var enquiries = enquiryDB.getEnquiries(campID);
        enquiriesView.displayEnquiries(enquiries);
    }

    /**
     * Allows staff or committee members to reply to an enquiry.
     *
     * @param enquiryID The ID of the enquiry.
     * @param replyText The reply text.
     */
    public void replyToEnquiry(String enquiryID, String replyText) {
        enquiryDB.updateEnquiryReply(enquiryID, replyText);
        enquiriesView.displayReplySuccess();
    }
}
