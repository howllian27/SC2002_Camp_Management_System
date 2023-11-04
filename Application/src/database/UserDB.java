package database;

import model.User;
import java.util.HashMap;
import java.util.Map;

public class UserDB {

    private static Map<String, User> users = new HashMap<>(); 

    public boolean addUser(User user) {
        if (user == null || users.containsKey(user.getID())) {
            return false;
        }
        users.put(user.getID(), user);
        return true;
    }

    public boolean removeUser(String userID) {
        return users.remove(userID) != null;
    }

    public User getUser(String userID) {
        return users.get(userID);
    }
    
    public boolean updatePassword(String userID, String newPassword) {
        User user = getUser(userID);
        if (user != null) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }
}
