package controller;

import java.util.List;
import java.util.HashMap;

import database.SuggestionDB;
import database.CampDB;
import database.UserDB;

import helper.InputHelper;
import model.Camp;
import model.CampInformation;
import model.Staff;
import model.Student;
import model.Suggestion;
import view.CampListView;
import view.SuggestionsView;

/**
 * The {@code SuggestionController } class is responsible for managing suggestions related to camps.
 * It interacts with the SuggestionDB for database operations and uses SuggestionsView for displaying relevant information.
 *
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-11-02
*/
public class SuggestionController implements BaseController {

    SuggestionDB suggestionDB;
    CampDB campDB;
    UserDB userDB;

    public SuggestionController() {
        setMasterVariables();
    }

    @Override
    public void setMasterVariables() {
        this.campDB = CampDB.getInstance();
        this.userDB = UserDB.getInstance();
        this.suggestionDB = SuggestionDB.getInstance();
    }

    /**
     * Submits a suggestion for a camp.
     *
     * @param student The student submitting the suggestion.
     * @param campID The ID of the camp for which the suggestion is submitted.
     */
    public void submitSuggestion(Student student, String campID) {
        Camp camp = campDB.getCamp(campID);
        CampInformation campInformationSuggestion = SuggestionsView.promptForSuggestions(camp);
        Suggestion suggestion = new Suggestion(student.getID(), campID, campInformationSuggestion, false);
        suggestionDB.addSuggestion(suggestion);
        student.addPoints(1);
        System.out.println("Suggestion successfully submitted!");
        System.out.println("+1 point for making a suggestion \n");
    }

    /**
     * Views all suggestions submitted by a student.
     *
     * @param studentID The ID of the student.
     * @param student The student object.
     * @return True if suggestions exist, false otherwise.
     */
    public boolean viewIndivSuggestions(String studentID, Student student) {
        List<Suggestion> suggestions = suggestionDB.getSuggestionsByStudent(studentID);
        Camp registeredCommitteeCamp = student.getRegisteredCommitteeCamp();

        if (suggestions.isEmpty()) {
            System.out.println("You've made no suggestions so far!");
            return false;
        } else {
            SuggestionsView.displaySuggestions(suggestions, registeredCommitteeCamp);
            return true;
        }
    }

    /**
     * Edits a suggestion submitted by a student.
     *
     * @param student The student submitting the suggestion.
     */
    public void editSuggestion(Student student) {
        Camp registeredCommitteeCamp = student.getRegisteredCommitteeCamp();
        List<Suggestion> suggestions = suggestionDB.getSuggestionsByStudent(student.getID()); 

        SuggestionsView.displaySuggestions(suggestions, registeredCommitteeCamp);
        System.out.println("Enter the number of the suggestion you want to edit: ");
        int suggestionNumber = InputHelper.nextInt();

        Suggestion suggestion;
        if (suggestions.get(suggestionNumber-1) == null) {
            System.out.println("Suggestion not found!");
            return;
        } else {
            suggestion = suggestions.get(suggestionNumber-1);
        }

        CampInformation newCampInformation = SuggestionsView.editSuggestionView(suggestion, registeredCommitteeCamp);
        suggestion.setCampInformation(newCampInformation);
        suggestionDB.updateSuggestion(suggestion);
        System.out.println("Suggestion successfully updated!");
    }

    /**
     * Deletes a suggestion submitted by a student.
     *
     * @param student The student submitting the suggestion.
     */
    public void deleteSuggestion(Student student) {
        Camp registeredCommitteeCamp = student.getRegisteredCommitteeCamp();
        List<Suggestion> suggestions = suggestionDB.getSuggestionsByStudent(student.getID()); 

        SuggestionsView.displaySuggestions(suggestions, registeredCommitteeCamp);
        System.out.println("Enter the number of the suggestion you want to delete: ");
        int suggestionNumber = InputHelper.nextInt();
        Suggestion suggestion;
        if (suggestions.get(suggestionNumber-1) == null) {
            System.out.println("Suggestion not found!");
            return;
        } else {
            suggestion = suggestions.get(suggestionNumber-1);
        }

        suggestionDB.removeSuggestion(suggestion);
        System.out.println("Suggestion successfully deleted!");
    }

    /**
     * Approves a suggestion for a camp.
     *
     * @param campID The ID of the camp.
     * @param studentID The ID of the student who submitted the suggestion.
     */
    public void approveSuggestion(String campID, String studentID) {
        Suggestion suggestion = suggestionDB.getSuggestion(campID, studentID);
        if (suggestion == null) {
            System.out.println("Suggestion not found!");
            return;
        }

        suggestion.setApproved(true);
        System.out.println("Suggestion successfully approved!");
    }

    /**
     * Handles suggestions for a staff member.
     * Displays suggestions for a specific camp and allows staff to approve or reject them.
     *
     * @param staff The staff member handling suggestions.
     */
    public void staffSuggestionHandler(Staff staff){
        HashMap<String, Camp> staffCamps = staff.getCamps();

        //Get list of staff camps
        List <Camp> camps = staffCamps.values().stream().toList();
        CampListView.displayCampsForStaff(camps, false);

        // Get staff input on which camp to view suggestions
        System.out.println("Enter the number of the camp you want to view suggestions for: ");
        int campNumber;
        try {
            campNumber = InputHelper.nextInt();
        } catch(Exception e) {
            System.out.println("Invalid input!");
            return;
        }
        
        Camp selectedCamp = camps.get(campNumber-1);


        List<Suggestion> suggestions = suggestionDB.getSuggestionsByCamp(selectedCamp.getName());
        if (suggestions.isEmpty()){
            System.out.println("No suggestions found for this camp!");
            return;
        }
        SuggestionsView.displaySuggestions(suggestions, selectedCamp);
        System.out.println("Enter the number of the suggestion you want to approve/reject: ");
        int suggestionNumber = InputHelper.nextInt();

        Suggestion suggestion;
        if (suggestions.get(suggestionNumber-1) == null) {
            System.out.println("Suggestion not found!");
            return;
        } else {
            suggestion = suggestions.get(suggestionNumber-1);
        }

        System.out.print("Enter 1 to approve, 2 to reject: ");
        int choice = InputHelper.nextInt();

        switch (choice){
            case 1:
                suggestion.setApproved(true);
                selectedCamp.setCampInformation(suggestion.getCampInformation());
                Student suggestionSetter = (Student) userDB.getUser(suggestion.getStudentId(), true);
                suggestionSetter.addPoints(1);

                System.out.println("Suggestion successfully approved!");
                break;
            
            case 2:
                System.out.println("Suggestion successfully rejected!");
                break;
            
            default:
                System.out.println("Invalid input!");
                break;
        }
        
    }
}
