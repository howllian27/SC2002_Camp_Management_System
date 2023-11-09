package database;

import model.Enquiry;
import java.util.HashMap;
import java.util.UUID;

public class EnquiryDB {

    private  HashMap<String, HashMap<String, Enquiry>> campEnquiries;
    private  HashMap<String, Enquiry> allEnquiries;

    // Static variable reference of userDB
    // of type EnquiryDB
    private static EnquiryDB enquiryDB = null;


    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private EnquiryDB() {
        this.campEnquiries = new HashMap<>();
        this.allEnquiries = new HashMap<>();
    }

    // Static method to create instance of Singleton(CampDB) class
    public static synchronized EnquiryDB getInstance()
    {
        if (enquiryDB == null)
            enquiryDB = new EnquiryDB();
     
        return enquiryDB;
    }

    public HashMap<String, Enquiry> getEnquiries(String campID) {
        return campEnquiries.getOrDefault(campID, new HashMap<>());
    }

    public String addEnquiry(Enquiry enquiryDetails) {
        String campID = enquiryDetails.getCamp().getName(); 
        String enquiryID = UUID.randomUUID().toString(); 
        
        campEnquiries.computeIfAbsent(campID, k -> new HashMap<>()).put(enquiryID, enquiryDetails);

        allEnquiries.put(enquiryID, enquiryDetails);

        return enquiryID;
    }

    public boolean updateEnquiryReply(String enquiryID, String replyText) {
        Enquiry enquiry = allEnquiries.get(enquiryID);
        if (enquiry != null) {
            enquiry.setResponse(replyText);
            return true;
        }
        return false;
    }
}