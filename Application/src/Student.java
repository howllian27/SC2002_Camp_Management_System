import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Camp> previouslyRegisteredCamps;
    private ArrayList<Camp> registeredCamps; //Camps where student is a member
    private Camp registeredCommitteeCamp;

    Student(String userID, Faculty faculty) {
        super(userID, faculty);
        previouslyRegisteredCamps = new ArrayList<Camp>();
        registeredCamps = new ArrayList<Camp>();
    }

    Student(String userID, String password, Faculty faculty) {
        super(userID, password, faculty);
        previouslyRegisteredCamps = new ArrayList<Camp>();
        registeredCamps = new ArrayList<Camp>();
    }

    //Getters
    public Camp getRegisteredCommitteeCamp() { return this.registeredCommitteeCamp; }
    public ArrayList<Camp> getRegisteredCamps() { return this.registeredCamps; }

    //Methods
    public boolean addCamp(Camp camp) {
        if(previouslyRegisteredCamps.contains(camp)) return false; //If student has already previously registered for the camp

        //CHECK IF STUDENT HAS CONFLICTING CAMP DATES -IMPL
        return true;
    }

    public boolean removeCamp(Camp camp) {
        return true;
    }

    public boolean addCommitteeCamp(Camp camp) {
        return true;
    }

    public boolean removeCommitteeCamp() {
        return true;
    }

    //Events
    public void onAttendeeAdded(Camp camp) {
        registeredCamps.add(camp);
    }
    public void onAttendeeRemoved(Camp camp) {
        registeredCamps.remove(camp);
    }
    public void onCommitteeMemberAdded(Camp camp) {
        registeredCommitteeCamp = camp;
    }
    public void onCommitteeMemberRemoved() {
        registeredCommitteeCamp = null;
    }
}
