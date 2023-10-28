package view;

import java.util.List;

public class ReportView {

    public void displayCampDetails(Camp camp) {
        System.out.println("Displaying details of the camp:");
        System.out.println(camp); // Implement a suitable toString() method in the Camp class
    }

    public void displayStudents(List<Student> students) {
        System.out.println("Displaying a list of students:");
        for (Student student : students) {
            System.out.println(student); // Implement a suitable toString() method in the Student class
        }
    }
    public void displayRoleParticipants() {
        System.out.println("Displaying the roles of participants");
        System.out.println(roles);
    }
    
}
