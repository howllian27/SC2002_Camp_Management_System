
package database;

import helper.FileHelper;
import model.Faculty;
import model.Staff;
import model.Student;
import model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDB
{
    private final Map<String, User> stringStudentMap;
    private final Map<String, User> staffUserMap;
    private final FileHelper fileHelper;

    private static final String STUDENT_FILE_PATH = "Application/src/database/data/student_list.txt";
    private static final String STAFF_FILE_PATH = "Application/src/database/data/staff_list.txt";

    public UserDB() {
        stringStudentMap = new HashMap<>();
        staffUserMap = new HashMap<>();
        fileHelper = new FileHelper();
        populateUserMap(true);  // Populate the student map
        populateUserMap(false); // Populate the staff map
    }

    public void populateUserMap(boolean isStudent) {
        String path = isStudent ? STUDENT_FILE_PATH : STAFF_FILE_PATH;
        List<String> listOfTxtLines = fileHelper.readFile(path);

        // Initialize the user map based on whether it's a student or staff
        Map<String, User> userMap = isStudent ? stringStudentMap : staffUserMap;

        for (String line : listOfTxtLines) {
            String[] parts = line.split(","); // Assuming CSV format
            if (parts.length < 3) continue; // Skip if not enough parts

            String userId = parts[0].trim();
            String email = parts[1].trim();
            Faculty faculty = getFacultyEnum(parts[2].trim());

            User user;
            if (isStudent) {
                // Assuming Student has a constructor Student(String id, String email, Faculty faculty)
                user = new Student(userId, email, faculty);
            } else {
                // Assuming Staff has a constructor Staff(String id, String email, Faculty faculty)
                user = new Staff(userId, email, faculty);
            }

            userMap.put(userId, user);
        }
        // For debugging, you can print the populated user map
        prettyPrintUserMap(userMap);
    }

    public void prettyPrintUserMap(Map<String, User> userMap) {
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            String key = entry.getKey();
            User user = entry.getValue();
            System.out.println("Key: " + key);
            System.out.println("Value: " + user.toString()); // You can customize the toString method of your User class for better formatting
            System.out.println();
        }
    }

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

    public boolean removeUser(String userID, boolean isStudent) {
        Map<String, User> userMap = isStudent ? stringStudentMap : staffUserMap;
        User removedUser = userMap.remove(userID);
        return removedUser != null;
    }

    public User getUser(String userID, boolean isStudent) {
        Map<String, User> userMap = isStudent ? stringStudentMap : staffUserMap;
        return userMap.get(userID);
    }

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