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
 * The EnquiryController class is responsible for handling enquiries from users.
 * @author Chan Hin Wai Howell
 * @version 1.0
 * @since 2023-11-02
 */
public class EnquiryController implements BaseController {

    EnquiryDB enquiryDB;
    private EnquiriesView enquiriesView;
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
        this.enquiriesView = new EnquiriesView();
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

        if (user instanceof Student) {
            Student student = (Student) user; // Safe downcast after checking with instanceof
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
     */
    public void viewEnquiriesByCamp(String campId) {
        List<Enquiry> enquiries = EnquiryDB.getInstance().getEnquiries(campId);
        enquiriesView.displayEnquiries(enquiries);
    }

    /**
     * Allows staff to view enquiries related to a student.
     * @param studentID The Id of the student
    */
    public void viewEnquiriesByStudent(String studentID) {
        List<Enquiry> enquiries = enquiryDB.getEnquiriesByStudent(studentID);
        enquiriesView.displayEnquiries(enquiries);
    }

    /**
     * Allows staff to reply to an enquiry.
     *
     * @param enquiry The original enquiry object
     * @param replyText The response to the enquiry.
     */
    public void replyToEnquiryAsStaff(int enquiryToReplyIndex, String replyText, String campID) {
        List<Enquiry> enquiries = enquiryDB.getEnquiriesByCamp(campID);
        Enquiry enquiryToReply = null;
        
        int count = 1;
        for (Enquiry enquiry : enquiries) {
            if(enquiry.getResponse() != null) {
                System.out.println("You had already previously replied to this enquiry as follows:");
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
            enquiryToReply.setResponse(replyText);
            System.out.println("Enquiry replied successfully.");
        }
    }

    /**
     * Allows camp committee member to reply to an enquiry.
     *
     * @param enquiry The original enquiry object
     * @param replyText The response to the enquiry.
     */
    public void replyToEnquiryAsCommittee(int enquiryToReplyIndex, String replyText, String userID) {
        List<Enquiry> enquiries = enquiryDB.getEnquiriesByStudent(userID);
        Enquiry enquiryToReply = null;
        
        int count = 1;
        for (Enquiry enquiry : enquiries) {
            if(enquiry.getResponse() != null) {
                System.out.println("You had already previously replied to this enquiry as follows:");
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

            if (user instanceof Student) {
                Student student = (Student) user;
                student.addPoints(1);
            } else {
                System.out.println("The user with ID " + userID + " is not a student.");
            }
            System.out.println("Enquiry replied successfully.");
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
     * @param enquiryToDeleteIndex
     * @param userID
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

    public void checkIfEnquiryReplied(int enquiryReplyIndex, String campId){
        List<Enquiry> enquiries = enquiryDB.getEnquiriesByCamp(campId);
        int count = 1;

        for(Enquiry enquiry : enquiries){
            if (count == enquiryReplyIndex){
                if(enquiry.getResponse() != null){
                    System.out.println("Enquiry has already been replied to! \n");
                    System.out.println("Response : " + enquiry.getResponse());
                }
            }
        }
    }
}