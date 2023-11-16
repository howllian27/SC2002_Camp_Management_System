package database;

import model.Camp;
import model.Enquiry;
import model.Student;

import java.util.*;
import java.io.*;

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

    // Static method to create instance of Singleton(CampDB) class
    public static synchronized EnquiryDB getInstance()
    {
        if (enquiryDB == null)
            enquiryDB = new EnquiryDB();
     
        return enquiryDB;
    }

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

    public void addEnquiry(Enquiry enquiry) {
        this.enquiries.add(enquiry);
    }

    public void updateEnquiry(Enquiry oldEnquiry, Enquiry newEnquiry) {
        try {
            enquiries.remove(oldEnquiry);
            enquiries.add(newEnquiry);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }

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