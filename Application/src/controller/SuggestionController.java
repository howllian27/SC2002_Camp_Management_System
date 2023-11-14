package controller;

import java.util.List;

import database.SuggestionDB;
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
    private SuggestionsView suggestionsView;

    public SuggestionController() {
        setMasterVariables();
    }

    @Override
    public void setMasterVariables() {
        this.suggestionDB = SuggestionDB.getInstance(); // Assuming SuggestionDB is a class that handles suggestion data.
        this.suggestionsView = new SuggestionsView(); // Assuming SuggestionsView is a class for displaying suggestions.
    }

    public void submitSuggestion(String committeeID, String campID, String suggestionText) {
        Suggestion suggestion = new Suggestion(committeeID, campID, suggestionText, false);
        suggestionDB.addSuggestion(suggestion);
        System.out.println("Suggestion successfully submitted!");
    }

    public void viewSuggestions(String committeeID) {
        List<String> suggestions = suggestionDB.getSuggestionsByCommittee(committeeID);
        if (suggestions.isEmpty()) {
            System.out.println("Suggestion not found!");
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
        suggestionDB.updateSuggestion(campID, studentID, suggestion);
        System.out.println("Suggestion successfully updated!");
    }

    public void deleteSuggestion(String campID, String studentID) {
        if (suggestionDB.getSuggestion(campID, studentID) != null) {
            System.out.println("Suggestion not found!");
            return;
        }

        suggestionDB.removeSuggestion(campID, studentID);
        System.out.println("Suggestion successfully deleted!");
    }

    public void approveSuggestion(String campID, String studentID) {
        Suggestion suggestion = suggestionDB.getSuggestion(campID, studentID);
        if (suggestion == null) {
            System.out.println("Suggestion not found!");
            return;
        }

        suggestion.setApproved(true);
        suggestionDB.updateSuggestion(campID, studentID, suggestion);
        System.out.println("Suggestion successfully approved!");
    }
}
