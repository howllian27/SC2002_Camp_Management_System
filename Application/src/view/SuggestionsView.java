package view;

import java.util.List;
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
}
