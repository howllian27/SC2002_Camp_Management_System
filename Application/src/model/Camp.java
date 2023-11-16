package model;

import java.util.Date;
import java.util.HashMap;

/**
 * The `Camp` class represents a camping event, providing information
 * about the camp, its attendees and the committee members.
 *
 * @author Shun Jie
 * @version 1.0
 */
public class Camp {
    private CampInformation campInformation;
    private final HashMap<String, Student> attendees;
    private final HashMap<String, Student> committee;

    /***
     * Constructs a new `Camp` object with the provided camp information.
     * @param campInformation The information associated with the camp.
     */
    public Camp(CampInformation campInformation) {
        this.campInformation =  campInformation;
        this.attendees = new HashMap<>();
        this.committee = new HashMap<>();
    }

    //Setters
    public void setName(String campName) { this.campInformation.campName = campName; }
    public void setDates(Date[] dates) { this.campInformation.dates = dates; }
    public void setClosingDate(Date closingDate) { this.campInformation.registrationClosingDate = closingDate; }
    public void setFaculty(Faculty faculty) { this.campInformation.faculty = faculty; }
    public void setLocation(String location) { this.campInformation.location = location; }
    public void setTotalSlots(int totalSlots) { this.campInformation.totalSlots = totalSlots; }
    public void setCommitteeSlots(int committeeSlots) { this.campInformation.committeeSlots = committeeSlots; }
    public void setDescription(String description) { this.campInformation.description = description; }
    public void setInCharge(Staff inCharge) { this.campInformation.inCharge = inCharge; }
    public void setVisibility(boolean visibility) { this.campInformation.isVisible = visibility; }
    public void setCampInformation(CampInformation campInformation) { this.campInformation = campInformation; }

    //Getters
    public CampInformation getCampInformation() { return this.campInformation; }
    public String getName() { return this.campInformation.campName; }
    public Date[] getDates() { return this.campInformation.dates; }
    public Date getClosingDate() { return this.campInformation.registrationClosingDate; }
    public Faculty getFaculty() { return this.campInformation.faculty; }
    public String getLocation() { return this.campInformation.location; }
    public int getTotalSlots() { return this.campInformation.totalSlots; }
    public int getRemainingSlots() { return this.campInformation.totalSlots - this.attendees.size(); }
    public int getCommitteeSlots() { return this.campInformation.committeeSlots; }
    public String getDescription() { return this.campInformation.description; }
    public Staff getInCharge() { return this.campInformation.inCharge; }
    public boolean getVisibility() { return this.campInformation.isVisible; }

    /***
     * Returns the set of attendees for this camp.
     * @return A set of students who are attendees for the camp.
     */
    public HashMap<String, Student> getAttendees() { return attendees; }

    /***
     * Returns the set of committee members for the camp
     * @return A set of students who are committee members for the camp.
     */
    public HashMap<String, Student> getCommittee() { return committee; }

    //Methods

    /***
     * Adds a student to the list of attendees for this camp.
     * @param student The student to be added as an attendee
     * @return `true` if the student is added successfully, `false` if the student is already an attendee.
     */
    public boolean addAttendee(String studentId, Student student) {
        if(attendees.size() >= campInformation.totalSlots) return false;
        attendees.put(studentId, student);
        return true;
    }

    /***
     * Adds a student to the list of committee members for this camp.
     * @param student The student to be added as a committee member
     * @return `true` if the student was added successfully, `false`if the student was already a committee member.
     */
    public boolean addCommitteeMember(String studentId, Student student) {
        if(committee.size() >= campInformation.committeeSlots) return false;
        committee.put(studentId, student);
        return true;
    }

    /***
     * Removes a student from the list of attendees for this camp.
     * @param studentId The id of the students to be removed from the camp attendees.
     * @return `true` if the student was removed successfully, `false` if the student was not already an attendee.
     */
    public boolean removeAttendee(String studentId) {
        if(attendees.get(studentId) == null) return false;
        attendees.remove(studentId);
        return true;
    }

    /***
     * Removes a student from the list of committee members for the camp.
     * @param studentId The id of the student to be removed from the committee members.
     * @return `true` if the student was removed successfully, `false` if the student was not already a committee member.
     */
    public boolean removeCommitteeMember(String studentId) {
        if(committee.get(studentId) == null) return false;
        committee.remove(studentId);
        return true;
    }
}
