package view;
import model.Student;
/**
 * The {@code UserProfileView} class is responsible for displaying user profiles.
 * It provides a method to display the profile of a user, including their user ID and faculty information.
 *
 * @author Cheng Lin
 * @version 1.0
 */

public class UserProfileView {
    /**
     * Displays the profile of a user, including user ID and faculty information.
     * @param student The User object containing the user's profile information.
     */
    public static void displayStudentProfile(Student student) {
        System.out.println("\n+------------------------------------------------------------+");
        System.out.println("|                                                             |");
        System.out.println("|                      YOUR USER PROFILE                      |");
        System.out.println("|                                                             |");
        System.out.println("|                                                             |");
        System.out.println("|        To return to the main menu, simply enter '0'.        |");
        System.out.println("|                                                             |");
        System.out.println("+------------------------------------------------------------+\n");
        // Display the profile of a user
        System.out.println("User Profile");
        System.out.println("User ID: " + student.getID());
        System.out.println("Faculty: " + student.getFaculty());
        if(student.getCampCommitteeMemberStatus()){
            System.out.println("Committee Member Status: True"  );
        }else{
            System.out.println("Committee Member Status: False"  );
        }
    }
}
