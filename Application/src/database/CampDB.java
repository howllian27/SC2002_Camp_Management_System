package database;

import java.util.HashMap;
import java.util.Map;
import model.Camp;
import model.Student;

public class CampDB {

    private final Map<String, Camp> campDatabase;

    public CampDB() {
        this.campDatabase = new HashMap<>();
    }

    public Map<String, Camp> getAllCamps() {
        return new HashMap<>(campDatabase);
    }

    public Camp getCamp(String campID) {
        return campDatabase.get(campID);
    }

    public boolean addCamp(String campID, Camp camp) {
        if (!campDatabase.containsKey(campID)) {
            campDatabase.put(campID, camp);
            return true;
        }
        return false;
    }

    public boolean updateCamp(String campID, Camp updatedDetails) {
        if (campDatabase.containsKey(campID)) {
            campDatabase.put(campID, updatedDetails);
            return true;
        }
        return false;
    }

    public boolean deleteCamp(String campID) {
        return campDatabase.remove(campID) != null;
    }
    
    public Map<String, Student> getAttendeesForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            Map<String, Student> attendeesMap = new HashMap<>();
            for (Student student : camp.getAttendees()) {
                attendeesMap.put(student.getId(), student);
            }
            return attendeesMap;
        }
        return new HashMap<>();
    }

    public Map<String, Student> getCommitteeMembersForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            Map<String, Student> committeeMap = new HashMap<>();
            for (Student student : camp.getCommittee()) {
                committeeMap.put(student.getId(), student);
            }
            return committeeMap;
        }
        return new HashMap<>();
    }

    public Map<String, Student> getAllStudentsForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            Map<String, Student> allStudents = new HashMap<>();
            for (Student student : camp.getAttendees()) {
                allStudents.put(student.getId(), student);
            }
            for (Student student : camp.getCommittee()) {
                allStudents.put(student.getId(), student);
            }
            return allStudents;
        }
        return new HashMap<>();
    }
}
