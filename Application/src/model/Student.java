package model;

import java.util.HashSet;
import java.util.Set;

/***
 * The `Student` class represents a student user in the system, including their
 * registration status for various camps and committee memberships.
 *
 * @author Shun Jie
 * @version 1.0
 */
public class Student extends User {
    private final Set<Camp> previouslyRegisteredCamps;
    private final Set<Camp> registeredCamps; //Camps where student is a member
    private Camp registeredCommitteeCamp;

    /***
     * Constructs a new `Student` object with the given user ID and faculty.
     * @param userID The user ID of the student.
     * @param faculty The faculty to which the student belongs.
     */
    Student(String userID, Faculty faculty) {
        super(userID, faculty);
        previouslyRegisteredCamps = new HashSet<Camp>();
        registeredCamps = new HashSet<Camp>();
    }

    /***
     * Constructs a new `Student` object with the given user ID, password and  faculty.
     * @param userID The user ID of the student.
     * @param password The password of the student.
     * @param faculty The faculty to which the student belongs.
     */
    Student(String userID, String password, Faculty faculty) {
        super(userID, password, faculty);
        previouslyRegisteredCamps = new HashSet<Camp>();
        registeredCamps = new HashSet<Camp>();
    }

    //Getters
    public Camp getRegisteredCommitteeCamp() { return this.registeredCommitteeCamp; }
    public Set<Camp> getRegisteredCamps() { return this.registeredCamps; }
    public Set<Camp> getPreviouslyRegisteredCamps() { return this.previouslyRegisteredCamps; }

    //Methods

    /***
     * Adds a camp to the set of camps where the student is registered, simultaneously marking
     * it as previously registered.
     * @param camp The camp to be added.
     * @return `true` if the camp is added successfully, `false` if its already registered.
     */
    public boolean addCamp(Camp camp) {
        if(registeredCamps.add(camp)) {
            previouslyRegisteredCamps.add(camp);
            return true;
        }
        return false;
    }

    /***
     * Adds a camp as the student's committee camp.
     * @param camp The camp to be set as the committee camp.
     * @return `true` if the camp is set as the committee camp successfully, `false` if the student is already a committee member in a camp.
     */
    public boolean addCommitteeCamp(Camp camp) {
        if(registeredCommitteeCamp != null) return false;
        registeredCommitteeCamp = camp;
        return true;
    }

    /***
     * Removes a camp from the set of camps where the student is registered.
     * @param camp The camp to be removed.
     * @return `true` if the camp is removed successfully, `false` if it's not found in the registered camps.
     */
    public boolean removeCamp(Camp camp) {
        return registeredCamps.remove(camp);
    }

    /***
     * Removes the student's committee camp.
     * @return `true` if the committee camp is removed successfully, `false` if the student is not part of a camp committee.
     */
    public boolean removeCommitteeCamp() {
        if(registeredCommitteeCamp == null) return false;
        registeredCommitteeCamp = null;
        return true;
    }
}
