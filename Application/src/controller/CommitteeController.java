package controller;

import model.CampCommittee;

import java.util.List;

import database.CampDB;
import database.EnquiryDB;
import view.EnquiriesView;
import view.LoginView;

/**
 * Controller responsible for camp committee member-related operations.
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-10-28
 */
public class CommitteeController {

    private CampDB campDB = new CampDB();
    private EnquiryDB enquiryDB = new EnquiryDB();
    private EnquiriesView enquiriesView = new EnquiriesView();

    /**
     * Allows a camp committee member to submit a suggestion for a camp.
     * @param userID The committee member's ID.
     * @param campID The ID of the camp to suggest changes for.
     * @param suggestionText The text of the suggestion.
     */
    public void submitSuggestion(String userID, int campID, String suggestionText) {
        CampCommittee committeeMember = (CampCommittee) campDB.getUser(userID);
        committeeMember.submitSuggestion(campID, suggestionText);
    }

    /**
     * Allows a camp committee member to view and reply to enquiries for a camp.
     * @param userID The committee member's ID.
     * @param campID The ID of the camp to view enquiries for.
     */
    public void viewAndReplyToEnquiries(String userID, int campID) {
        List<Enquiry> enquiries = enquiryDB.getEnquiries(campID);
        enquiriesView.displayEnquiries(enquiries);
        // Putting replyTest here for simplicity first
        String replyText = "sampleReply";
        enquiryDB.updateEnquiryReply(enquiryID, replyText);
    }

    /**
     * Allows a camp committee member to generate a report for a camp.
     * @param userID The committee member's ID.
     * @param campID The ID of the camp to generate a report for.
     */
    public void generateReport(String userID, int campID) {
        CampCommittee committeeMember = (CampCommittee) campDB.getUser(userID);
        committeeMember.generateReport(campID);
    }
}
