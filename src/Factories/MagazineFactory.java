package Factories;

import java.util.Calendar;

import Interfaces.IAbstractBibliograpgicProductFractory;
import model.BibliographicProduct;
import model.Magazine;
import model.Enumerations.Category;

public class MagazineFactory implements IAbstractBibliograpgicProductFractory {

    public MagazineFactory() {

    }

    /**
     * This function creates a new Magazine object with the given parameters and
     * returns it.
     * 
     * @param name           The name of the magazine being created.
     * @param numberOfPages  an integer representing the number of pages in the
     *                       magazine.
     * @param pubicationDate A Calendar object representing the date of publication
     *                       of the magazine.
     * @param URL            The URL parameter is a String that represents the web
     *                       address or link to the online
     *                       version of the magazine.
     * @param value          The value parameter in the createProduct method
     *                       represents the monetary value of the
     *                       magazine being created.
     * @param extraArgument  The "extraArgument" parameter is a String that can be
     *                       used to pass any
     *                       additional information or data that may be required to
     *                       create the product. It is not used in this
     *                       specific implementation of the "createProduct" method.
     * @param category_genre An integer value representing the category or genre of
     *                       the magazine. It can be
     *                       1 for Varieties, 2 for Design, or 3 for Scientific.
     * @return A Magazine object is being returned.
     */
    @Override
    public BibliographicProduct createProduct(
            String name,
            int numberOfPages,
            Calendar pubicationDate,
            String URL,
            double value,
            String extraArgument,
            int category_genre) {
        Category category;
        switch (category_genre) {
            case 1:
                category = Category.VARIETIES;
                break;
            case 2:
                category = Category.DESIGN;
                break;
            case 3:
                category = Category.SCIENTIFIC;
                break;
            default:
                category = null;
        }

        return new Magazine(name, numberOfPages, pubicationDate, URL, value, extraArgument, category);
    }
}
