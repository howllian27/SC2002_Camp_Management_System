/*
    A hashset of user
*/

package database;

import model.User;
import java.util.HashSet;

public class UserDB {
    
    public HashSet<User> users = new HashSet<>(); 

    public boolean addUser(User user) 
    {
        return users.add(user); 
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }

    public User getUser(String userID) {
        for (User user : users) {
            if (user.getID().equals(userID)) { // user.getID from SJ Package
                return user;
            }
        }
        System.out.println("User not found");
        return null;  
    }
    
    public boolean updatePassword(String userID, String newPassword)
    {
        for (User user : users) {
            if (user.getID().equals(userID)) 
            {
                user.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }
}
