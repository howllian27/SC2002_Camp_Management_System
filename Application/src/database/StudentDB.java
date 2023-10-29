package database;

public class StudentDB extends UserDB {

    private String studentID; 

    public StudentDB(String name, String email, String facultyInfo, String studentID) {
        super(name, email, facultyInfo);
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

}