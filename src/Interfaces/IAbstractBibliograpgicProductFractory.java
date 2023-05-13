package Interfaces;

import java.util.Calendar;

import model.BibliographicProduct;

// This is an interface in Java named `IAbstractBibliograpgicProductFractory` that defines a method
// `createProduct` which takes in several parameters such as name, number of pages, publication date,
// URL, value, extra argument, and category genre. The method returns an object of type
// `BibliographicProduct`. This interface can be implemented by any class that wants to create a
// `BibliographicProduct` object.
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
