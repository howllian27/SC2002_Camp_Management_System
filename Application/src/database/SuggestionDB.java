package database;

import model.Suggestion;
import java.util.HashMap;

public class SuggestionDB {
    private static SuggestionDB suggestionDB = null;
    private HashMap<String, Suggestion> suggestions;

    private SuggestionDB() {
        suggestions = new HashMap<>();
    }

    public static synchronized SuggestionDB getInstance() {
        if (suggestionDB == null)
            suggestionDB = new SuggestionDB();
         
        return suggestionDB;
    }

    public Suggestion getSuggestion(String campId, String studentId) {
        return suggestions.get(campId + "-" + studentId);
    }

    public void addSuggestion(Suggestion suggestion) {
        String key = suggestion.getCampId() + "-" + suggestion.getStudentId();
        suggestions.put(key, suggestion);
    }

    public void updateSuggestion(String campId, String studentId, Suggestion updatedSuggestion) {
        String key = campId + "-" + studentId;
        if (suggestions.containsKey(key)) {
            suggestions.put(key, updatedSuggestion);
        }
    }

    public void removeSuggestion(String campId, String studentId) {
        suggestions.remove(campId + "-" + studentId);
    }
}