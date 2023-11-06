package model;

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
    private final Set<Camp> createdCamps;

    /***
     * Constructs a new `Staff` class with the given user ID and faculty.
     * @param userID The staff's user ID.
     * @param faculty The faculty to which the staff belongs to.
     */
    public Staff(String userID, Faculty faculty) {
        super(userID, faculty);
        createdCamps = new HashSet<Camp>();
    }

    /***
     * Constructs a new `Staff` class with the given user ID, password and faculty.
     * @param userID The staff's user ID.
     * @param password The password of the staff.
     * @param faculty The faculty to which the staff belongs to.
     */
    public Staff(String userID, String password, Faculty faculty) {
        super(userID, password, faculty);
        createdCamps = new HashSet<Camp>();
    }

    // Create a new camp
    public void create(Camp camp) {
        createdCamps.add(camp);
    }

    // Edit an existing camp
    public void edit(Camp camp) {
        // Implementation for editing a camp
    }

    // Delete a camp
    public void delete(Camp camp) {
        createdCamps.remove(camp);
    }

    // Toggle the visibility of a camp
    public void toggle(Camp camp) {
        // Implementation for toggling camp visibility
    }

    // View all camps
    public Set<Camp> viewAllCamps() {
        // Implementation to return all camps
        return null;
    }

    // View camps created by this staff
    public Set<Camp> viewMyCamps() {
        return createdCamps;
    }

    // View student enquiries for a specific camp
    public void viewStudentEnquiries(Camp camp) {
        // Implementation to view student enquiries for a camp
    }

    // Reply to student enquiries
    // public void replyStudentEnquiries(Enquiry enquiry, String reply) {
    //     // Implementation to reply to a student enquiry
    // }

    // View suggestions for a specific camp
    public void viewSuggestions(Camp camp) {
        // Implementation to view suggestions for a camp
    }

    // Approve suggestions for a specific camp
    // public void approveSuggestions(Suggestion suggestion) {
    //     // Implementation to approve a suggestion
    // }

    // Generate a list of students attending a specific camp
    public void generateList(Camp camp) {
        // Implementation to generate a list of students for a camp
    }

    // Generate a report for a specific camp
    public void generateReport(Camp camp) {
        // Implementation to generate a report for a camp
    }
}
