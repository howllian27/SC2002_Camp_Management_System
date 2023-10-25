import java.util.Objects;

public class User {
    private final String userID;
    private final Faculty faculty;
    private String password = "password";

    User() {
        this.userID = "default";
        this.password = password;
        this.faculty = Faculty.SCHOOL;
    }

    User(String userID, Faculty faculty) {
        this.userID = userID;
        this.faculty = faculty;
    }

    User(String userID, String password, Faculty faculty) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
    }

    public String getID() { return this.userID; }
    public Faculty getFaculty() { return this.faculty; }

    public boolean authenticate(String password) {
        return Objects.equals(this.password, password);
    }
    public void changePassword(String password) { this.password = password; }

}
