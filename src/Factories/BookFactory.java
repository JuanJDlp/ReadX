package Factories;

import java.util.Calendar;

import Interfaces.IAbstractBibliograpgicProductFractory;
import model.BibliographicProduct;
import model.Book;
import model.Enumerations.Genre;

public class BookFactory implements IAbstractBibliograpgicProductFractory {
    public BookFactory() {

    }

    /**
     * This function creates a new Book object with specified parameters including
     * genre.
     * 
     * @param name           The name of the bibliographic product (in this case, a
     *                       book).
     * @param numberOfPages  The number of pages in the book.
     * @param pubicationDate A Calendar object representing the publication date of
     *                       the book.
     * @param URL            The URL parameter is a String that represents the web
     *                       address or link to the product's
     *                       online location.
     * @param value          The value parameter in the createProduct method
     *                       represents the price or cost of the
     *                       book being created.
     * @param extraArgument  The "extraArgument" parameter is a String that can be
     *                       used to pass any
     *                       additional information or data that may be required to
     *                       create the BibliographicProduct object. It is
     *                       not used in this specific implementation of the
     *                       createProduct method.
     * @param category_genre an integer value representing the genre/category of the
     *                       book being created.
     *                       The possible values and their corresponding genres are:
     * @return A BibliographicProduct object of type Book is being returned.
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
        Genre genre = null;
        switch (category_genre) {
            case 1:
                genre = Genre.SCIENCE_FICTION;
                break;
            case 2:
                genre = Genre.FANTASY;
                break;
            case 3:
                genre = Genre.HISTORICAL_NOVEL;
                break;
            default:
        }
        return new Book(name, numberOfPages, pubicationDate, URL, value, extraArgument, genre);
    }
}