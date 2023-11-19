package database;

import java.util.HashMap;
import model.Camp;
import model.CampInformation;
import model.Student;

/**
 * The {@code CampDB} class represents a database for managing camps.
 * It provides methods to retrieve, add, update, and delete camps, as well as retrieve attendees and committee members for a camp.
 * This class follows the Singleton pattern to ensure a single instance throughout the application.
 *
 * @author Wei Hong
 * @version 1.0
 */
public class CampDB {

    private final HashMap<String, Camp> campDatabase;

    // Static variable reference of userDB
    // of type CampDB
    private static CampDB campDB = null;
    

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private CampDB() {
        campDatabase = new HashMap<>();
    }

    /**
     * Static method to create an instance of the Singleton (CampDB) class.
     *
     * @return The single instance of CampDB.
     */
    public static synchronized CampDB getInstance()
    {
        if (campDB == null)
            campDB = new CampDB();
 
        return campDB;
    }

    /**
     * Retrieves a copy of all camps in the database.
     *
     * @return A HashMap containing all camps.
     */
    public HashMap<String, Camp> getAllCamps() {
        return new HashMap<>(campDatabase);
    }

    /**
     * Retrieves a specific camp based on its ID.
     *
     * @param campID The ID of the camp to retrieve.
     * @return The Camp object or null if not found.
     */
    public Camp getCamp(String campID) {
        Camp camp = campDatabase.get(campID);
        if (camp != null){
            return camp;
        } else {
            return null;
        }
    }

    /**
     * Adds a new camp to the database.
     *
     * @param campID The ID of the camp to add.
     * @param camp The Camp object to add.
     * @return True if the camp is added successfully, false if the camp ID already exists.
     */
    public boolean addCamp(String campID, Camp camp) {
        if (!campDatabase.containsKey(campID)) {
            campDatabase.put(campID, camp);
            return true;
        }
        return false;
    }

    /**
     * Retrieves camp details based on its ID.
     *
     * @param campID The ID of the camp.
     * @return The CampInformation object or null if the camp is not found.
     */
    public CampInformation getCampDetails(String campID) {
        Camp camp = campDatabase.get(campID);
        if (camp != null) {
            return camp.getCampInformation();
        }
        return null;
    }

    /**
     * Updates an existing camp with new information.
     *
     * @param campID The ID of the camp to update.
     * @param updatedCamp The updated Camp object.
     * @return True if the camp is updated successfully, false if the camp ID does not exist.
     */
    public boolean updateCamp(String campID, Camp updatedCamp) {
        if (campDatabase.containsKey(campID)) {
            campDatabase.put(campID, updatedCamp);
            return true;
        }
        return false;
    }

    /**
     * Deletes a camp based on its ID.
     *
     * @param campID The ID of the camp to delete.
     * @return True if the camp is deleted successfully, false if the camp ID does not exist.
     */
    public boolean deleteCamp(String campID) {
        return campDatabase.remove(campID) != null;
    }

    /**
     * Retrieves a copy of attendees for a specific camp.
     *
     * @param campID The ID of the camp.
     * @return A HashMap containing attendees.
     */
    public HashMap<String, Student> getAttendeesForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            return new HashMap<>(camp.getAttendees());
        }
        return new HashMap<>();
    }

    /**
     * Retrieves a copy of committee members for a specific camp.
     *
     * @param campID The ID of the camp.
     * @return A HashMap containing committee members.
     */
    public HashMap<String, Student> getCommitteeMembersForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            return new HashMap<>(camp.getCommittee());
        }
        return new HashMap<>();
    }

    /**
     * Retrieves a copy of all students (attendees and committee members) for a specific camp.
     *
     * @param campID The ID of the camp.
     * @return A HashMap containing all students.
     */
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
