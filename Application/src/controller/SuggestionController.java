package controller;

import java.util.List;

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

    public void submitSuggestion(Student student, String campID) {
        Camp camp = campDB.getCamp(campID);
        CampInformation campInformationSuggestion = suggestionsView.promptForSuggestions(camp);
        Suggestion suggestion = new Suggestion(student.getID(), campID, campInformationSuggestion, false);
        suggestionDB.addSuggestion(suggestion);
        student.addPoints(1);
        System.out.println("Suggestion successfully submitted!");
        System.out.println("+1 point for making a suggestion");
    }

    public void viewIndivSuggestions(String studentID) {
        List<Suggestion> suggestions = suggestionDB.getSuggestionsByStudent(studentID);
        if (suggestions.isEmpty()) {
            System.out.println("You've made no suggestions so far!");
        } else {
            suggestionsView.displaySuggestions(suggestions);
        }
    }

    public void editSuggestion(String campID, String studentID, String updatedText) {
        Suggestion suggestion = suggestionDB.getSuggestion(campID, studentID);
        if (suggestion == null) {
            System.out.println("Suggestion not found!");
            return;
        }

        suggestion.setSuggestionText(updatedText);
        suggestionDB.updateSuggestion(suggestion);
        System.out.println("Suggestion successfully updated!");
    }

    public void deleteSuggestion(String campID, String studentID) {
        Suggestion suggestion = suggestionDB.getSuggestion(campID, studentID);
        if (suggestion == null) {
            System.out.println("Suggestion not found!");
            return;
        }

        suggestionDB.removeSuggestion(suggestion);
        System.out.println("Suggestion successfully deleted!");
    }

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
