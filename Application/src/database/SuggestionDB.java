package database;

import model.Suggestion;

import java.util.*;

/**
 * The {@code SuggestionDB} class represents an in-memory database for managing suggestions related to camps.
 * It provides methods for adding, updating, and retrieving suggestions from the database.
 * This class follows the Singleton design pattern to ensure a single instance is used throughout the application.
 *
 * @author Wei Hong
 * @version 1.0
 */
public class SuggestionDB {
    private final ArrayList<Suggestion> suggestions;

    private static SuggestionDB suggestionDB = null;

    private SuggestionDB() {
        this.suggestions = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the {@code SuggestionDB} class.
     *
     * @return The singleton instance of {@code SuggestionDB}.
     */
    public static synchronized SuggestionDB getInstance() {
        if (suggestionDB == null)
            suggestionDB = new SuggestionDB();

        return suggestionDB;
    }

    /**
     * Retrieves a suggestion from the database based on the camp ID and student ID.
     *
     * @param campId    The ID of the camp associated with the suggestion.
     * @param studentId The ID of the student who made the suggestion.
     * @return The suggestion object or {@code null} if not found.
     */
    public Suggestion getSuggestion(String campId, String studentId) {
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getCampId().equals(campId) && suggestion.getStudentId().equals(studentId)) {
                return suggestion;
            }
        }
        return null;
    }

    /**
     * Adds a new suggestion to the database.
     *
     * @param suggestion The suggestion object to be added.
     */
    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    /**
     * Updates an existing suggestion in the database.
     *
     * @param updatedSuggestion The updated suggestion object.
     */
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

    /**
     * Removes a suggestion from the database.
     *
     * @param suggestion The suggestion object to be removed.
     */
    public void removeSuggestion(Suggestion suggestion) {
        suggestions.remove(suggestion);
    }

    /**
     * Retrieves all suggestions associated with a specific camp from the database.
     *
     * @param campId The ID of the camp for which suggestions are retrieved.
     * @return A list of suggestions for the specified camp.
     */
    public List<Suggestion> getSuggestionsByCamp(String campId) {
        List<Suggestion> campSuggestions = new ArrayList<>();
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getCampId().equals(campId)) {
                campSuggestions.add(suggestion);
            }
        }
        return campSuggestions;
    }

    /**
     * Retrieves all suggestions made by a specific student from the database.
     *
     * @param studentId The ID of the student for which suggestions are retrieved.
     * @return A list of suggestions made by the specified student.
     */
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
