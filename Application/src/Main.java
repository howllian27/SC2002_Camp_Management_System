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
import helper.InputHelper;
import helper.LoggerHelper;
import model.Camp;
import model.CampInformation;
import model.Staff;
import model.Student;
import model.User;
import view.CampListView;
import view.CreateCampView;
import view.SuggestionsView;

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
    static SuggestionsView suggestionsView = new SuggestionsView();

    public static void main(String[] args) {
        while (true) {
            if (currentUser == null) {
                    LoggerHelper.clearScreen();
                    System.out.println("+--------------------------------------+");
                    System.out.println("|       ____    _    __  __ ____       |");
                    System.out.println("|      / ___|  / \\  |  \\/  / ___|      |");
                    System.out.println("|     | |     / _ \\ | |\\/| \\___ \\      |");
                    System.out.println("|     | |___ / ___ \\| |  | |___) |     |");
                    System.out.println("|      \\____/_/   \\_\\_|  |_|____/      |");
                    System.out.println("|                                      |");
                    System.out.println("+--------------------------------------+\n");


                    System.out.println("Welcome to CAMs!");
                    System.out.println("1. Login");
                    System.out.println("2. Exit");
                    System.out.print("Enter choice: ");

                    int choice = InputHelper.nextInt();

                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter User ID: ");
                            String userID = InputHelper.nextLine();
                            System.out.print("Enter Password: ");
                            String password = InputHelper.nextLine();
                            System.out.print("Are you a student? (Y/N): ");
                            String isStudent = InputHelper.nextLine();
                            boolean isStudentBool = !isStudent.equals("N") && !isStudent.equals("n");

                            User currentUser = userController.loginUser(userID, password, isStudentBool);
                            
                            if (currentUser.getFirstTimeRegistered() == false){
                                System.out.println("Please enter your new password:");
                                String newPassword = InputHelper.nextLine();
                                userController.changePassword(userID, newPassword);
                                currentUser.setRegisteredStatus();
                            }

                            LoggerHelper.clearScreen();

                            if (currentUser != null) { //Removed unnecessary error message
                                if (isStudentBool) {
                                    studentMenu(userID, currentUser);
                                } else {
                                    staffMenu(userID, currentUser);
                                }
                            }
                        }
                        case 2 -> {
                            LoggerHelper.Success("Goodbye!");
                            return;
                        }
                        default -> System.out.println("Invalid choice. Try again.");
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
            System.out.println("-----------------------------------------------------");
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
            System.out.println("-----------------------------------------------------");
            System.out.print("Enter choice: ");


            int choice = InputHelper.nextInt();

            System.out.println();
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
                    String selectedCampID = InputHelper.nextLine();
                    System.out.println("Would you like to register as a participant or a committee member?");
                    System.out.println("1. Participant");
                    System.out.println("2. Committee Member");
                    int roleChoice = InputHelper.nextInt();
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
                    LoggerHelper.clearScreen();
                    campOperationsController.viewCampsForUserType(userType);
                    System.out.println("Type the name of the camp you would like to submit an enquiry for!");
                    String campID = InputHelper.nextLine();
                    if (campDB.getCamp(campID) == null){
                        System.out.println("Invalid camp name. Try again.");
                        break;
                    }
                    System.out.println("Type the enquiry you would like to make!");
                    String enquiry = InputHelper.nextLine();
                    enquiryController.submitEnquiry(userID, campID, enquiry);

                    break;
                case 4:
                    // Submit suggestion for a camp
                    LoggerHelper.clearScreen();
                    if (!student.getCampCommitteeMemberStatus()){
                        System.out.println("You are not a committee member, so you can't make suggestions!");
                        break;
                    }

                    Camp camp = student.getRegisteredCommitteeCamp();
                    suggestionsView.setMasterView();
                    System.out.println("Your committee camp name is: " + camp.getName() + "\n");

                    suggestionController.submitSuggestion(student, camp.getName());

                    break;
                case 5:
                    // View registered camps
                    LoggerHelper.clearScreen();
                    HashMap<String, Camp> registeredCamps = student.getRegisteredCamps();
                    List <Camp> camps = new ArrayList<Camp> (registeredCamps.values());
                    CampListView.displayRegCampsForStudent(camps, student);
                    break;
                case 6:
                    // View/Edit/Delete my enquiries
                    LoggerHelper.clearScreen();
                    enquiryController.viewEnquiriesByStudent(userID);
                    System.out.println("Please type the number of the action you would like to perform.");
                    System.out.println("1. Edit my enquiries");
                    System.out.println("2. Delete my enquiries");
                    System.out.println("3. Reply to enquiries (Camp Committee only)");
                    int enquiryChoice = InputHelper.nextInt();

                    switch (enquiryChoice) {
                        case 1:
                            // Edit my enquiries
                            enquiryController.viewEnquiriesByStudent(userID);
                            System.out.println("Type the enquiry you would like to edit!");
                            int enquiryToEditIndex = InputHelper.nextInt();
                            enquiryController.checkIfEnquiryReplied(enquiryToEditIndex, userID);
                            InputHelper.nextLine();
                            System.out.println("Type the new enquiry you would like to make!");
                            String newEnquiry = InputHelper.nextLine();
                            enquiryController.editEnquiry(enquiryToEditIndex, newEnquiry, userID);
                            break;
                        case 2:
                            // Delete my enquiries
                            enquiryController.viewEnquiriesByStudent(userID);
                            System.out.println("Type the number of the enquiry you would like to delete!");
                            int enquiryToDeleteIndex = InputHelper.nextInt();
                            enquiryController.checkIfEnquiryReplied(enquiryToDeleteIndex, userID);
                            enquiryController.deleteEnquiry(enquiryToDeleteIndex, userID);
                            break;
                        case 3:
                            // Reply to enquiries (Camp Committee only)
                            if (!student.getCampCommitteeMemberStatus()){
                                System.out.println("You are not a committee member!");
                                break;
                            }
                            String campIdToReply = student.getRegisteredCommitteeCamp().getName();
                            System.out.println("Your committee camp name is: " + campIdToReply);
                            enquiryController.viewEnquiriesByCamp(campIdToReply);;
                            System.out.println("Type the number of the enquiry you would like to reply to!");
                            int enquiryToReplyIndex = InputHelper.nextInt();
                            InputHelper.nextLine();
                            enquiryController.checkIfEnquiryReplied(enquiryToReplyIndex, userID);
                            System.out.println("Type the reply you would like to make!");
                            String reply = InputHelper.nextLine();
                            enquiryController.replyToEnquiryAsCommittee(enquiryToReplyIndex, reply, campIdToReply, userID);
                        default:
                            System.out.println("Invalid choice. Try again.");
                            break;
                    }
                    enquiryController.viewEnquiriesByStudent(userID);
                    break;
                case 7:
                    // View/Edit/Delete my suggestions
                    LoggerHelper.clearScreen();
                    System.out.println("View my suggestions:");
                    if (!suggestionController.viewIndivSuggestions(userID, student)) break;
                    System.out.println("\n Please type the number of the action you would like to perform.");
                    System.out.println("1. Edit my suggestions");
                    System.out.println("2. Delete my suggestions");
                    int suggestionChoice = InputHelper.nextInt();

                    switch (suggestionChoice) {
                        case 1 ->
                            // Edit my suggestions
                                suggestionController.editSuggestion(student);
                        case 2 ->
                            // Delete my suggestions
                                suggestionController.deleteSuggestion(student);
                        default -> System.out.println("Invalid choice. Try again.");
                    }
                case 8:
                    // View my enquiry replies
                    LoggerHelper.clearScreen();
                    break;
                case 9:
                    // Withdraw from a camp
                    LoggerHelper.clearScreen();
                    campOperationsController.viewCampsForUserType(userType);
                    System.out.println("Type the name of the camp you would like to withdraw from.");
                    String withdrawCampID = InputHelper.nextLine();
                    studentCampInteractionController.withdrawFromCamp(userID, withdrawCampID);
                    break;
                case 10:
                    LoggerHelper.clearScreen();
                    System.out.println("Please enter your new password:");
                    String newPassword = InputHelper.nextLine();
                    userController.changePassword(userID, newPassword);
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
            System.out.println("-----------------------------------------------------");
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
            System.out.println("-----------------------------------------------------");
            System.out.print("Enter choice: ");
            int choice = InputHelper.nextInt();

            String userType = "staff";
            Staff staff = (Staff) userDB.getUser(userID, false);

            switch (choice) {
                case 1 -> {
                    LoggerHelper.clearScreen();
                    System.out.println("Please enter your new password:");
                    String newPassword = InputHelper.nextLine();
                    userController.changePassword(userID, newPassword);
                }
                case 2 -> {
                    // Create a new camp
                    LoggerHelper.clearScreen();
                    CampInformation campInformation = createCampView.creatingCamp(user);
                }
                case 3 -> {
                    // Edit an existing camp
                    LoggerHelper.clearScreen();
                    campOperationsController.viewIndivCamps(staff);
                    System.out.println("Which camp would you like to edit?");
                    String campToEdit = InputHelper.nextLine();
                    campOperationsController.editCamp(campToEdit, staff);
                }
                case 4 ->{
                    // View all camps
                    LoggerHelper.clearScreen();
                    campOperationsController.viewCampsForUserType(userType);
                }
                case 5 -> {
                    // View registered students for a camp
                    LoggerHelper.clearScreen();
                    campOperationsController.viewCampsForUserType(userType);
                    System.out.println("Select a camp to view registered students for:");
                    String campToView = InputHelper.nextLine();
                    campOperationsController.viewRegisteredStudents(campToView);
                }
                case 6 -> {
                    //View/Reply to camp enquiries
                    LoggerHelper.clearScreen();
                    campOperationsController.viewCampsForUserType(userType);
                    System.out.println("Which camp would you like to view enquiries for?");
                    String campToViewEnquiries = InputHelper.nextLine();
                    if (!campOperationsController.verifyCampOwnership(campToViewEnquiries, staff)) break;
                    enquiryController.viewEnquiriesByCamp(campToViewEnquiries);
                    System.out.println("Type the number of the enquiry you wish to reply to.");
                    int enquiryToReply = InputHelper.nextInt();
                    System.out.println("Type the reply you would like to make!");
                    String reply = InputHelper.nextLine();
                    enquiryController.replyToEnquiryAsStaff(enquiryToReply, reply, campToViewEnquiries);
                }
                case 7 ->{
                    // Accept/Reject suggestions
                    LoggerHelper.clearScreen();
                    suggestionController.staffSuggestionHandler(staff);
                }
                case 8 ->{
                    // Generate reports
                    LoggerHelper.clearScreen();
                    reportController.generateReportsForStaff(staff);
                }
                case 9 -> {
                    currentUser = null;
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
