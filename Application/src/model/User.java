package model;

/***
 * The {@code User} class represents a user in the system with a unique user ID, password and faculty.
 *
 * @author Shun Jie
 * @version 1.0
 */
public class User {
    private final String userID;
    private final Faculty faculty;
    private String password = "password";
    private final String name;

    /**
     * Constructs a new {@code User} object with default values.
     * The default user ID is "default," and the default faculty is "SCHOOL."
     */
    public User() {
        this.userID = "default";
        this.faculty = Faculty.SCHOOL;
        this.name = "";
    }

    /***
     * Constructs a new {@code User} object with the given user ID and faculty.
     *
     * @param userID The unique identifier of this user.
     * @param faculty The school that this user is part of.
     * @param name The name of this user.
     */
    public User(String userID, Faculty faculty, String name) {
        this.userID = userID;
        this.faculty = faculty;
        this.name = name;
    }

    /**
     * Constructs a new {@code User} object with the given user ID, password, faculty and name.
     *
     * @param userID   The user ID of this user.
     * @param password The user's password.
     * @param faculty  The faculty to which this user belongs.
     * @param name The name of this user.
     */
    public User(String userID, String password, Faculty faculty, String name) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
        this.name = name;
    }

    //Getter
    public String getID() { return this.userID; }
    public Faculty getFaculty() { return this.faculty; }
    public String getPassword() { return this.password; }
    public String getName() { return this.name; }

    //Setter
    public void setPassword(String password) { this.password = password; }
}
