package helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversionHelper {
    public Date convertDate(String dateString) {
        // Define the date format to match the input string
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        try {
            // Parse the input date string into a Date object
            Date date = dateFormat.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
