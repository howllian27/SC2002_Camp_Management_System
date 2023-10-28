package view;

import java.util.List;

public class EnquiriesView {
    
    // Display a list of student enquiries for staff to view

    public void displayEnquiries(List<String> enquiries) {
        System.out.println("Displaying student inquiries:");
        for (String enquiry : enquiries) {
            System.out.println(enquiry);
        }
    }

    public void promptForEnquiries() {
        System.out.println("Please enter your inquiry:");
    }
}
