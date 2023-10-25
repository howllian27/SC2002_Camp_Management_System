import java.util.HashSet;
import java.util.Set;

public class Staff extends User {
    private Set<Camp> createdCamps;

    Staff(String userID, Faculty faculty) {
        super(userID, faculty);
        createdCamps = new HashSet<Camp>();
    }


    Staff(String userID, String password, Faculty faculty) {
        super(userID, password, faculty);
        createdCamps = new HashSet<Camp>();
    }
}
