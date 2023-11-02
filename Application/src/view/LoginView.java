package view;
import java.util.Scanner;

/**
 * The LoginView class is responsible for displaying login-related pages and messages.
 * It provides methods to display the login page, login success message, login error message, and password change success message.
 *
 * @author Cheng lin
 * @version 1.0
 */

public class LoginView {

    /**
     * Displays the login page and prompts the user to enter their user ID and password.
     */
    public void displayLogin() {
        // Display the login page
                System.out.println("\n+------------------------------------------------------------+");
                System.out.println("|                                                             |");
                System.out.println("|                         LOGIN PORTAL                        |");
                System.out.println("|                                                             |");
                System.out.println("|   Please enter your current userID and password to proceed. |");
                System.out.println("|                                                             |");
                System.out.println("|                                                             |");
                System.out.println("+------------------------------------------------------------+\n");
        System.out.println("Welcome to CAMs");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
    }

    /**
     * Displays a message indicating that the login was successful.
     */
    public void displayLoginSuccess() {
        System.out.println("Login Successful");
        
    }

    /**
     * Displays a message indicating that the login was unsuccessful due to invalid credentials.
     */
    public void displayLoginError() {
        System.out.println("Login Failed. Please try again.");
        
    }

    /**
     * Displays a message indicating that you can change the password.
     */
    public void changeUserPassword(){
                System.out.println("\n+------------------------------------------------------------+");
                System.out.println("|                                                             |");
                System.out.println("|                 PASSWORD CHANGE PORTAL                      |");
                System.out.println("|                                                             |");
                System.out.println("|    Please re-enter your current password to proceed.        |");
                System.out.println("|                                                             |");
                System.out.println("|       To return to the main menu, simply enter '0'.         |");
                System.out.println("|                                                             |");
                System.out.println("+------------------------------------------------------------+\n");
    }
    
    /**
     * Displays a message indicating that the password change was successful.
     */
    public void displayPasswordChangeSuccess() {
        System.out.println("Password Change Successful");
    }

    /**
     * Displays a message indicating that the password change was unsuccessful.
     */
    public void displayPasswordChangeError() {
        System.out.println("Password Change Failed. Please try again.");
    }
}