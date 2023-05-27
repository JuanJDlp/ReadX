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

    /**
     * This is a recursive implementation of the quicksort algorithm that sorts an
     * ArrayList of
     * BibliographicProduct objects based on a pivot element.
     * 
     * @param products An ArrayList of BibliographicProduct objects that needs to be
     *                 sorted using the
     *                 quicksort algorithm.
     * @param low      The index of the first element in the subarray to be sorted.
     * @param high     The index of the last element in the subarray being sorted.
     */
    public static void quickSort(ArrayList<BibliographicProduct> products, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(products, low, high);
            quickSort(products, low, pivotIndex - 1);
            quickSort(products, pivotIndex + 1, high);
        }
    }

    /**
     * This is a Java function that partitions an ArrayList of BibliographicProduct
     * objects based on their
     * number of pages read using the quicksort algorithm.
     * 
     * @param products an ArrayList of BibliographicProduct objects that needs to be
     *                 sorted
     * @param low      The index of the first element in the subarray being
     *                 partitioned.
     * @param high     The index of the last element in the subarray being
     *                 partitioned.
     * @return The method is returning an integer value which represents the index
     *         of the pivot element
     *         after partitioning the ArrayList.
     */
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

    /**
     * The function swaps two elements in an ArrayList of BibliographicProduct
     * objects.
     * 
     * @param products An ArrayList of BibliographicProduct objects that contains
     *                 the products to be
     *                 swapped.
     * @param i        The index of the first element to be swapped in the
     *                 ArrayList.
     * @param j        The parameter "j" is an integer representing the index of the
     *                 second element to be swapped
     *                 in the ArrayList "products".
     */
    private static void swap(ArrayList<BibliographicProduct> products, int i, int j) {
        BibliographicProduct temp = products.get(i);
        products.set(i, products.get(j));
        products.set(j, temp);
    }
}
