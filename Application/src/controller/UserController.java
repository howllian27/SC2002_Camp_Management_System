package controller;

import view.LoginView;
import model.User;
import database.UserDB;

/**
 * Controller responsible for user-related operations.
 */
public class UserController {

    private LoginView loginView = new LoginView();
    private UserDB userDB = new UserDB();

    /**
     * Handles the user login process.
     */
    public void loginUser() {
        loginView.displayLogin();
        // Get user input from view (for simplicity, let's assume we have it here)
        String userID = "sampleID"; // Replace with actual input
        String password = "samplePassword"; // Replace with actual input

        // Validate user credentials
        User user = userDB.getUser(userID);
        if (user != null && user.getPassword().equals(password)) {
            loginView.displayLoginSuccess();
        } else {
            loginView.displayLoginError();
        }
    }

    /**
     * Allows a user to change their password.
     * @param userID The user's ID.
     * @param newPassword The new password.
     */
    public void changePassword(String userID, String newPassword) {
        userDB.updatePassword(userID, newPassword);
        // Display success message
        loginView.displayPasswordChangeSuccess();
    }
}
