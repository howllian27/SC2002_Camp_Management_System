
package database;

import helper.FileHelper;
import model.Faculty;
import model.Staff;
import model.Student;
import model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code UserDB} class represents an in-memory database for managing users, including students and staff.
 * It provides methods for adding, removing, updating, and retrieving user information.
 * This class follows the Singleton design pattern to ensure a single instance is used throughout the application.
 *
 * @author Wei Hong
 * @version 1.0
 */
public class UserDB
{
    private final Map<String, User> stringStudentMap;
    private final Map<String, User> staffUserMap;
    private final FileHelper fileHelper;

    private static final String STUDENT_FILE_PATH = "Application/src/database/data/student_list.txt";
    private static final String STAFF_FILE_PATH = "Application/src/database/data/staff_list.txt";

    // Static variable reference of userDB
    // of type UserDB
    private static UserDB userDB = null;
    

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private UserDB() {
        stringStudentMap = new HashMap<>();
        staffUserMap = new HashMap<>();
        fileHelper = new FileHelper();
        populateUserMap(true);  // Populate the student map
        populateUserMap(false); // Populate the staff map
    }

    /**
     * Returns the singleton instance of the {@code UserDB} class.
     *
     * @return The singleton instance of {@code UserDB}.
     */
    public static synchronized UserDB getInstance()
    {
        if (userDB == null)
            userDB = new UserDB();
 
        return userDB;
    }

    /**
     * Populates the user map with data from the corresponding file.
     *
     * @param isStudent {@code true} if populating student map, {@code false} for staff map.
     */
    public void populateUserMap(boolean isStudent) {
        String path = isStudent ? STUDENT_FILE_PATH : STAFF_FILE_PATH;
        List<String> listOfTxtLines = fileHelper.readFile(path);

        // Initialize the user map based on whether it's a student or staff
        Map<String, User> userMap = isStudent ? stringStudentMap : staffUserMap;

        int counter = 0;

        for (String line : listOfTxtLines) {
            if (counter > 0){
                String[] parts = line.split(" ");
                if (parts.length < 3) continue; // Skip if not enough parts

                String name = parts[0];
                String email = parts[1];
                Faculty faculty = getFacultyEnum(parts[2]);
                String userId = parts[1].split("@")[0];

                User user;
                if (isStudent) {
                    // Assuming Student has a constructor Student(String id, String email, Faculty faculty)
                    user = new Student(userId, "password", faculty, name);
                } else {
                    // Assuming Staff has a constructor Staff(String id, String email, Faculty faculty)
                    user = new Staff(userId, "password", faculty, name);
                }

                userMap.put(userId, user);
            }
            counter++;
        }
        // For debugging, you can print the populated user map
        // prettyPrintUserMap(userMap);
    }

    /**
     * Prints a formatted representation of the user map for debugging purposes.
     *
     * @param userMap The user map to be printed.
     */
    public void prettyPrintUserMap(Map<String, User> userMap) {
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            String key = entry.getKey();
            User user = entry.getValue();
            System.out.println("Key: " + key);
            System.out.println("Value: " + user.toString()); // You can customize the toString method of your User class for better formatting
            System.out.println();
        }
    }

    /**
     * Converts a faculty string to the corresponding {@code Faculty} enum.
     *
     * @param facultyString The string representation of the faculty.
     * @return The corresponding {@code Faculty} enum.
     */
    public Faculty getFacultyEnum(String facultyString) {
        switch (facultyString) {
            case "SCSE":
                return Faculty.SCSE;
            case "ADM":
                return Faculty.ADM;
            case "EEE":
                return Faculty.EEE;
            case "NBS":
                return Faculty.NBS;
            case "SSS":
                return Faculty.SSS;
            default:
                return Faculty.SCHOOL;
        }
    }

    /**
     * Adds a user to the user map, either in the student or staff map based on the parameter.
     *
     * @param user      The user to be added.
     * @param isStudent {@code true} if adding a student, {@code false} for staff.
     * @return {@code true} if the user was successfully added, {@code false} otherwise.
     */
    public boolean addUser(User user, boolean isStudent) {
        if (user == null) {
            return false;
        }

        String userID = user.getID();

        if (isStudent) {
            if (stringStudentMap.containsKey(userID)) {
                return false;
            }
            stringStudentMap.put(userID, user);
        } else {
            if (staffUserMap.containsKey(userID)) {
                return false;
            }
            staffUserMap.put(userID, user);
        }
        return true;
    }

    /**
     * Removes a user from the user map, either in the student or staff map based on the parameter.
     *
     * @param userID    The ID of the user to be removed.
     * @param isStudent {@code true} if removing a student, {@code false} for staff.
     * @return {@code true} if the user was successfully removed, {@code false} otherwise.
     */
    public boolean removeUser(String userID, boolean isStudent) {
        Map<String, User> userMap = isStudent ? stringStudentMap : staffUserMap;
        User removedUser = userMap.remove(userID);
        return removedUser != null;
    }

    /**
     * Retrieves a user from the user map, either in the student or staff map based on the parameter.
     *
     * @param userID    The ID of the user to be retrieved.
     * @param isStudent {@code true} if retrieving a student, {@code false} for staff.
     * @return The user object or {@code null} if not found.
     */
    public User getUser(String userID, boolean isStudent) {
        Map<String, User> userMap = isStudent ? stringStudentMap : staffUserMap;
        return userMap.get(userID);
    }

    /**
     * Updates the password for a user in the user map.
     * The user can be either a student or staff member.
     *
     * @param userID      The ID of the user whose password is to be updated.
     * @param newPassword The new password to be set.
     * @return {@code true} if the password was successfully updated, {@code false} otherwise.
     */
    public boolean updatePassword(String userID, String newPassword) {
        User user = getUser(userID, true);
        if (user != null) {
            // User is a student, update the password
            user.setPassword(newPassword);
            return true;
        }
        else
        {
            user = getUser(userID, false);
            if (user != null) {
                // User is a staff member, update the password
                user.setPassword(newPassword);
                return true;
            }
        }
        // User was not found in either map
        return false;
    }
}