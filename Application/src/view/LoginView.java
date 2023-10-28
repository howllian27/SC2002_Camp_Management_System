package view;
import java.util.Scanner;


public class LoginView {
    public void displayLogin() {
        // Code to display the login page
        
        System.out.println("Welcome to CAMs");
        System.out.println("  ____       _           ______ _    _ ");
        System.out.println(" |  _ \\     | |          |  ____| |  | |");
        System.out.println(" | |_) | ___| |_   _     | |__  | |__| |");
        System.out.println(" |  _ < / _ \\ | | | |    |  __| |  __  |");
        System.out.println(" | |_) |  __/ | |_| |    | |____| |  | |");
        System.out.println(" |____/ \\___|_|\\__, |    |______|_|  |_|");
        System.out.println("               __/ |                  ");
        System.out.println("              |___/                   ");
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
        
        System.out.println("Password Change Successful");
        
    }
}