package controller;

import database.UserDB;
import model.User;
import view.LoginView;

/**
 * The UserController class is responsible for handling user-related operations.
 * It provides methods for user login and password change functionalities.
 * This class interacts with the UserDB for database operations and uses LoginView for displaying relevant messages.
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-10-28
 */
public class UserController {

    /** The UserDB object for interacting with the user database. */
    private UserDB userDB = new UserDB();

    /** The LoginView object for displaying user-related messages. */
    private LoginView loginView = new LoginView();

    /**
     * Validates user credentials against the database.
     * If the credentials are valid, it displays a successful login message.
     * Otherwise, it displays an error message.
     * 
     * @param userID The user's ID.
     * @param password The user's password.
     */
    public void loginUser(String userID, String password) {
        User user = userDB.getUser(userID);
        if (user != null && user.getPassword().equals(password)) {
            loginView.displayLoginSuccess();
        } else {
            loginView.displayLoginError();
        }
    }

    /**
     * Allows a user to change their password.
     * If the password is successfully updated in the database, it displays a success message.
     * Otherwise, it handles the error or displays a failure message.
     * 
     * @param userID The user's ID.
     * @param newPassword The new password to be set for the user.
     */
    public void changePassword(String userID, String newPassword) {
        boolean isUpdated = userDB.updatePassword(userID, newPassword);
        if (isUpdated) {
            loginView.displayPasswordChangeSuccess();
        } else {
            // Handle error or display a message indicating the password change failed.
            // This can be done using another method in the LoginView or using a general error view.
        }
    }
}