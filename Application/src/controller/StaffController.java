package controller;

import java.util.List;

import database.CampDB;
import database.EnquiryDB;
import model.Staff;
import model.Student;
import model.Camp;
import model.Enquiry;
import view.ReportView;
import view.EnquiriesView;
import view.PerformanceReport;
import view.SuggestionsView;

/**
 * The StaffController class is responsible for handling staff-related operations.
 * It provides methods for staff to create, edit, delete camps, view and reply to enquiries, and view and approve suggestions from camp committees.
 * This class interacts with the CampDB and EnquiryDB for database operations and uses ReportView, EnquiriesView, and SuggestionsView for displaying relevant staff information.
 * 
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-10-28
 */
public class StaffController {

    /** The CampDB object for interacting with the camp database. */
    private CampDB campDB = new CampDB();

    /** The EnquiryDB object for interacting with the enquiries database. */
    private EnquiryDB enquiryDB = new EnquiryDB();

    /** The ReportView object for displaying camp and performance reports. */
    private ReportView reportView = new ReportView();

    /** The EnquiriesView object for displaying and replying to student enquiries. */
    private EnquiriesView enquiriesView = new EnquiriesView();

    /** The SuggestionsView object for viewing and approving camp committee suggestions. */
    private SuggestionsView suggestionsView = new SuggestionsView();

    /**
     * Allows staff to create a new camp.
     * 
     * @param campDetails The details of the camp to be created.
     */
    public void createCamp(String campDetails) {
        // Placeholder implementation. Actual implementation would involve adding the new camp to the camp database.
        // For now, we'll assume there's a method in CampDB to handle this.
        campDB.addCamp(campDetails);
    }

    /**
     * Allows staff to edit camp details.
     * 
     * @param campID The ID of the camp to be edited.
     * @param updatedDetails The updated details of the camp.
     */
    public void editCamp(String campID, String updatedDetails) {
        // Placeholder implementation. Actual implementation would involve updating the camp details in the database.
        // For now, we'll assume there's a method in CampDB to handle this.
        campDB.updateCamp(campID, updatedDetails);
    }

    /**
     * Allows staff to delete a camp.
     * 
     * @param campID The ID of the camp to be deleted.
     */
    public void deleteCamp(String campID) {
        // Placeholder implementation. Actual implementation would involve removing the camp from the database.
        // For now, we'll assume there's a method in CampDB to handle this.
        campDB.deleteCamp(campID);
    }

    /**
     * Allows staff to view enquiries related to a camp.
     * 
     * @param campID The ID of the camp for which enquiries are to be viewed.
     */
    public void viewEnquiries(String campID) {
        // Retrieve enquiries related to the camp from the database
        Enquiry[] enquiries = enquiryDB.getEnquiries(campID);

        // Display the enquiries
        enquiriesView.displayEnquiries(enquiries);
    }

    /**
     * Allows staff to reply to an enquiry.
     * 
     * @param enquiryID The ID of the enquiry to which the reply is to be made.
     * @param replyText The text content of the reply.
     */
    public void replyToEnquiry(String enquiryID, String replyText) {
        // Update the reply to the enquiry in the database
        enquiryDB.updateEnquiryReply(enquiryID, replyText);
    }

    /**
     * Allows staff to view suggestions.
     * 
     * @param campID The ID of the camp for which suggestions are to be viewed.
     */
    public void viewSuggestion(String campID) {
        SuggestionsView suggestionsView = new SuggestionsView();
        // Assuming getSuggestions is a method in SuggestionDB that retrieves suggestions for a specific camp.
        suggestionsView.displaySuggestions(suggestionDB.getSuggestions(campID));
    }

    /**
     * Allows staff to approve a suggestion.
     * 
     * @param suggestionID The ID of the suggestion to be approved.
     */
    public void approveSuggestion(String suggestionID) {
        // Logic to approve a specific suggestion.
        // This might involve updating the suggestion's status in the database.
        suggestionDB.approveSuggestion(suggestionID);
        System.out.println("Suggestion approved successfully!");
    }

    /**
        * Allows staff to generate a report.
        * @param campID The ID of the camp for which the report is to be generated.
        * @param filterType The type of report to be generated.
     */
    public void generateStudentReport(String campID, String filterType) {
        ReportView reportView = new ReportView();
        
        // Fetch the camp details and display them.
        Camp campDetails = campDB.getCamp(campID);
        reportView.displayCampDetails(campDetails);
        
        // Fetch the list of students based on the filterType.
        List<Student> students;
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

    /**
     * Allows staff to generate a performance report for camp committee members.
     * 
     * @param campID The ID of the camp for which the performance report is to be generated.
     */
    public void generateCommitteePerformanceReport(String campID) {
        PerformanceReport performanceReport = new PerformanceReport();
        
        // Fetch the performance data for the camp committee members.
        String committeePerformanceData = campDB.getCommitteePerformanceForCamp(campID);
        
        // Display the performance report.
        performanceReport.displayReport(committeePerformanceData);
    }
}
