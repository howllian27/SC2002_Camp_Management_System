package view;

import java.util.List;

import database.StudentDB;
import model.Camp;
import model.Student;
/**
 * The ReportView class is responsible for displaying various types of reports and information.
 * It provides methods to display camp details, a list of students, and roles of student participants.
 *
 * @author Cheng Lin
 * @version 1.0
 */
public class ReportView {
    
    /**
     * Displays the details of a camp.
     * @param camp The Camp object containing the camp's information.
     */
    public void displayCampDetails(Camp camp) {
        System.out.println("Displaying details of the camp:");
        System.out.println(camp); // Implement a suitable toString() method in the Camp class
    }

    /**
     * Displays a list of students.
     * @param students A list of Student objects representing the students to be displayed.
     */
    public void displayStudents(List<StudentDB> students) {
        System.out.println("Displaying a list of students:");
        for (StudentDB student : students) {
            System.out.println(student); // Implement a suitable toString() method in the Student class
        }
    }

    /**
     * Displays the roles of student participants.
     */
    public void displayRoleParticipants(List<Student> students) {
        System.out.println("Displaying the roles of participants");
        System.out.println(roles);
    }
    
}
