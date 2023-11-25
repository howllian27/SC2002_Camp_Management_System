package controller;

import database.EnquiryDB;

import java.util.List;

import database.CampDB;
import database.UserDB;
import model.Camp;
import model.Enquiry;
import model.Student;
import model.User;
import view.EnquiriesView;

/**
 * The {@code EnquiryController } class is responsible for handling enquiries from users.
 *
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-11-02
 */
public class EnquiryController implements BaseController {

    EnquiryDB enquiryDB;
    CampDB campDB;
    UserDB userDB;

    public EnquiryController() {
        setMasterVariables();
    }

    @Override
    public void setMasterVariables() {
        this.campDB = CampDB.getInstance();
        this.userDB = UserDB.getInstance();
        this.enquiryDB = EnquiryDB.getInstance();
    }

    /**
     * Allows a user to submit an enquiry.
     *
     * @param userID The ID of the user.
     * @param campID The ID of the camp.
     * @param enquiryText The text of the enquiry.
     */
    public void submitEnquiry(String userID, String campID, String enquiryText) {
        Camp camp = campDB.getCamp(campID);
        User user = userDB.getUser(userID, true);

        if (user instanceof Student student) {
            // Safe downcast after checking with instanceof
            Enquiry enquiry = new Enquiry(camp, student, enquiryText);
            enquiryDB.addEnquiry(enquiry);
            System.out.println("Enquiry submitted successfully.");
        } else {
            System.out.println("The user with ID " + userID + " is not a student.");
        }
    }

    /**
     * Allows staff to view enquiries related to a camp.
     *
     * @param campId The ID of the camp.
     * @return True if there are enquiries to display; false otherwise.
     */
    public boolean viewEnquiriesByCamp(String campId) {
        List<Enquiry> enquiries = EnquiryDB.getInstance().getEnquiries(campId);
        EnquiriesView.displayEnquiries(enquiries);

        if (enquiries.size() == 0) {
            return false;
        }

        return true;
    }

    /**
     * Allows staff to view enquiries related to a student.
     *
     * @param studentID The Id of the student
     * @return True if there are enquiries to display; false otherwise.
    */
    public boolean viewEnquiriesByStudent(String studentID) {
        List<Enquiry> enquiries = enquiryDB.getEnquiriesByStudent(studentID);
        EnquiriesView.displayEnquiries(enquiries);

        if (enquiries.size() == 0) {
            return false;
        }

        return true;
    }

    /**
     * Allows staff to reply to an enquiry.
     *
     * @param enquiryToReplyIndex The index of the enquiry to reply to.
     * @param replyText The response to the enquiry.
     * @param campID The ID of the camp.
     */
    public void replyToEnquiryAsStaff(int enquiryToReplyIndex, String replyText, String campID) {
        List<Enquiry> enquiries = enquiryDB.getEnquiriesByCamp(campID);
        Enquiry enquiryToReply = null;
        
        int count = 1;
        for (Enquiry enquiry : enquiries) {            
            if (enquiryToReplyIndex == count && enquiry.getResponse() != null) {
                System.out.println("You had already previously replied to this enquiry as follows:");
                System.out.println("Response : " + enquiry.getResponse() + "\n");
                return;
            } else if (enquiryToReplyIndex == count) {
                enquiryToReply = enquiry;
            }

            count++;
        }

        if (enquiryToReply == null) {
            System.out.println("Invalid enquiry index.");
            return;
        } else {
            enquiryToReply.setResponse(replyText);
            System.out.println("Enquiry replied successfully.");
        }
    }

    /**
     * Allows camp committee member to reply to an enquiry.
     *
     * @param enquiryToReplyIndex The index of the enquiry to reply to.
     * @param replyText The response to the enquiry.
     * @param campID The ID of the camp.
     * @param userID The ID of the user.
     */
    public void replyToEnquiryAsCommittee(int enquiryToReplyIndex, String replyText, String campID, String userID) {
        List<Enquiry> enquiries = enquiryDB.getEnquiriesByCamp(campID);
        Enquiry enquiryToReply = null;
        
        int count = 1;
        for (Enquiry enquiry : enquiries) {
            if(enquiry.getResponse() != null && enquiryToReplyIndex == count) {
                System.out.println("This enquiry has already previously been replied as follows:");
                System.out.println("Response : " + enquiry.getResponse() + "\n");
                return;
            }
            
            if (enquiryToReplyIndex == count) {
                enquiryToReply = enquiry;
            }

            count++;
        }

        if (enquiryToReply == null) {
            System.out.println("Invalid enquiry index.");
            return;
        } else {
            User user = userDB.getUser(userID, true);
            enquiryToReply.setResponse(replyText);

            if (user instanceof Student student) { //Changed to pattern variable
                student.addPoints(1);
            } else {
                System.out.println("The user with ID " + userID + " is not a student.");
            }
            System.out.println("\nEnquiry replied successfully.");
            System.out.println("+1 point for replying to an enquiry!");
        }
    }

    /**
     * Allows students to edit their enquiries.
     * 
     * @param enquiryToEditIndex The index of the enquiry to edit.
     * @param newEnquiryText The new enquiry text.
     * @param userID The ID of the user.
    */
    public void editEnquiry(int enquiryToEditIndex, String newEnquiryText, String userID) {
        List<Enquiry> enquiries = enquiryDB.getEnquiriesByStudent(userID);
        Enquiry enquiryToEdit = null;
        
        int count = 1;
        for (Enquiry enquiry : enquiries) {
            if(enquiry.getResponse() != null) System.out.println("Response : " + enquiry.getResponse() + "\n");
            
            if (enquiryToEditIndex == count) {
                enquiryToEdit = enquiry;
            }

            count++;
        }

        if (enquiryToEdit == null) {
            System.out.println("Invalid enquiry index.");
            return;
        } else {
            Enquiry editedEnquiry = new Enquiry(
                    enquiryToEdit.getCamp(),
                    enquiryToEdit.getStudent(),
                    newEnquiryText
            );
    
            EnquiryDB.getInstance().updateEnquiry(enquiryToEdit, editedEnquiry);
            System.out.println("Enquiry edited successfully.");
        }
    }

    /**
     * Allows students to delete their enquiries.
     *
     * @param enquiryToDeleteIndex The index of the enquiry to delete.
     * @param userID The ID of the user.
     */
    public void deleteEnquiry(int enquiryToDeleteIndex, String userID) {
        List<Enquiry> enquiries = enquiryDB.getEnquiriesByStudent(userID);
        Enquiry enquiryToDelete = null;
        
        int count = 1;
        for (Enquiry enquiry : enquiries) {
            if(enquiry.getResponse() != null) System.out.println("Response : " + enquiry.getResponse() + "\n");
            
            if (enquiryToDeleteIndex == count) {
                enquiryToDelete = enquiry;
            }

            count++;
        }

        if (enquiryToDelete == null) {
            System.out.println("Invalid enquiry index.");
            return;
        } else {
            EnquiryDB.getInstance().removeEnquiry(enquiryToDelete);
            System.out.println("Enquiry deleted successfully.");
        }
    }

    /**
     * Checks if an enquiry has been replied to and displays the response.
     *
     * @param enquiryReplyIndex The index of the enquiry to check.
     * @param campId The ID of the camp.
     */
    public boolean checkIfEnquiryReplied(int enquiryReplyIndex, String userId){
        List<Enquiry> enquiries = enquiryDB.getEnquiriesByStudent(userId);
        int count = 1;

        for(Enquiry enquiry : enquiries){
            if (count == enquiryReplyIndex){
                if(enquiry.getResponse() != null){
                    System.out.println("\nEnquiry has already been replied to!");
                    System.out.println("Response : " + enquiry.getResponse());
                    return true;
                }
            }

            count++;
        }

        return false;
    }
}