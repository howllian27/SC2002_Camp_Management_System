package database;

import java.util.Date;
import java.util.HashMap;
import model.Camp;

/***
 *
 * @author Shun Jie
 * @version 1.1
 */
public class CampDB { 
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