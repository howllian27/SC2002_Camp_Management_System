package view;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import helper.InputHelper;
import model.Camp;
import model.CampInformation;
import model.Suggestion;

/**
 * The {@code SuggestionsView} class is responsible for managing and displaying suggestions and user interactions.
 * It provides methods to display a list of suggestions and prompt users for their own suggestions.
 *
 * @author Cheng Lin
 * @version 1.0
 */
public class SuggestionsView {
    public static void setMasterView() {
                System.out.println("\n+------------------------------------------------------------+");
                System.out.println("|                                                             |");
                System.out.println("|                       SUGGESTIONS PORTAL                    |");
                System.out.println("|                                                             |");
                System.out.println("|                                                             |");
                System.out.println("|        To return to the main menu, simply enter '0'.        |");
                System.out.println("|                                                             |");
                System.out.println("+------------------------------------------------------------+\n");
    }
    /**
     * Displays a list of suggestions to the user.
     *
     * @param suggestions A list of strings containing suggestions to be displayed.
     * @param registeredCommitteeCamp The camp object related to the suggestions.
     */
    public static void displaySuggestions(List<Suggestion> suggestions, Camp registeredCommitteeCamp) {
        setMasterView();
        System.out.println("Displaying suggestions:");

        CampInformation originalCampInfo = registeredCommitteeCamp.getCampInformation();

        int count = 1;

        for (Suggestion suggestion : suggestions) {
            if (!Objects.equals(suggestion.getCampInformation().campName, originalCampInfo.campName)) {
                System.out.println(count + ". Suggestion Made to Change Camp Name to: " + suggestion.getCampInformation().campName + "\n");
            }

            else if (!Objects.equals(suggestion.getCampInformation().description, originalCampInfo.description)) {
                System.out.println(count + ". Suggestion Made to Change Camp Description to: " + suggestion.getCampInformation().description + "\n");
            }

            else if (!Objects.equals(suggestion.getCampInformation().location, originalCampInfo.location)){
                System.out.println(count + ". Suggestion Made to Change Camp Location to: " + suggestion.getCampInformation().location + "\n");
            }

            else if (suggestion.getCampInformation().dates[0] != originalCampInfo.dates[0]){
                System.out.println(count + ". Suggestion Made to Change Camp Start Date to: " + suggestion.getCampInformation().dates[0] + "\n");
            }

            else if (suggestion.getCampInformation().dates[1] != originalCampInfo.dates[1]){
                System.out.println(count + ". Suggestion Made to Change Camp End Date to: " + suggestion.getCampInformation().dates[1] + "\n");
            }

            else if (suggestion.getCampInformation().registrationClosingDate != originalCampInfo.registrationClosingDate){
                System.out.println(count + ". Suggestion Made to Change Camp Registration Closing Date to: " + suggestion.getCampInformation().registrationClosingDate + "\n");
            }

            else if (suggestion.getCampInformation().totalSlots != originalCampInfo.totalSlots){
                System.out.println(count + ". Suggestion Made to Change Number of Camp Total Slots to: " + suggestion.getCampInformation().totalSlots + "\n");
            }

            else if (suggestion.getCampInformation().committeeSlots != originalCampInfo.committeeSlots){
                System.out.println(count + ". Suggestion Made to Change Number of Camp Committee Slots to: " + suggestion.getCampInformation().committeeSlots + "\n");
            }

            count++;
        }
    }

    /**
     * Prompts users to enter their suggestions for a specific aspect of the camp information.
     * Based on the user's input, constructs a new CampInformation object with the suggested changes.
     *
     * @param informationChoice The user's choice for which camp information aspect to suggest changes.
     * @param campInformation    The original CampInformation object.
     * @param camp               The associated camp object.
     * @return A CampInformation object with the suggested changes.
     */
    public static CampInformation suggestCampInformation(int informationChoice, CampInformation campInformation, Camp camp){
        switch (informationChoice){
            case 1:
                System.out.println("Please enter your suggestions for the camp name:");
                String nameSuggestion = InputHelper.nextLine();
                campInformation = new CampInformation(nameSuggestion, camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 2:
                System.out.println("Please enter your suggestions for the camp description:");
                String descriptionSuggestion = InputHelper.nextLine();
                campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), descriptionSuggestion, camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 3:
                System.out.println("Please enter your suggestions for the camp location:");
                String locationSuggestion = InputHelper.nextLine();
                campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), locationSuggestion, camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 4:
                System.out.println("Please enter your suggestions for the camp start date: (dd-mm-yyyy)");
                Date startDate = InputHelper.nextDate();
                Date oldEndDate = camp.getDates()[1];
                campInformation = new CampInformation(camp.getName(), new Date[]{startDate, oldEndDate}, camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 5:
                System.out.println("Please enter your suggestions for the camp end date: (dd-mm-yyyy)");
                Date endDate = InputHelper.nextDate();
                Date oldStartDate = camp.getDates()[0];
                campInformation = new CampInformation(camp.getName(), new Date[]{oldStartDate, endDate}, camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 6:
                System.out.println("Please enter your suggestions for the camp registration closing date: (dd-mm-yyyy)");
                Date closingDate = InputHelper.nextDate();
                campInformation = new CampInformation(camp.getName(), camp.getDates(), closingDate, camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 7:
                System.out.println("Please enter your suggestions for the camp total slots:");
                int totalSlotsSuggestion = InputHelper.nextInt();
                campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), totalSlotsSuggestion, camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            case 8:
                System.out.println("Please enter your suggestions for the camp committee slots:");
                int committeeSlotsSuggestion = InputHelper.nextInt();
                campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), committeeSlotsSuggestion, camp.getDescription(), camp.getInCharge(), camp.getVisibility());

                return campInformation;
            default:
                return null;
        }
    }

    /**
     * Prompts users to enter their suggestions for various aspects of the camp information.
     * Displays the current camp information and lets the user choose which aspect to suggest changes for.
     *
     * @param camp The camp object associated with the suggestions.
     * @return A CampInformation object with the suggested changes.
     */
    public static CampInformation promptForSuggestions(Camp camp) {
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

        int informationChoice = InputHelper.nextInt();

        return suggestCampInformation(informationChoice, campInformation, camp);
    }

    /**
     * Allows users to edit their suggestions based on the original suggestion and associated camp.
     * Displays the original suggestion and prompts the user to provide an edited suggestion.
     *
     * @param suggestion The original suggestion made by the user.
     * @param camp       The camp associated with the suggestion.
     * @return A CampInformation object with the edited suggestion.
     */
    public static CampInformation editSuggestionView(Suggestion suggestion, Camp camp){
        CampInformation originalCampInfo = camp.getCampInformation();
        CampInformation campInformation;

        if (!Objects.equals(suggestion.getCampInformation().campName, originalCampInfo.campName)) {
            System.out.println("You Suggested to Change Camp Name to: " + suggestion.getCampInformation().campName + "\n");
            System.out.println("Type edited suggestion:");
            String nameSuggestion = InputHelper.nextLine();
            campInformation = new CampInformation(nameSuggestion, camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

            return campInformation;
        }

        else if (!Objects.equals(suggestion.getCampInformation().description, originalCampInfo.description)) {
            System.out.println("You Suggested to Change Camp Description to: " + suggestion.getCampInformation().description + "\n");
            System.out.println("Type edited suggestion:");

            String descriptionSuggestion = InputHelper.nextLine();
            campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), descriptionSuggestion, camp.getInCharge(), camp.getVisibility());

            return campInformation;
        }

        else if (!Objects.equals(suggestion.getCampInformation().location, originalCampInfo.location)){
            System.out.println("You Suggested to Change Camp Location to: " + suggestion.getCampInformation().location + "\n");
            System.out.println("Type edited suggestion:");

            String locationSuggestion = InputHelper.nextLine();
            campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), locationSuggestion, camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

            return campInformation;
        }

        else if (suggestion.getCampInformation().dates[0] != originalCampInfo.dates[0]){
            System.out.println("You Suggested to Change Camp Start Date to: " + suggestion.getCampInformation().dates[0] + "\n");
            System.out.println("Type edited suggestion:");
            Date startDate = InputHelper.nextDate();
            Date oldEndDate = camp.getDates()[1];
            campInformation = new CampInformation(camp.getName(), new Date[]{startDate, oldEndDate}, camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

            return campInformation;
        }

        else if (suggestion.getCampInformation().dates[1] != originalCampInfo.dates[1]){
            System.out.println("You Suggested to Change Camp End Date to: " + suggestion.getCampInformation().dates[1] + "\n");
            System.out.println("Type edited suggestion:");

            Date endDate = InputHelper.nextDate();
            Date oldStartDate = camp.getDates()[0];
            campInformation = new CampInformation(camp.getName(), new Date[]{oldStartDate, endDate}, camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

            return campInformation;
        }

        else if (suggestion.getCampInformation().registrationClosingDate != originalCampInfo.registrationClosingDate){
            System.out.println("You Suggested to Change Camp Registration Closing Date to: " + suggestion.getCampInformation().registrationClosingDate + "\n");
            System.out.println("Type edited suggestion:");

            Date closingDate = InputHelper.nextDate();
            campInformation = new CampInformation(camp.getName(), camp.getDates(), closingDate, camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

            return campInformation;
        }

        else if (suggestion.getCampInformation().totalSlots != originalCampInfo.totalSlots){
            System.out.println("You Suggested to Change Number of Camp Total Slots to: " + suggestion.getCampInformation().totalSlots + "\n");
            System.out.println("Type edited suggestion:");

            String totalSlotsSuggestion = InputHelper.nextLine();
            campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), Integer.parseInt(totalSlotsSuggestion), camp.getCommitteeSlots(), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

            return campInformation;
        }

        else if (suggestion.getCampInformation().committeeSlots != originalCampInfo.committeeSlots){
            System.out.println("You Suggested to Change Number of Camp Committee Slots to: " + suggestion.getCampInformation().committeeSlots + "\n");
            System.out.println("Type edited suggestion:");

            String committeeSlotsSuggestion = InputHelper.nextLine();
            campInformation = new CampInformation(camp.getName(), camp.getDates(), camp.getClosingDate(), camp.getFaculty(), camp.getLocation(), camp.getTotalSlots(), Integer.parseInt(committeeSlotsSuggestion), camp.getDescription(), camp.getInCharge(), camp.getVisibility());

            return campInformation;
        } else {
            return null;
        }
    }

    /**
     * Displays camp details and suggested changes, and allows the staff member to approve or reject the changes.
     * Provides a detailed overview of suggested changes and prompts the staff member for approval.
     *
     * @param suggestions A list of strings containing suggestions to be displayed.
     */
    public static void viewAndApproveSuggestions(List<String> suggestions){

        // Prompt the staff member for approval
        System.out.print("\nDo you approve these changes? (yes/no): ");
        String decision = InputHelper.nextLine().trim().toLowerCase();

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
