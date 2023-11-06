package database;

import helper.FileHelper;
import model.Faculty;
import model.Staff;
import model.Student;
import model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDB {

    // initialize users according to studets and staff list.txt
    private final Map<String, User> stringStudentMap;
    private final Map<String, User> staffUserMap;

    public UserDB() {
        stringStudentMap = new HashMap<>();
        staffUserMap = new HashMap<>();
    }

    public void populateUserMap(boolean isStudent) {
        FileHelper fileHelper = new FileHelper();
        String path = "Application/src/database/data/staff_list.txt";
        List<String> listOfTxtLines = fileHelper.readFile(path);

        // Initialize the user map based on whether it's a student or staff
        Map<String, User> userMap = isStudent ? stringStudentMap : staffUserMap;

        for (int i = 1; i < listOfTxtLines.size(); i++) { // Start from index 1 to skip the header row
            String[] parts = listOfTxtLines.get(i).split(" ");
            String email = parts[1];
            String faculty = parts[2];
            String[] emailParts = email.split("@");
            String userId = emailParts[0];
            User user = isStudent ? new Student(userId, getFacultyEnum(faculty)) : new Staff(userId, getFacultyEnum(faculty));
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
