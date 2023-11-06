package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/***
 * The `Staff` class represents a staff user in the system, including the camps that
 * they have created.
 *
 * @author Shun Jie
 * @version 1.0
 */
public class Staff extends User {
    private final HashMap<String, Camp> createdCamps;

    /***
     * Constructs a new `Staff` class with the given user ID and faculty.
     * @param userID The staff's user ID.
     * @param faculty The faculty to which the staff belongs to.
     */
    public Staff(String userID, Faculty faculty) {
        super(userID, faculty);
        createdCamps = new HashMap<>();
    }

    /***
     * Constructs a new `Staff` class with the given user ID, password and faculty.
     * @param userID The staff's user ID.
     * @param password The password of the staff.
     * @param faculty The faculty to which the staff belongs to.
     */
    public Staff(String userID, String password, Faculty faculty) {
        super(userID, password, faculty);
        createdCamps = new HashMap<>();
    }

    //Getters
    /***
     * Gets a HashMap of camps that were created but the staff.
     * @return A HashMap of the camps created by this staff object
     */
    public HashMap<String, Camp> getCamps() { return this.createdCamps; }

    /***
     * Adds a camp to the staff's created camps list
     * @param campId The camp Id.
     * @param camp The created camp object.
     */
    public void addCamp(String campId, Camp camp) {
        createdCamps.put(campId, camp);
    }

    /***
     * Removes a camp from the staff's created camp list.
     * @param campId The camp Id.
     */
    public void removeCamp(String campId) {
        createdCamps.remove(campId);
    }
}
