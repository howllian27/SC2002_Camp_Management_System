package model;

import java.util.HashMap;


/***
 * The {@code Student} class represents a student user in the system, including their
 * registration status for various camps and committee memberships.
 *
 * @author Shun Jie
 * @version 1.0
 */
public class Student extends User {
    private final HashMap<String, Camp> previouslyRegisteredCamps;
    private final HashMap<String, Camp> registeredCamps; //Camps where student is a member
    private Camp registeredCommitteeCamp;
    private boolean isCampCommitteeMember = false;
    private int points = 0;

    /***
     * Constructs a new {@code Student} object with the given user ID and faculty.
     *
     * @param userID The user ID of the student.
     * @param faculty The faculty to which the student belongs.
     * @param name The name of this student.
     */
    public Student(String userID, Faculty faculty, String name) {
        super(userID, faculty, name);
        previouslyRegisteredCamps = new HashMap<String, Camp>();
        registeredCamps = new HashMap<String, Camp>();
    }

    /***
     * Constructs a new {@code Student} object with the given user ID, password and  faculty.
     *
     * @param userID The user ID of the student.
     * @param password The password of the student.
     * @param faculty The faculty to which the student belongs.
     * @param name The name of this student.
     */
    public Student(String userID, String password, Faculty faculty, String name) {
        super(userID, password, faculty, name);
        previouslyRegisteredCamps = new HashMap<String, Camp>();
        registeredCamps = new HashMap<String, Camp>();
    }

    //Getters
    public Camp getRegisteredCommitteeCamp() { return this.registeredCommitteeCamp; }
    public HashMap<String, Camp> getRegisteredCamps() { return this.registeredCamps; }
    public HashMap<String, Camp> getPreviouslyRegisteredCamps() { return this.previouslyRegisteredCamps; }
    public boolean getCampCommitteeMemberStatus() { return this.isCampCommitteeMember; }
    public int getPoints() { return this.points; }

    // Setters
    public void setCampCommitteeMember() { this.isCampCommitteeMember = true; }

    //Methods

    /***
     * Adds a camp to the set of camps where the student is registered, simultaneously marking
     * it as previously registered.
     *
     * @param campId The unique identifier of a camp.
     * @param camp The camp object of the campId.
     * @return {@code true} if the camp is added successfully, {@code false} if it is already registered.
     */
    public boolean addCamp(String campId, Camp camp) {
        if (!registeredCamps.containsKey(campId)) {
            registeredCamps.put(campId, camp);
            previouslyRegisteredCamps.put(campId, camp);
            return true; // Successfully added a new camp
        }
        return false; // Camp already exists
    }

    /***
     * Adds a camp as the student's committee camp.
     *
     * @param camp The camp to be set as the committee camp.
     * @return {@code true} if the camp is set as the committee camp successfully, {@code false} if the student is already a committee member in a camp.
     */
    public boolean addCommitteeCamp(Camp camp) {
        if(registeredCommitteeCamp != null) return false;
        registeredCommitteeCamp = camp;
        return true;
    }

    /***
     * Removes a camp from the set of camps where the student is registered.
     *
     * @param campId The campId to be removed.
     * @return {@code true} if the camp is removed successfully, {@code false} if it's not found in the registered camps.
     */
    public boolean removeCamp(String campId) {
        return registeredCamps.remove(campId) != null;
    }

    /***
     * Removes the student's committee camp.
     *
     * @return {@code true} if the committee camp is removed successfully, {@code false} if the student is not part of a camp committee.
     */
    public boolean removeCommitteeCamp() {
        if(registeredCommitteeCamp == null) return false;
        registeredCommitteeCamp = null;
        return true;
    }

    /**
     * Adds points to the student's account.
     *
     * @param points The numbers of points to add to this {@code Student}
    */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Removes points from the student's account.
     * 
     * @param oldCampId
     * @param newCampId
    */
    public void changeCampId(String oldCampId, String newCampId) {
        Camp camp = registeredCamps.get(oldCampId);
        registeredCamps.remove(oldCampId);
        registeredCamps.put(newCampId, camp);

        camp = previouslyRegisteredCamps.get(oldCampId);
        previouslyRegisteredCamps.remove(oldCampId);
        previouslyRegisteredCamps.put(newCampId, camp);
    }
}
