/*
 - Attributes: userID, password
 - Attributes from excel: facultyInfo, name, email
 - Methods: Change password

*/

package database;

import java.io.Console;
import java.util.HashMap;
import java.util.Scanner;

public class UserDB {

    protected String userID; // userID will be obtained from email
    protected String password = "password"; // default is password
    protected String facultyInfo;
    protected String name; // to retrieve from excel
    protected String userEmail; // to retrieve from excel
    protected UserType userType = UserType.UNKNOWN;

    /*
    Constructor
    */

    public UserDB(String name, String userEmail, String facultyInfo) {
        this.name = name;
        this.password = password;
        this.userEmail = userEmail;
        this.userID = userEmail.split("@")[0];
        this.facultyInfo = facultyInfo;
        this.userType = UserType.UNKNOWN;
    }

    /*
    get and set methods
    */

    public String getUserID() // No set method is needed 
    {
        return this.userID;
    }

    // Password
    public String getPassword()
    {
        return this.password;
    }
    public Boolean setPassword(String password)
    {
        if (password.isEmpty()){
            return false;
        }
        this.password = password;
        return true;
    }

    // Name
    public String getName()
    {
        return this.name;
    }
    public Boolean setName(String name)
    {
        if (name.isEmpty()){
            return false;
        }
        this.name = name;
        return true;
    }
    
    // Email
    public String getUserEmail() {
        return this.userEmail;
    }
    public Boolean setUserEmail(String userEmail) {
        if (userEmail.isEmpty()) {
            return false;
        }
        this.userEmail = userEmail;
        this.userID = userEmail.split("@")[0];
        return true;
    }

    public UserType getUserType() { // MAY NEED TO REVISE
        return this.userType;
    } 
    
    public Boolean checkPassword(String inputPassword) {
        // Returns True if the Password matches
        return this.password.equals(inputPassword);
    }



    public Boolean changeUserPassword(Scanner scObject) 
    {
        int validationAttempts = 3;
        do {
                System.out.println("\n+------------------------------------------------------------+");
                System.out.println("|                                                             |");
                System.out.println("|                 PASSWORD CHANGE PORTAL                      |");
                System.out.println("|                                                             |");
                System.out.println("|    Please re-enter your current password to proceed.        |");
                System.out.println("|                                                             |");
                System.out.println("|       To return to the main menu, simply enter '0'.         |");
                System.out.println("|                                                             |");
                System.out.println("+------------------------------------------------------------+\n");

        System.out.print("Enter your Password: ");
        String userPassword = scObject.nextLine();

        if (userPassword.equals("0")) {
            System.out.println("Cancelling the Password Change Request.");
            System.out.println("Returning to previous menu...");
            return false;
        }

        if (this.checkPassword(userPassword)) 
        {
            System.out.print("Enter your New Password: ");
            String newPassword1 = scObject.nextLine();

            System.out.print("Re-Enter your New Password: ");
            String newPassword2 = scObject.nextLine();

            if (newPassword1.equals(newPassword2)) {
                // Change the Password
                this.setPassword(newPassword1);
                System.out.println("Your password has been changed successfully.");
                return true;
            } 
            else 
            {
                System.out.println("Your passwords do not match. Please try again.");
            }
        } 
        else 
        {
            validationAttempts--;
            System.out.println("Incorrect password. You have " + validationAttempts + " more tries to validate your identity.");
        }
    } while(validationAttempts != 0);
    
    System.out.println("You are not allowed to change your password as you have not validated your identity.");
    return false;
    }
}
/* CHECK USER TYPE
    public UserType authenticateUser(String userID, String password, 
        HashMap<String, Student> studentMap, 
        HashMap<String, Staff> staffMap, 
        HashMap<String, CAMPCOORDINATOR> campCoordinatorMap) 
    {

    UserType currentUserType = UserType.UNKNOWN;

    Student student = studentMap.get(userID);
    if (student != null && student.getPassword().equalsIgnoreCase(password)) {
        currentUserType = UserType.STUDENT;
    }

    // If not Student, check if Staff
    if (currentUserType == UserType.UNKNOWN) {
        Staff staff = staffMap.get(userID);
        if (staff != null && staff.getPassword().equalsIgnoreCase(password)) {
            currentUserType = UserType.STAFF;
        }
    }

    // If not student or staff, should be camp coordinator ** may remove
    if (currentUserType == UserType.UNKNOWN) {
        CAMPCOORDINATOR campCoordinator = campCoordinatorMap.get(userID);
        if (campCoordinator != null && campCoordinator.getPassword().equalsIgnoreCase(password)) {
            currentUserType = UserType.CAMPCOORDINATOR;
        }
    }
 */
