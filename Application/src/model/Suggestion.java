package model;

public class Suggestion {
    private final String campId;
    private final String studentId;
    private CampInformation campInformation;
    private String suggestionText;
    private boolean isApproved;

    public Suggestion(String studentId, String campId, String suggestionText, boolean isApproved) {
        this.studentId = studentId;
        this.campId = campId;
        this.suggestionText = suggestionText;
        this.isApproved = isApproved;
    }

    public Suggestion(String studentId, String campId, CampInformation campInformation, boolean isApproved) {
        this.studentId = studentId;
        this.campId = campId;
        this.campInformation = campInformation;
        this.isApproved = isApproved;
    }

    public void setApproved(boolean status) {
        this.isApproved = status;
    }

    public void setSuggestionText(String updatedSuggestion) {
        this.suggestionText = updatedSuggestion;
    }

    public CampInformation getCampInformation() { return this.campInformation; }
    public String getStudentId() { return this.studentId; }
    public String getCampId() { return this.campId; }
}
