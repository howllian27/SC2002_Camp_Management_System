package database;

import model.Enquiry;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EnquiryDB {

    private static HashMap<String, HashMap<String, Enquiry>> campEnquiries;
    private static HashMap<String, Enquiry> allEnquiries;

    public EnquiryDB() {
        this.campEnquiries = new HashMap<>();
        this.allEnquiries = new HashMap<>();
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