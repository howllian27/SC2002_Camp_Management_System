package view;
import java.util.Scanner;


public class LoginView {
    public void displayLogin() {
        // Display the login page

        System.out.println("Welcome to CAMs");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
    }
    public void displayLoginSuccess() {
        // Display to users that the credentials are valid
        System.out.println("Login Successful");
        
    }
    
    public void displayLoginError() {
        // Display to users that the credentials are invalid
        System.out.println("Login Failed. Please try again.");
        
    }
    
    public void displayPasswordChangeSuccess() {
        // Display to users that they have changed their passwords successfully
        System.out.println("Password Change Successful");
        
    }
}