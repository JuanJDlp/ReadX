package Factories;

import java.util.Calendar;

import Interfaces.IAbstractBibliograpgicProductFractory;
import model.BibliographicProduct;
import model.Book;
import model.Enumerations.Genre;

public class BookFactory implements IAbstractBibliograpgicProductFractory {
    public BookFactory() {

    }

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