package Factories;

import java.util.Calendar;

import Interfaces.IAbstractBibliograpgicProductFractory;
import model.BibliographicProduct;
import model.Magazine;
import model.Enumerations.Category;

public class MagazineFactory implements IAbstractBibliograpgicProductFractory {

    public MagazineFactory() {

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
