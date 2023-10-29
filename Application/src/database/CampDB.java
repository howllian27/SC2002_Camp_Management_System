package database;

import java.util.Date;
import java.util.HashMap;

public class CampDB { 

    public static class Camp {
        private String campName;
        private Date startDate;
        private Date endDate;
        private Date registrationClosingDate;
        private String location;
        private String description;
        private int totalSlots;
        private int campCommitteeSlots = 10; // max 10
        private boolean isOpenToWholeNTU;
        private StaffDB staffInCharge;
        private HashMap<String, StudentDB> attendees;
        private HashMap<String, StudentDB> campCommittee;

        public Camp(String campName, Date startDate, Date endDate, Date registrationClosingDate,
                    String location, String description, int totalSlots, StaffDB staffInCharge) {
            this.campName = campName;
            this.startDate = startDate;
            this.endDate = endDate;
            this.registrationClosingDate = registrationClosingDate;
            this.location = location;
            this.description = description;
            this.totalSlots = totalSlots;
            this.staffInCharge = staffInCharge;
            this.attendees = new HashMap<>();
            this.campCommittee = new HashMap<>();
        }

        public boolean registerStudent(StudentDB student) {
            if(attendees.size() < totalSlots && !attendees.containsKey(student.getStudentID())) {
                attendees.put(student.getStudentID(), student);
                return true;
            }
            return false;
        }

        public boolean addCommitteeMember(StudentDB student) {
            if(campCommittee.size() < campCommitteeSlots && !campCommittee.containsKey(student.getStudentID())) {
                campCommittee.put(student.getStudentID(), student);
                return true;
            }
            return false;
        }

    }

    // Howell Methods
    private final HashMap<String, Camp> campDatabase;

    public CampDB() {
        this.campDatabase = new HashMap<>();
    }

    public HashMap<String, Camp> getAllCamps() {
        return campDatabase;
    }

    public Camp getCamp(String campID) {
        return campDatabase.get(campID);
    }

    public void addCamp(String campID, Camp campDetails) {
        campDatabase.put(campID, campDetails);
    }

    public void updateCamp(String campID, Camp updatedDetails) {
        if (campDatabase.containsKey(campID)) {
            campDatabase.put(campID, updatedDetails);
        }
    }

    public void deleteCamp(String campID) {
        campDatabase.remove(campID);
    }
}