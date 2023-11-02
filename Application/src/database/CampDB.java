package database;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import model.Camp;
import model.Student;

/***
 *
 * @author Shun Jie
 * @version 1.1
 */
public class CampDB {

    private final HashSet<Camp> campDatabase;

    public CampDB() {
        this.campDatabase = new HashSet<>();
    }

    public HashSet<Camp> getAllCamps() {
        return campDatabase;
    }

    public Camp getCamp(String campID) {
        for (Camp camp : campDatabase) {
            if (camp.getName().equals(campID)) {
                return camp;
            }
        }
        return null;
    }

    public boolean addCamp(Camp camp) {
        return campDatabase.add(camp);
    }

    public void updateCamp(String campID, Camp updatedDetails) {
        Camp existingCamp = getCamp(campID);
        if (existingCamp != null) {
            campDatabase.remove(existingCamp);
            campDatabase.add(updatedDetails);
        }
    }

    public boolean deleteCamp(String campID) {
        Camp existingCamp = getCamp(campID);
        if (existingCamp != null) {
            return campDatabase.remove(existingCamp);
        }
        return false;
    }
    
    public Set<Student> getAttendeesForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            return camp.getAttendees();
        }
        return null;
    }

    public Set<Student> getCommitteeMembersForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            return camp.getCommittee();
        }
        return null;
    }

    public Set<Student> getAllStudentsForCamp(String campID) {
        Camp camp = getCamp(campID);
        if (camp != null) {
            Set<Student> allStudents = new HashSet<>();
            allStudents.addAll(camp.getAttendees());
            allStudents.addAll(camp.getCommittee());
            return allStudents;
        }
        return null;
    }


}