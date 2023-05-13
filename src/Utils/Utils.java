package Utils;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    /**
     * The function formats a given date object into a string with the format
     * "dd/MM/yyyy".
     * 
     * @param date The input parameter of type Date that needs to be formatted.
     * @return A string representation of the input date in the format "dd/MM/yyyy".
     */
    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    /**
     * The function converts a string representation of a date in the format
     * "dd/MM/yyyy" to a Calendar
     * object.
     * 
     * @param dateString A string representing a date in the format "dd/MM/yyyy".
     * @return A Calendar object is being returned.
     */
    public static Calendar stringToCalendar(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(dateString));
        return calendar;
    }
}
