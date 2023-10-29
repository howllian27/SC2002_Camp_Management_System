package controller;

import java.util.List;

import database.*;
import model.*;
import view.*;

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
        Suggestion suggestion = new Suggestion(committeeID, campID, suggestionText);
        suggestionDB.addSuggestion(suggestion);
        // Add a point for the committee member for submitting a suggestion.
        CampCommittee committeeMember = (CampCommittee) userDB.getUser(committeeID);
        committeeMember.addPoint();
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

    /**
     * Allows committee members to view their suggestions.
     * 
     * @param committeeID The ID of the committee member.
     */
    public void viewSuggestions(String committeeID) {
        SuggestionsView suggestionsView = new SuggestionsView();
        List<Suggestion> suggestions = suggestionDB.getSuggestionsByCommittee(committeeID);
        suggestionsView.displaySuggestions(suggestions);
    }

    /**
     * Allows committee members to edit their suggestions.
     * 
     * @param suggestionID The ID of the suggestion.
     * @param updatedText The updated suggestion text.
     */
    public void editSuggestion(String suggestionID, String updatedText) {
        suggestionDB.updateSuggestion(suggestionID, updatedText);
    }

    /**
     * Allows committee members to delete their suggestions.
     * 
     * @param suggestionID The ID of the suggestion.
     */
    public void deleteSuggestion(String suggestionID) {
        suggestionDB.deleteSuggestion(suggestionID);
    }

    /**
     * Allows committee members to generate a report of students attending the camp.
     * 
     * @param campID The ID of the camp.
     * @param filterType The type of filter (attendee, camp committee, etc.).
     */
    public void generateStudentReport(String campID, String filterType) {
        ReportView reportView = new ReportView();
        // Fetch the camp details and display them.
        Camp campDetails = campDB.getCamp(campID);
        reportView.displayCampDetails(campDetails);
        
        // Fetch the list of students based on the filterType.
        List<StudentDB> students;
        if ("attendee".equals(filterType)) {
            students = campDB.getAttendeesForCamp(campID);
        } else if ("campCommittee".equals(filterType)) {
            students = campDB.getCommitteeMembersForCamp(campID);
        } else {
            students = campDB.getAllStudentsForCamp(campID);
        }
        
        // Display the roles of the participants.
        reportView.displayRoleParticipants(students);
    }
}
