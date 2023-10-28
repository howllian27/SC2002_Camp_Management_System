package view;

import java.util.List;

public class SuggestionsView {
    
    public void displaySuggestions(List<String> suggestions) {
        System.out.println("Displaying suggestions:");
        for (String suggestion : suggestions) {
            System.out.println(suggestion);
        }
    }

    public void promptForSuggestions() {
        System.out.println("Please enter your suggestions:");
    }
}
