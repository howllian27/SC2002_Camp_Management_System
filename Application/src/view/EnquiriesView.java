package view;

import java.util.List;
/**
 * The EnquiriesView class is responsible for managing student inquiries and staff interactions.
 * It provides methods to display a list of student enquiries and prompt students for inquiries if any.
 *
 * @author Cheng lin
 * @version 1.0
 */

public class EnquiriesView {

    /**
     * Displays a list of student inquiries for staff to view.
     *
     * @param enquiries A list of strings containing student inquiries.
     */
    public void displayEnquiries(List<String> enquiries) {
        
        System.out.println("Displaying student inquiries:");
        for (String enquiry : enquiries) {
            System.out.println(enquiry);
        }
    }

    /**
     * Prompts student to enter an enquiry.
     */
    public void promptForEnquiries() {
        System.out.println("Please enter your inquiry:");
    }
}
