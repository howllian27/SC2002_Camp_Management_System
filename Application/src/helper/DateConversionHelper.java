package helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * The DateConversionHelper class is responsible for converting a date string into a Date object.
 * 
 * @author Chan Hin Wai Howell
 * @version 1.0
*/
public class DateConversionHelper {
    public static Date convertDate(String dateString) {
        // Define the date format to match the input string
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        try {
            // Parse the input date string into a Date object
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
