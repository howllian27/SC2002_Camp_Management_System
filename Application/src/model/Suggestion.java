package model;

/**
 * Represents a suggestion made by a student regarding a camp, including related information and approval status.
 * This class encapsulates information about the student, camp, camp information, and approval status.
 *
 * @author Shun Jie
 * @version 1.1
 */
public class Suggestion {
    private final String studentId;
    private String campId;
    private CampInformation campInformation;
    private boolean isApproved;

    /**
     * Constructs a {@code Suggestion} object with the specified student ID, camp ID, camp information, and approval status.
     *
     * @param studentId The ID of the student making the suggestion.
     * @param campId The ID of the camp associated with the suggestion.
     * @param campInformation The information related to the camp suggested by the student.
     * @param isApproved The approval status of the suggestion.
     */
    public Suggestion(String studentId, String campId, CampInformation campInformation, boolean isApproved) {
        this.studentId = studentId;
        this.campId = campId;
        this.campInformation = campInformation;
        this.isApproved = isApproved;
    }

    //Setters
    public void setApproved(boolean status) { this.isApproved = status; }
    public void setCampInformation(CampInformation campInformation) { this.campInformation = campInformation; }
    public void setCampID(String campID) { this.campId = campID; }

    //Getters
    public CampInformation getCampInformation() { return this.campInformation; }
    public String getStudentId() { return this.studentId; }
    public String getCampId() { return this.campId; }
    public boolean getIsApproved() { return this.isApproved; }
}
