package database;

import java.util.HashMap;
import java.util.Map;
import model.Camp;
import model.CampInformation;
import model.Student;

public class CampDB {

    private Map<String, Camp> campDatabase;

    // Static variable reference of userDB
    // of type CampDB
    private static CampDB campDB = null;
    

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private CampDB() {
        campDatabase = new HashMap<>();
    }

    // Static method to create instance of Singleton(CampDB) class
    public static synchronized CampDB getInstance()
    {
        if (campDB == null)
            campDB = new CampDB();
 
        return campDB;
    }

    public HashMap<String, Camp> getAllCamps() {
        return new HashMap<>(campDatabase);
    }

    public Camp getCamp(String campID) {
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

    public CampInformation getCampDetails(String campID) {
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

    public HashMap<String, Student> getAttendeesForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            return new HashMap<>(camp.getAttendees());
        }
        return new HashMap<>();
    }

    public HashMap<String, Student> getCommitteeMembersForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            return new HashMap<>(camp.getCommittee());
        }
        return new HashMap<>();
    }

    public HashMap<String, Student> getAllStudentsForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            HashMap<String, Student> allStudents = new HashMap<>(camp.getAttendees());
            allStudents.putAll(camp.getCommittee());
            return allStudents;
        }
        return new HashMap<>();
    }
}
