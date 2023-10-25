import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Camp {
    private final CampInformation campInformation;
    private final ArrayList<Student> attendees;
    private final ArrayList<Student> committee;

    Camp(String campName, Date[] dates, Date closingDate, Faculty faculty,
         String location, int totalSlots, int committeeSlots, String description, Staff inCharge, boolean visibility) {
        this.campInformation = new CampInformation(campName, dates, closingDate, faculty,
                location, totalSlots, committeeSlots, description, inCharge, visibility);
        this.attendees = new ArrayList<Student>();
        this.committee = new ArrayList<Student>();
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

    public ArrayList<Student> getAttendees() { return attendees; }
    public ArrayList<Student> getCommittee() { return committee; }


    //Methods
    public boolean addAttendee(Student student) {
        if(attendees.size() >= campInformation.totalSlots) return false; //If camp is full
        if(new Date().after(campInformation.registrationClosingDate)) return false; //If date is past registration date
        if(attendees.contains(student)) return true;

        //ADD CHECK FOR IF STUDENT HAS CONFLICTING CAMP DATES -IMPL

        if(attendees.add(student)) {
            student.onAttendeeAdded(this);
            return true;
        }
        return false;
    }
    public boolean removeAttendee(Student student) {
        if(attendees.remove(student)) {
            student.onAttendeeRemoved(this);
            return true;
        }
        return false;
    }
    public boolean addCommittee(Student student) {
        if(student.getRegisteredCommitteeCamp() != null) return false;
        if(committee.contains(student)) return true;

        committee.add(student);
        student.onCommitteeMemberAdded(this);
        return true;
    }
    public boolean removeCommittee(Student student) {
        if(committee.remove(student)) {
            student.onCommitteeMemberRemoved();
            return true;
        }
        return false;
    }

    //Events
    public void onCampAdded(Student student) {

    }

    public void onCampRemoved(Student student) {

    }

    public void onCampCommitteeAdded(Student student) {

    }

    public void onCampCommitteeRemoved(Student student) {
        
    }
}
