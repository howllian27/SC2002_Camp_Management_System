package controller;

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

    private SuggestionDB suggestionDB;
    private SuggestionsView suggestionsView;

    public SuggestionController() {
        setMasterVariables();
    }

    @Override
    public void setMasterVariables() {
        this.suggestionDB = new SuggestionDB(); // Assuming SuggestionDB is a class that handles suggestion data.
        this.suggestionsView = new SuggestionsView(); // Assuming SuggestionsView is a class for displaying suggestions.
    }

    public void submitSuggestion(String committeeID, String campID, String suggestionText) {
        Suggestion suggestion = new Suggestion(committeeID, campID, suggestionText, false);
        suggestionDB.addSuggestion(suggestion);
        suggestionsView.displaySubmissionSuccess(suggestion);
    }

    public void viewSuggestions(String committeeID) {
        var suggestions = suggestionDB.getSuggestionsByCommittee(committeeID);
        if (suggestions.isEmpty()) {
            suggestionsView.displayNoSuggestionsFound();
        } else {
            suggestionsView.displaySuggestions(suggestions);
        }
    }

    public void editSuggestion(String suggestionID, String updatedText) {
        Suggestion suggestion = suggestionDB.getSuggestion(suggestionID);
        if (suggestion == null) {
            suggestionsView.displaySuggestionNotFound(suggestionID);
            return;
        }

        suggestion.setSuggestionText(updatedText);
        suggestionDB.updateSuggestion(suggestionID, suggestion);
        suggestionsView.displayEditSuccess(suggestion);
    }

    public void deleteSuggestion(String suggestionID) {
        if (!suggestionDB.exists(suggestionID)) {
            suggestionsView.displaySuggestionNotFound(suggestionID);
            return;
        }

        suggestionDB.removeSuggestion(suggestionID);
        suggestionsView.displayDeletionSuccess(suggestionID);
    }

    public void approveSuggestion(String suggestionID) {
        Suggestion suggestion = suggestionDB.getSuggestion(suggestionID);
        if (suggestion == null) {
            suggestionsView.displaySuggestionNotFound(suggestionID);
            return;
        }

        suggestion.setApproved(true);
        suggestionDB.updateSuggestion(suggestionID, suggestion);
        suggestionsView.displayApprovalSuccess(suggestion);
    }
}
