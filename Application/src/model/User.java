package model;

import java.util.Objects;

/***
 * The `User` class represents a user in the system with a unique user ID, password and faculty.
 *
 * @author Ruin9999
 * @version 1.0
 */
public class User {
    private final String userID;
    private final Faculty faculty;
    private String password = "password";

    /**
     * Constructs a new `User` object with default values.
     * The default user ID is "default," and the default faculty is "SCHOOL."
     */
    User() {
        this.userID = "default";
        this.password = password;
        this.faculty = Faculty.SCHOOL;
    }

    /**
     * Constructs a new `User` object with the given user ID and faculty.
     *
     * @param userID   The user ID of the user.
     * @param faculty  The faculty to which the user belongs.
     */
    User(String userID, Faculty faculty) {
        this.userID = userID;
        this.faculty = faculty;
    }

    /**
     * Constructs a new `User` object with the given user ID, password, and faculty.
     *
     * @param userID   The user ID of the user.
     * @param password The user's password.
     * @param faculty  The faculty to which the user belongs.
     */
    User(String userID, String password, Faculty faculty) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
    }

    public String getID() { return this.userID; }
    public Faculty getFaculty() { return this.faculty; }

    /**
     * Authenticates the user by comparing the provided password with the stored password.
     *
     * @param password The password to be authenticated.
     * @return `true` if the provided password matches the stored password, `false` otherwise.
     */
    public boolean authenticate(String password) {
        return Objects.equals(this.password, password);
    }

    /**
     * Changes the user's password to the provided value.
     *
     * @param password The new password to be set for the user.
     */
    public void changePassword(String password) { this.password = password; }

}
