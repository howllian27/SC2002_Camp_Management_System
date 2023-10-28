package view;
import model.User;
/**
 * The UserProfileView class is responsible for displaying user profiles.
 * It provides a method to display the profile of a user, including their user ID and faculty information.
 */

public class UserProfileView {
    /**
     * Displays the profile of a user, including user ID and faculty information.
     *
     * @param user The User object containing the user's profile information.
     */
    public void displayUserProfile(User user) {
        // Display the profile of a user
        System.out.println("User Profile");
        System.out.println("User ID: " + user.getUserId());
        System.out.println("Faculty: " + user.getFaculty());
        
    }
}
