import java.util.HashMap;
import java.util.Scanner;

import controller.CampOperationsController;
import controller.EnquiryController;
// import controller.StudentCampInteractionController;
// import controller.SuggestionController;
import controller.UserController;
import controller.StudentCampInteractionController;

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
import view.CreateCampView;



public class Main {
    private static User currentUser = null;

    // Initialise databases
    static UserDB userDB = UserDB.getInstance();
    static CampDB campDB = CampDB.getInstance();

    // Initialise controllers
    static UserController userController = new UserController();
    static CampOperationsController campOperationsController = new CampOperationsController();
    static StudentCampInteractionController studentCampInteractionController = new StudentCampInteractionController();

    // Initialise views
    static CreateCampView createCampView = new CreateCampView();

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
                                studentMenu(userID, currentUser);
                            } else {
                                staffMenu(userID, currentUser);
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

    private static void studentMenu(String userID, User user) {
        while (true) {
            System.out.println("Student Menu:");
            System.out.println("1. View Available Camps");
            System.out.println("2. Register for Camp");
            System.out.println("3. Submit Enquiry for a Camp");
            System.out.println("4. Submit Suggestion for a Camp");
            System.out.println("5. View Registered Camps");
            System.out.println("6. View Enquiry Replies");
            System.out.println("7. Withdraw from a Camp");
            System.out.println("8. Change Password");
            System.out.println("9. Logout");
            System.out.print("Enter choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            String userType = "student";
    
            switch (choice) {
                case 1:
                    // Display available camps
                    campOperationsController.viewCampsForUserType(userType);
                    break;
                case 2:
                    // Register for a camp
                    campOperationsController.viewCampsForUserType(userType);
                    System.out.println("Type the name of the camp you would like to register for!");
                    String selectedCampID = scanner.nextLine(); 
                    System.out.println("Would you like to register as a participant or a committee member?");
                    System.out.println("1. Participant");
                    System.out.println("2. Committee Member");
                    int roleChoice = scanner.nextInt();
                    if (roleChoice == 1){
                        userType = "student";
                    }
                    else if (roleChoice == 2){
                        userType = "committee";
                    }
                    else{
                        System.out.println("Invalid choice. Try again.");
                        break;
                    }
                    studentCampInteractionController.registerForCamp(userID, selectedCampID, userType);
                    break;
                case 3:
                    // Submit enquiry for a camp
                    break;
                case 4:
                    // Submit enquiry for a camp
                    break;
                case 5:
                    // View registered camps
                    break;
                case 6:
                    // View enquiry replies
                    break;
                case 7:
                    // Withdraw from a camp
                    break;
                case 8:
                    System.out.println("Please enter your new password:");
                    String newPassword = scanner.nextLine();
                    userController.changePassword(userID, newPassword);
                    System.out.println(user.getPassword());
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void staffMenu(String userID, User user) {
        
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
            String userType = "staff";
    
            switch (choice) {
                case 1:
                    System.out.println("Please enter your new password:");
                    String newPassword = scanner.nextLine();
                    userController.changePassword(userID, newPassword);
                    System.out.println(user.getPassword());
                    break;
                case 2:
                    // Create a new camp
                    CampInformation campInformation = createCampView.creatingCamp(user);
                    break;
                case 3:
                    // Edit an existing camp
                    System.out.println("Which camp would you like to edit?");
                    String campToEdit = scanner.nextLine();
                    campOperationsController.editCamp(campToEdit);
                    
                    break;
                case 4:
                    // View all camps
                    campOperationsController.viewCampsForUserType(userType);
                    break;
                case 5:
                    // View registered students for a camp
                    campOperationsController.viewCampsForUserType(userType);
                    System.out.println("Select a camp to view registered students for:");
                    String campToView = scanner.nextLine();

                    campOperationsController.viewRegisteredStudents(campToView);
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
