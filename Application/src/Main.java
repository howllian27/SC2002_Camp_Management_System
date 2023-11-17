import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import controller.CampOperationsController;
import controller.EnquiryController;
// import controller.StudentCampInteractionController;
// import controller.SuggestionController;
import controller.UserController;
import controller.StudentCampInteractionController;
import controller.SuggestionController;
import controller.EnquiryController;
import controller.ReportController;

import database.CampDB;
import database.UserDB;
import model.Camp;
import model.CampInformation;
import model.Staff;
import model.Student;
import model.User;
import view.CampListView;
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
    static EnquiryController enquiryController = new EnquiryController();
    static SuggestionController suggestionController = new SuggestionController();
    static ReportController reportController = new ReportController();

    // Initialise views
    static CreateCampView createCampView = new CreateCampView();
    static CampListView campListView = new CampListView();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (currentUser == null) {
                System.out.println("Welcome to CAMs!");
                System.out.println("1. Login");
                System.out.println("2. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

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
            System.out.println("");
            System.out.println("Student Menu:");
            System.out.println("1. View Available Camps");
            System.out.println("2. Register for Camp");
            System.out.println("3. Submit Enquiry for a Camp");
            System.out.println("4. Submit Suggestion for a Camp");
            System.out.println("5. View Registered Camps");
            System.out.println("6. View/Edit/Delete/Reply to Enquiries");
            System.out.println("7. View/Edit/Delete Suggestions");
            System.out.println("8. View Enquiry Replies");
            System.out.println("9. Withdraw from a Camp");
            System.out.println("10. Change Password");
            System.out.println("11. Logout");
            System.out.print("Enter choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            System.out.println("");
            scanner.nextLine();
            String userType = "student";
            Student student = (Student) userDB.getUser(userID, true);

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
                    campOperationsController.viewCampsForUserType(userType);
                    System.out.println("Type the name of the camp you would like to submit an enquiry for!");
                    String campID = scanner.nextLine();
                    if (campDB.getCamp(campID) == null){
                        System.out.println("Invalid camp name. Try again.");
                        break;
                    }
                    System.out.println("Type the enquiry you would like to make!");
                    String enquiry = scanner.nextLine();
                    enquiryController.submitEnquiry(userID, campID, enquiry);

                    break;
                case 4:
                    // Submit suggestion for a camp
                    if (student.getCampCommitteeMemberStatus() == false){
                        System.out.println("You are not a committee member, so you can't make suggestions!");
                        break;
                    }

                    Camp camp = student.getRegisteredCommitteeCamp();
                    System.out.println("Your committee camp name is: " + camp.getName() + "\n");

                    suggestionController.submitSuggestion(student, camp.getName());

                    break;
                case 5:
                    // View registered camps
                    HashMap<String, Camp> registeredCamps = student.getRegisteredCamps();
                    List <Camp> camps = new ArrayList<Camp> (registeredCamps.values());
                    campListView.displayCampsForStudent(camps);
                    break;
                case 6:
                    // View/Edit/Delete my enquiries
                    System.out.println("Please type the number of the action you would like to perform.");
                    System.out.println("View my enquiries:");
                    enquiryController.viewEnquiriesByStudent(userID);
                    System.out.println("1. Edit my enquiries");
                    System.out.println("2. Delete my enquiries");
                    System.out.println("3. Reply to enquiries (Camp Committee only)");
                    int enquiryChoice = scanner.nextInt();

                    switch (enquiryChoice) {
                        case 1:
                            // Edit my enquiries
                            enquiryController.viewEnquiriesByStudent(userID);
                            System.out.println("Type the enquiry you would like to edit!");
                            int enquiryToEditIndex = scanner.nextInt();
                            enquiryController.checkIfEnquiryReplied(enquiryToEditIndex, userID);
                            scanner.nextLine();
                            System.out.println("Type the new enquiry you would like to make!");
                            String newEnquiry = scanner.nextLine();
                            enquiryController.editEnquiry(enquiryToEditIndex, newEnquiry, userID);
                            break;
                        case 2:
                            // Delete my enquiries
                            enquiryController.viewEnquiriesByStudent(userID);
                            System.out.println("Type the number of the enquiry you would like to delete!");
                            int enquiryToDeleteIndex = scanner.nextInt();
                            enquiryController.checkIfEnquiryReplied(enquiryToDeleteIndex, userID);
                            enquiryController.deleteEnquiry(enquiryToDeleteIndex, userID);
                            break;
                        case 3:
                            // Reply to enquiries (Camp Committee only)
                            if (student.getCampCommitteeMemberStatus() == false){
                                System.out.println("You are not a committee member!");
                                break;
                            }
                            String campIdToReply = student.getRegisteredCommitteeCamp().getName();
                            System.out.println("Your committee camp name is: " + campIdToReply);
                            enquiryController.viewEnquiriesByCamp(campIdToReply);;
                            System.out.println("Type the number of the enquiry you would like to reply to!");
                            int enquiryToReplyIndex = scanner.nextInt();
                            scanner.nextLine();
                            enquiryController.checkIfEnquiryReplied(enquiryToReplyIndex, userID);
                            System.out.println("Type the reply you would like to make!");
                            String reply = scanner.nextLine();
                            enquiryController.replyToEnquiryAsCommittee(enquiryToReplyIndex, reply, campIdToReply, userID);
                        default:
                            System.out.println("Invalid choice. Try again.");
                            break;
                    }
                    enquiryController.viewEnquiriesByStudent(userID);
                    break;
                case 7:
                    // View/Edit/Delete my suggestions
                    System.out.println("View my suggestions:");
                    if (!suggestionController.viewIndivSuggestions(userID, student)) break;
                    System.out.println("\n Please type the number of the action you would like to perform.");
                    System.out.println("1. Edit my suggestions");
                    System.out.println("2. Delete my suggestions");
                    int suggestionChoice = scanner.nextInt();

                    switch (suggestionChoice) {
                        case 1:
                            // Edit my suggestions
                            suggestionController.editSuggestion(student);
                            break;
                        case 2:
                            // Delete my suggestions
                            suggestionController.deleteSuggestion(student);
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                            break;
                    }
                case 8:
                    // View my enquiry replies
                    break;
                case 9:
                    // Withdraw from a camp
                    campOperationsController.viewCampsForUserType(userType);
                    System.out.println("Type the name of the camp you would like to withdraw from.");
                    String withdrawCampID = scanner.nextLine(); 
                    studentCampInteractionController.withdrawFromCamp(userID, withdrawCampID);
                    break;
                case 10:
                    System.out.println("Please enter your new password:");
                    String newPassword = scanner.nextLine();
                    userController.changePassword(userID, newPassword);
                    System.out.println(user.getPassword());
                    break;
                case 11:
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void staffMenu(String userID, User user) {
        
        while (true) {
            System.out.println("");
            System.out.println("Staff Menu:");
            System.out.println("1. Change Password");
            System.out.println("2. Create a New Camp");
            System.out.println("3. Edit an Existing Camp");
            System.out.println("4. View All Camps");
            System.out.println("5. View Registered Students for a Camp");
            System.out.println("6. View/Reply to Enquiries for a Camp");
            System.out.println("7. Accept/Reject Suggestions");
            System.out.println("8. Generate Reports");
            System.out.println("9. Logout");
            System.out.print("Enter choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            System.out.println("");
            scanner.nextLine();  // Consume newline
            String userType = "staff";
            Staff staff = (Staff) userDB.getUser(userID, false);
    
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
                    campOperationsController.viewCampsForUserType(userType);
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
                    //View/Reply to camp enquiries
                    campOperationsController.viewCampsForUserType(userType);
                    System.out.println("Which camp would you like to view enquiries for?");
                    String campToViewEnquiries = scanner.nextLine();
                    if (!campOperationsController.verifyCampOwnership(campToViewEnquiries, staff)) break;
                    enquiryController.viewEnquiriesByCamp(campToViewEnquiries);
                    System.out.println("Type the number of the enquiry you wish to reply to.");
                    int enquiryToReply = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Type the reply you would like to make!");
                    String reply = scanner.nextLine();
                    enquiryController.replyToEnquiryAsStaff(enquiryToReply, reply, campToViewEnquiries);
                    break;
                case 7:
                    // Accept/Reject suggestions
                    suggestionController.staffSuggestionHandler(staff);
                    break;
                case 8:
                    // Generate reports
                    reportController.generateReportsForStaff(staff);
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
