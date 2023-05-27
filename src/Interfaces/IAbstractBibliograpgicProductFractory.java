package Interfaces;

import java.util.Calendar;

import model.BibliographicProduct;

/**
 * This is an interface in Java named `IAbstractBibliograpgicProductFractory`
 * that declares a method
 * `createProduct` which returns an object of type `BibliographicProduct`. The
 * method takes in several
 * parameters such as the name of the product, number of pages, publication
 * date, URL, value, extra
 * argument, and category/genre. This interface can be implemented by any class
 * that wants to create a
 * `BibliographicProduct` object.
 */
public interface IAbstractBibliograpgicProductFractory {
    BibliographicProduct createProduct(
            String name,
            int numberOfPages,
            Calendar pubicationDate,
            String URL,
            double value,
            String extraArgument,
            int category_genre);
}
