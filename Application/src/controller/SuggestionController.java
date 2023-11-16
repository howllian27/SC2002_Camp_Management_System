package controller;

import java.util.List;
import java.util.Scanner;

import database.SuggestionDB;
import database.CampDB;
import model.Camp;
import model.CampInformation;
import model.Student;
import model.Suggestion;
import view.SuggestionsView;

/**
 * The SuggestionController class is responsible for managing suggestions related to camps.
 * It interacts with the SuggestionDB for database operations and uses SuggestionsView for displaying relevant information.
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-11-02
*/
public class SuggestionController implements BaseController {

    SuggestionDB suggestionDB;
    CampDB campDB;
    private SuggestionsView suggestionsView;

    public SuggestionController() {
        setMasterVariables();
    }

    @Override
    public void setMasterVariables() {
        this.campDB = CampDB.getInstance();
        this.suggestionDB = SuggestionDB.getInstance();
        this.suggestionsView = new SuggestionsView();
    }

    /**
     * This method is used to submit a suggestion for a camp.
     * @param student
     * @param campID
    */
    public void submitSuggestion(Student student, String campID) {
        Camp camp = campDB.getCamp(campID);
        CampInformation campInformationSuggestion = suggestionsView.promptForSuggestions(camp);
        Suggestion suggestion = new Suggestion(student.getID(), campID, campInformationSuggestion, false);
        suggestionDB.addSuggestion(suggestion);
        student.addPoints(1);
        System.out.println("Suggestion successfully submitted!");
        System.out.println("+1 point for making a suggestion \n");
    }

    /**
     * This method is used to view all suggestions for a camp.
     * @param studentID
     * @param student
    */
    public boolean viewIndivSuggestions(String studentID, Student student) {
        List<Suggestion> suggestions = suggestionDB.getSuggestionsByStudent(studentID);
        Camp registeredCommitteeCamp = student.getRegisteredCommitteeCamp();

        if (suggestions.isEmpty()) {
            System.out.println("You've made no suggestions so far!");
            return false;
        } else {
            suggestionsView.displaySuggestions(suggestions, registeredCommitteeCamp);
            return true;
        }
    }

    /**
     * This method is used to view all suggestions for a camp.
     * @param student
     */
    public void editSuggestion(Student student) {
        Camp registeredCommitteeCamp = student.getRegisteredCommitteeCamp();
        List<Suggestion> suggestions = suggestionDB.getSuggestionsByStudent(student.getID()); 

        suggestionsView.displaySuggestions(suggestions, registeredCommitteeCamp);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the suggestion you want to edit: ");
        int suggestionNumber = scanner.nextInt();
        scanner.nextLine();
        Suggestion suggestion;
        if (suggestions.get(suggestionNumber-1) == null) {
            System.out.println("Suggestion not found!");
            return;
        } else {
            suggestion = suggestions.get(suggestionNumber-1);
        }

        CampInformation newCampInformation = suggestionsView.editSuggestionView(suggestion, registeredCommitteeCamp);
        suggestion.setCampInformation(newCampInformation);
        suggestionDB.updateSuggestion(suggestion);
        System.out.println("Suggestion successfully updated!");
    }

    /**
     * This method is used to delete a suggestion for a camp.
     * @param student
    */
    public void deleteSuggestion(Student student) {
        Camp registeredCommitteeCamp = student.getRegisteredCommitteeCamp();
        List<Suggestion> suggestions = suggestionDB.getSuggestionsByStudent(student.getID()); 

        suggestionsView.displaySuggestions(suggestions, registeredCommitteeCamp);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the suggestion you want to delete: ");
        int suggestionNumber = scanner.nextInt();
        scanner.nextLine();
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
     * This method is used to approve a suggestion for a camp.
     * @param campID
     * @param studentID
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
}
