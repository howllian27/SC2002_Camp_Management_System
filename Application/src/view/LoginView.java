package view;
import java.util.Scanner;


public class LoginView {
    public void displayLogin() {
        // Code to display the login page
        System.out.println("Welcome to CAMs");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
    }
    public void displayLoginSuccess() {
        
        System.out.println("Login Successful");
        
    }
    
    public void displayLoginError() {
        
        System.out.println("Login Failed. Please try again.");
        
    }
    
    public void displayPasswordChangeSuccess() {
        System.out.println("Please enter you new password:");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        System.out.println("Password Change Successful");
        
    }
}