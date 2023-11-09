package database;

public class SuggestionDB {
    private static SuggestionDB suggestionDB = null;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private SuggestionDB(){}

    // Static method to create instance of Singleton(CampDB) class
    public static synchronized SuggestionDB getInstance()
    {
        if (suggestionDB == null)
            suggestionDB = new SuggestionDB();
         
        return suggestionDB;
    }
}
