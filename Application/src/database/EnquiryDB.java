package database;

import model.Camp;
import model.Enquiry;
import model.Student;

import java.util.*;

/**
 * The {@code EnquiryDB} class represents a database for managing camp inquiries.
 * It provides methods to retrieve, add, update, and remove inquiries.
 * This class follows the Singleton pattern to ensure a single instance throughout the application.
 *
 * @author Wei Hong
 * @version 1.0
 */
public class EnquiryDB {
    private ArrayList<Enquiry>  enquiries;

    // Static variable reference of userDB
    // of type EnquiryDB
    private static EnquiryDB enquiryDB = null;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private EnquiryDB() {
        this.enquiries = new ArrayList<>();
    }


    /**
     * Static method to create an instance of the Singleton (EnquiryDB) class.
     *
     * @return The single instance of EnquiryDB.
     */
    public static synchronized EnquiryDB getInstance()
    {
        if (enquiryDB == null)
            enquiryDB = new EnquiryDB();
     
        return enquiryDB;
    }

    /**
     * Retrieves a list of enquiries for a specific camp.
     *
     * @param campID The ID of the camp.
     * @return A List containing enquiries for the specified camp.
     */
    public List<Enquiry> getEnquiries(String campID) {
        List<Enquiry> campEnquiries = new ArrayList<>();
        Camp camp = CampDB.getInstance().getCamp(campID);
        for(Enquiry enquiry : enquiries) {
            if(Objects.equals(enquiry.getCamp(), camp)) {
                campEnquiries.add(enquiry);
            }
        }
        return campEnquiries;
    }

    /**
     * Adds a new enquiryy to the database.
     *
     * @param enquiry The Enquiry object to add.
     */
    public void addEnquiry(Enquiry enquiry) {
        this.enquiries.add(enquiry);
    }

    /**
     * Updates an existing enquiry with new information.
     *
     * @param oldEnquiry The old Enquiry object to be updated.
     * @param newEnquiry The new Enquiry object with updated information.
     */
    public void updateEnquiry(Enquiry oldEnquiry, Enquiry newEnquiry) {
        try {
            enquiries.remove(oldEnquiry);
            enquiries.add(newEnquiry);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Removes an enquiry from the database.
     *
     * @param enquiry The Enquiry object to be removed.
     */
    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }

    /**
     * Retrieves a list of inquiries made by a specific student.
     *
     * @param studentId The ID of the student.
     * @return A List containing inquiries made by the specified student.
     */
    public List<Enquiry> getEnquiriesByStudent(String studentId) {
        List<Enquiry> studentEnquiries = new ArrayList<>();
        Student student = (Student) UserDB.getInstance().getUser(studentId, true);
        for(Enquiry enquiry: enquiries) {
            if(Objects.equals(student, enquiry.getStudent())) {
                studentEnquiries.add(enquiry);
            }
        }
        return studentEnquiries;
    }

    /**
     * Retrieves a list of inquiries related to a specific camp.
     *
     * @param campId The ID of the camp.
     * @return A List containing inquiries related to the specified camp.
     */
    public List<Enquiry> getEnquiriesByCamp(String campId) {
        List<Enquiry> campEnquiries = new ArrayList<>();
        Camp camp = CampDB.getInstance().getCamp(campId);
        for(Enquiry enquiry : enquiries) {
            if(Objects.equals(enquiry.getCamp(), camp)) {
                campEnquiries.add(enquiry);
            }
        }
        return campEnquiries;
    }
}