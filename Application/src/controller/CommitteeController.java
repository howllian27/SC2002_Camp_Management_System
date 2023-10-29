package controller;

import database.EnquiryDB;
import view.EnquiriesView;
import view.SuggestionsView;

/**
 * The CommitteeController class is responsible for handling operations related to camp committee members.
 * It provides methods for committee members to submit suggestions, reply to enquiries, and view their own suggestions.
 * This class interacts with the EnquiryDB for database operations and uses EnquiriesView and SuggestionsView for displaying relevant committee information.
 * 
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-10-28
 */
public class CommitteeController {

    /** The EnquiryDB object for interacting with the enquiries database. */
    private EnquiryDB enquiryDB = new EnquiryDB();

    /** The EnquiriesView object for displaying and replying to student enquiries. */
    private EnquiriesView enquiriesView = new EnquiriesView();

    /** The SuggestionsView object for submitting and viewing camp committee suggestions. */
    private SuggestionsView suggestionsView = new SuggestionsView();

    /**
     * Allows committee members to submit suggestions for a specific camp.
     * 
     * @param committeeID The ID of the committee member submitting the suggestion.
     * @param campID The ID of the camp for which the suggestion is being made.
     * @param suggestionText The text content of the suggestion.
     */
    public void submitSuggestion(String committeeID, String campID, String suggestionText) {
        // Placeholder implementation. Actual implementation would involve adding the suggestion to a database or list.
        // For now, we'll assume there's a method in SuggestionsView to handle this.
        suggestionsView.promptForSuggestions(committeeID, campID, suggestionText);
    }

    /**
     * Allows committee members to reply to an enquiry related to a camp they oversee.
     * 
     * @param enquiryID The ID of the enquiry to which the reply is to be made.
     * @param replyText The text content of the reply.
     */
    public void replyToEnquiry(String enquiryID, String replyText) {
        // Placeholder implementation. Actual implementation would involve updating the enquiry with the reply.
        // For now, we'll assume there's a method in EnquiryDB to handle this and a method in EnquiriesView to display the reply.
        enquiryDB.updateEnquiryReply(enquiryID, replyText);
        enquiriesView.displayEnquiries(enquiryDB.getEnquiriesByCommitteeID(enquiryID));
    }
}
