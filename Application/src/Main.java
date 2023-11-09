import java.util.HashMap;
import java.util.Scanner;

import controller.CampOperationsController;
import controller.EnquiryController;
// import controller.StudentCampInteractionController;
// import controller.SuggestionController;
import controller.UserController;

import database.CampDB;
import database.UserDB;
import database.UserType;

import helper.FileHelper;

import model.Camp;
import model.CampInformation;
import model.Faculty;
import model.Staff;
import model.Student;
import model.User;



public class Main {
    private static User currentUser = null;

    static UserDB userDB = UserDB.getInstance();
    static UserController userController = new UserController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (currentUser == null) {
                System.out.println("Welcome to CAMs!");
                System.out.println("1. Login");
                System.out.println("2. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter User ID: ");
                        String userID = scanner.nextLine();
                        System.out.print("Enter Password: ");
                        String password = scanner.nextLine();
                        System.out.print("Are you a student? (Y/N): ");
                        String isStudent = scanner.nextLine();
                        boolean isStudentBool = true;
                        
                        if (isStudent.equals("Y") || isStudent.equals("y")){
                            isStudentBool = true;
                        }
                        else if (isStudent.equals("N") || isStudent.equals("n")){
                            isStudentBool = false;
                        }

                        // else{
                        //     System.out.println("Invalid input. Try again.");
                        //     break;
                        // }

                        System.out.println(isStudentBool);
                        
                        User currentUser = userController.loginUser(userID, password, isStudentBool);
                        if (currentUser == null) {
                            System.out.println("Invalid credentials. Try again.");
                        } else {
                            if (isStudentBool) {
                                studentMenu();
                            } else {
                                staffMenu();
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                // if (currentUser instanceof StudentDB) {
                //     studentMenu(scanner);
                // } else if (currentUser instanceof StaffDB) {
                //     staffMenu(scanner);
                // } else {
                //     System.out.println("Unknown user type. Logging out.");
                //     currentUser = null;
                // }
            }
        }
    }

    private static void studentMenu() {
        while (true) {
            System.out.println("Student Menu:");
            System.out.println("1. View Available Camps");
            System.out.println("2. Register for Camp");
            System.out.println("3. Submit Enquiry for a Camp");
            System.out.println("4. View Registered Camps");
            System.out.println("5. View Enquiry Replies");
            System.out.println("6. Withdraw from a Camp");
            System.out.println("7. Change Password");
            System.out.println("8. Logout");
            System.out.print("Enter choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
    
            switch (choice) {
                case 1:
                    // Display available camps
                    break;
                case 2:
                    // Register for a camp
                    break;
                case 3:
                    // Submit enquiry for a camp
                    break;
                case 4:
                    // View registered camps
                    break;
                case 5:
                    // View enquiry replies
                    break;
                case 6:
                    // Withdraw from a camp
                    break;
                case 7:
                    // Change password
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void staffMenu() {
        
        while (true) {
            System.out.println("Staff Menu:");
            System.out.println("1. Change Password");
            System.out.println("2. Create a New Camp");
            System.out.println("3. Edit an Existing Camp");
            System.out.println("4. View All Camps");
            System.out.println("5. View Registered Students for a Camp");
            System.out.println("6. View Camp Detail Suggestions");
            System.out.println("7. Accept/Reject Suggestions");
            System.out.println("8. Generate Reports");
            System.out.println("9. Logout");
            System.out.print("Enter choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
    
            switch (choice) {
                case 1:
                    // Change password
                    // changeUserPassword()
                    break;
                case 2:
                    // Create a new camp
                    break;
                case 3:
                    // Edit an existing camp
                    break;
                case 4:
                    // View all camps
                    break;
                case 5:
                    // View registered students for a camp
                    break;
                case 6:
                    // View camp detail suggestions
                    break;
                case 7:
                    // Accept/Reject suggestions
                    break;
                case 8:
                    // Generate reports
                    break;
                case 9:
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
