package data;

public class Student extends UserDB {

    private String studentID; 

    public Student(String name, String email, String facultyInfo, String studentID) {
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