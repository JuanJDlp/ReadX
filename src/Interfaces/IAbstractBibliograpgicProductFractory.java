package Interfaces;

import java.util.Calendar;

import model.BibliographicProduct;

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
