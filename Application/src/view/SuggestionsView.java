package view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Camp;
import model.CampInformation;
import model.Suggestions;

/**
 * The SuggestionsView class is responsible for managing and displaying suggestions and user interactions.
 * It provides methods to display a list of suggestions and prompt users for their own suggestions.
 *
 * @author Cheng Lin
 * @version 10.0
 */

public class SuggestionsView {

    /**
     * Displays a list of suggestions to the user.
     *
     * @param suggestions A list of strings containing suggestions to be displayed.
     */
    public void displaySuggestions(List<String> suggestions) {
                System.out.println("\n+------------------------------------------------------------+");
                System.out.println("|                                                             |");
                System.out.println("|                       SUGGESTIONS PORTAL                    |");
                System.out.println("|                                                             |");
                System.out.println("|                                                             |");
                System.out.println("|        To return to the main menu, simply enter '0'.        |");
                System.out.println("|                                                             |");
                System.out.println("+------------------------------------------------------------+\n");
        System.out.println("Displaying suggestions:");
        for (String suggestion : suggestions) {
            System.out.println(suggestion);
        }
    }
    
    /**
     * Prompts users to enter their suggestions.
     */
    public void promptForSuggestions() {
        System.out.println("Please enter your suggestions:");
    }
    /**
     * Displays camp details and suggested changes, and allows the staff member to approve or reject the changes.
     *
     * @param camp The Camp object containing the camp's information.
     * @param suggestions A list of strings containing suggestions to be displayed.
     */
    public void viewAndApproveSuggestions(){

        // Prompt the staff member for approval
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDo you approve these changes? (yes/no): ");
        String decision = scanner.nextLine().trim().toLowerCase();

        if (decision.equals("yes")) {
            // Update the camp details with the suggested changes
            Camp.putAll(suggestions);
            System.out.println("Camp details have been updated.");
        } else if (decision.equals("no")) {
            System.out.println("Changes have been rejected.");
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
        }
    }
}
