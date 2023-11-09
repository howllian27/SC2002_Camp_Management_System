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
public class UserController implements BaseController{
    UserDB userDB;
    private LoginView loginView;

    // Constructor
    public UserController() {
        setMasterVariables();
    }

    // Set master variables
    @Override
    public void setMasterVariables() {
        this.userDB = UserDB.getInstance(); // Assuming UserDB is a class that handles user data.
        this.loginView = new LoginView(); // Assuming LoginView is a class for displaying login-related UI.
    }

    /**
     * Validates user credentials against the database.
     * If the credentials are valid, it displays a successful login message.
     * Otherwise, it displays an error message.
     * 
     * @param userID The user's ID.
     * @param password The user's password.
     */
    public User loginUser(String userID, String password, boolean isStudent) {
        User user = userDB.getUser(userID, isStudent);
        if (user != null && user.getPassword().equals(password)) {
            loginView.displayLoginSuccess();
            return user;
        } else {
            loginView.displayLoginError();
            return null;
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
            loginView.displayPasswordChangeError();
        }
    }
}