package view;

import model.Enquiry;

import java.util.List;
/**
 * The {@code EnquiriesView} class is responsible for managing student inquiries and staff interactions.
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
    public static void displayEnquiries(List<Enquiry> enquiries) {
                System.out.println("\n+------------------------------------------------------------+");
                System.out.println("|                                                             |");
                System.out.println("|                       ENQUIRIES PORTAL                      |");
                System.out.println("|                                                             |");
                System.out.println("|                                                             |");
                System.out.println("|        To return to the main menu, simply enter '0'.        |");
                System.out.println("|                                                             |");
                System.out.println("+------------------------------------------------------------+\n");
        System.out.println("Displaying Enquiries:");
        if(enquiries.isEmpty()){
            System.out.println("No Enquiries so far");
        }
        int count = 1;
        for (Enquiry enquiry : enquiries) {
            System.out.println(count + ". " + "Enquiry : " + enquiry.getEnquiry());
            System.out.println("   Camp : " + enquiry.getCamp().getName());
            if(enquiry.getResponse() != null) System.out.println("   Response : " + enquiry.getResponse() + "\n");
            count++;
        }

        System.out.println("\n");
    }

    /**
     * Prompts student to enter an enquiry.
     */
    public static void promptForEnquiries() {
        System.out.println("Please enter your inquiry:");
    }
}
