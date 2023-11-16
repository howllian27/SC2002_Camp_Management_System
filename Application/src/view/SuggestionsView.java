package view;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.Camp;
import model.CampInformation;
import model.Suggestion;
import helper.DateConversionHelper;
/**
 * The SuggestionsView class is responsible for managing and displaying suggestions and user interactions.
 * It provides methods to display a list of suggestions and prompt users for their own suggestions.
 *
 * @author Cheng Lin
 * @version 10.0
 */

public class SuggestionsView {

    DateConversionHelper dateConversionHelper = new DateConversionHelper();
    /**
     * Displays a list of suggestions to the user.
     *
     * @param suggestions A list of strings containing suggestions to be displayed.
     */
    public void displaySuggestions(List<Suggestion> suggestions) {
                System.out.println("\n+------------------------------------------------------------+");
                System.out.println("|                                                             |");
                System.out.println("|                       SUGGESTIONS PORTAL                    |");
                System.out.println("|                                                             |");
                System.out.println("|                                                             |");
                System.out.println("|        To return to the main menu, simply enter '0'.        |");
                System.out.println("|                                                             |");
                System.out.println("+------------------------------------------------------------+\n");
        System.out.println("Displaying suggestions:");
        for (Suggestion suggestion : suggestions) {
            System.out.println(suggestion);
        }
    }
    
    /**
     * Prompts users to enter their suggestions.
     */
    public CampInformation promptForSuggestions(Camp camp) {
        System.out.println("Type the number of the information you would like to make a suggestion for:");
        System.out.println("1. Current Camp Name: " + camp.getName());
        System.out.println("2. Current Camp Description: " + camp.getDescription());
        System.out.println("3. Current Camp Location: " + camp.getLocation());
        System.out.println("4. Current Camp Start Date: " + camp.getDates()[0]);
        System.out.println("5. Current Camp End Date: " + camp.getDates()[1]);
        System.out.println("6. Current Camp Registration Closing Date: " + camp.getClosingDate());
        System.out.println("7. Current Camp Total Slots: " + camp.getTotalSlots());
        System.out.println("8. Current Camp Committee Slots: " + camp.getCommitteeSlots());
        CampInformation campInformation = camp.getCampInformation();

        Scanner scanner = new Scanner(System.in);
        int informationChoice = scanner.nextInt();

        switch (informationChoice){
            case 1:
                System.out.println("Please enter your suggestions for the camp name:");
                scanner.nextLine();
                String nameSuggestion = scanner.nextLine();
                campInformation = new CampInformation(nameSuggestion, camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 2:
                System.out.println("Please enter your suggestions for the camp description:");
                scanner.nextLine();
                String descriptionSuggestion = scanner.nextLine();
                campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), descriptionSuggestion, camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 3:
                System.out.println("Please enter your suggestions for the camp location:");
                scanner.nextLine();
                String locationSuggestion = scanner.nextLine();
                campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), locationSuggestion, camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 4:
                System.out.println("Please enter your suggestions for the camp start date: (dd-mm-yyyy)");
                scanner.nextLine();
                String startDateSuggestion = scanner.nextLine();
                Date startDate =  dateConversionHelper.convertDate(startDateSuggestion);
                Date oldEndDate = camp.getDates()[1];
                campInformation = new CampInformation(camp.getName(), new Date[]{startDate, oldEndDate}, camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 5:
                System.out.println("Please enter your suggestions for the camp end date: (dd-mm-yyyy)");
                scanner.nextLine();
                String endDateSuggestion = scanner.nextLine();
                Date endDate =  dateConversionHelper.convertDate(endDateSuggestion);
                Date oldStartDate = camp.getDates()[0];
                campInformation = new CampInformation(camp.getName(), new Date[]{oldStartDate, endDate}, camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 6:
                System.out.println("Please enter your suggestions for the camp registration closing date: (dd-mm-yyyy)");
                scanner.nextLine();
                String closingDateSuggestion = scanner.nextLine();
                Date closingDate =  dateConversionHelper.convertDate(closingDateSuggestion);
                campInformation = new CampInformation(camp.getName(), camp.getDates(), closingDate, camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 7:
                System.out.println("Please enter your suggestions for the camp total slots:");
                scanner.nextLine();
                String totalSlotsSuggestion = scanner.nextLine();
                campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), Integer.parseInt(totalSlotsSuggestion), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 8:
                System.out.println("Please enter your suggestions for the camp committee slots:");
                scanner.nextLine();
                String committeeSlotsSuggestion = scanner.nextLine();
                campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), Integer.parseInt(committeeSlotsSuggestion), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            default:
                return null;
        }
    }
    /**
     * Displays camp details and suggested changes, and allows the staff member to approve or reject the changes.
     *
     * @param camp The Camp object containing the camp's information.
     * @param suggestions A list of strings containing suggestions to be displayed.
     */
    public void viewAndApproveSuggestions(List<String> suggestions){

        // Prompt the staff member for approval
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDo you approve these changes? (yes/no): ");
        String decision = scanner.nextLine().trim().toLowerCase();

        if (decision.equals("yes")) {
            // Update the camp details with the suggested changes
            System.out.println("Camp details have been updated.");
        } else if (decision.equals("no")) {
            System.out.println("Changes have been rejected.");
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
        }
    }
}
