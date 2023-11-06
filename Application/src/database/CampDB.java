package database;

import java.util.HashMap;
import java.util.Map;
import model.Camp;
import model.CampInformation;
import model.Student;

public class CampDB {

    private Map<String, Camp> campDatabase;

    public CampDB() {
        CampDB.campDatabase = new HashMap<>();
    }

    public Map<String, Camp> getAllCamps() {
        return new HashMap<>(campDatabase);
    }

    public static Camp getCamp(String campID) {
        Camp camp = campDatabase.get(campID);
        if (camp != null){
            return camp;
        } else {
            return null;
        }
    }

    public boolean addCamp(String campID, Camp camp) {
        if (!campDatabase.containsKey(campID)) {
            campDatabase.put(campID, camp);
            return true;
        }
        return false;
    }

    public static CampInformation getCampDetails(String campID) {
        Camp camp = campDatabase.get(campID);
        if (camp != null) {
            return camp.getCampInformation();
        }
        return null;
    }

    public boolean updateCamp(String campID, Camp updatedCamp) {
        if (campDatabase.containsKey(campID)) {
            campDatabase.put(campID, updatedCamp);
            return true;
        }
        return false;
    }

    public boolean deleteCamp(String campID) {
        return campDatabase.remove(campID) != null;
    }
    
    public static Map<String, Student> getAttendeesForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            Map<String, Student> attendeesMap = new HashMap<>();
            for (Student student : camp.getAttendees()) {
                attendeesMap.put(student.getID(), student);
            }
            return attendeesMap;
        }
        return new HashMap<>();
    }

    public static Map<String, Student> getCommitteeMembersForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            Map<String, Student> committeeMap = new HashMap<>();
            for (Student student : camp.getCommittee()) {
                committeeMap.put(student.getID(), student);
            }
            return committeeMap;
        }
        return new HashMap<>();
    }

    public static Map<String, Student> getAllStudentsForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            Map<String, Student> allStudents = new HashMap<>();
            for (Student student : camp.getAttendees()) {
                allStudents.put(student.getID(), student);
            }
            for (Student student : camp.getCommittee()) {
                allStudents.put(student.getID(), student);
            }
            return allStudents;
        }
        return new HashMap<>();
    }
}
