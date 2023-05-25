package Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.BibliographicProduct;

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

    public static void quickSort(ArrayList<BibliographicProduct> products, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(products, low, high);
            quickSort(products, low, pivotIndex - 1);
            quickSort(products, pivotIndex + 1, high);
        }
    }

    private static int partition(ArrayList<BibliographicProduct> products, int low, int high) {
        int pivot = products.get(high).getNumberOfPagesRead();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (products.get(j).getNumberOfPagesRead() < pivot) {
                i++;
                swap(products, i, j);
            }
        }

        swap(products, i + 1, high);
        return i + 1;
    }

    private static void swap(ArrayList<BibliographicProduct> products, int i, int j) {
        BibliographicProduct temp = products.get(i);
        products.set(i, products.get(j));
        products.set(j, temp);
    }
}
