package database;

import model.Suggestion;

import java.util.*;

public class SuggestionDB {
    private ArrayList<Suggestion> suggestions;

    private static SuggestionDB suggestionDB = null;

    private SuggestionDB() {
        this.suggestions = new ArrayList<>();
    }

    public static synchronized SuggestionDB getInstance() {
        if (suggestionDB == null)
            suggestionDB = new SuggestionDB();

        return suggestionDB;
    }

    public Suggestion getSuggestion(String campId, String studentId) {
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getCampId().equals(campId) && suggestion.getStudentId().equals(studentId)) {
                return suggestion;
            }
        }
        return null;
    }

    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    public void updateSuggestion(Suggestion updatedSuggestion) {
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getCampId().equals(updatedSuggestion.getCampId()) &&
                suggestion.getStudentId().equals(updatedSuggestion.getStudentId())) {
                suggestions.remove(suggestion);
                suggestions.add(updatedSuggestion);
                break;
            }
        }
    }

    public void removeSuggestion(Suggestion suggestion) {
        suggestions.remove(suggestion);
    }

    public List<Suggestion> getSuggestionsByCamp(String campId) {
        List<Suggestion> campSuggestions = new ArrayList<>();
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getCampId().equals(campId)) {
                campSuggestions.add(suggestion);
            }
        }
        return campSuggestions;
    }

    public List<Suggestion> getSuggestionsByStudent(String studentId) {
        List<Suggestion> studentSuggestions = new ArrayList<>();
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getStudentId().equals(studentId)) {
                studentSuggestions.add(suggestion);
            }
        }
        return studentSuggestions;
    }
}
