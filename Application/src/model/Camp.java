package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The `Camp` class represents a camping event, providing information
 * about the camp, its attendees and the committee members.
 *
 * @author Ruin9999
 * @version 1.0
 */
public class Camp {
    private final CampInformation campInformation;
    private final Set<Student> attendees;
    private final Set<Student> committee;

    /***
     * Constructs a new `Camp` object with the provided camp information.
     * @param campInformation The information associated with the camp.
     */
    Camp(CampInformation campInformation) {
        this.campInformation =  campInformation;
        this.attendees = new HashSet<Student>();
        this.committee = new HashSet<Student>();
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
    public void setVisibility(boolean visibility) { this.campInformation.visibility = visibility; }

    //Getters
    public String getName() { return this.campInformation.campName; }
    public Date[] getDates() { return this.campInformation.dates; }
    public Date getClosingDate() { return this.campInformation.registrationClosingDate; }
    public Faculty getFaculty() { return this.campInformation.faculty; }
    public String getLocation() { return this.campInformation.location; }
    public int getTotalSlots() { return this.campInformation.totalSlots; }
    public int getCommitteeSlots() { return this.campInformation.committeeSlots; }
    public String getDescription() { return this.campInformation.description; }
    public Staff getInCharge() { return this.campInformation.inCharge; }
    public boolean getVisibility() { return this.campInformation.visibility; }

    /***
     * Returns the set of attendees for this camp.
     * @return A set of students who are attendees for the camp.
     */
    public Set<Student> getAttendees() { return attendees; }

    /***
     * Returns the set of committee members for the camp
     * @return A set of students who are committee members for the camp.
     */
    public Set<Student> getCommittee() { return committee; }

    //Methods

    /***
     * Adds a student to the list of attendees for this camp.
     * @param student The student to be added as an attendee
     * @return `true` if the student is added successfully, `false` if the student is already an attendee.
     */
    public boolean addAttendee(Student student) {
        if(attendees.size() >= campInformation.totalSlots) return false;
        return attendees.add(student);
    }

    /***
     * Adds a student to the list of committee members for this camp.
     * @param student The student to be added as a committee member
     * @return `true` if the student was added successfully, `false`if the student was already a committee member.
     */
    public boolean addCommitteeMember(Student student) {
        if(committee.size() >= campInformation.committeeSlots) return false;
        return committee.add(student);
    }

    /***
     * Removes a student from the list of attendees for this camp.
     * @param student The student to be removed from the camp attendees.
     * @return `true` if the student was removed successfully, `false` if the student was not already an attendee.
     */
    public boolean removeAttendee(Student student) {
        return attendees.remove(student);
    }

    /***
     * Removes a student from the list of committee members for the camp.
     * @param student The student to be removed from the committee members.
     * @return `true` if the student was removed successfully, `false` if the student was not already a committee member.
     */
    public boolean removeCommitteeMember(Student student) {
        return committee.remove(student);
    }
}
