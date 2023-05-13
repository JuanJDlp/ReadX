package model;

import java.util.Calendar;

public class Standar extends AbstractUser {

    public Standar(String ID, String name, Calendar dateOfEntering) {
        super(ID, name, dateOfEntering);
    }

    /**
     * This function counts the number of book objects in an array of bibliographic
     * products.
     * 
     * @return The method `amountOfBook()` returns an integer value which represents
     *         the number of `Book`
     *         objects in the `products` array.
     */
    public int amountOfBook() {
        int counter = 0;
        for (BibliographicProduct entry : super.products) {
            if (entry != null && entry instanceof Book)
                counter++;
        }
        return counter;
    }

    /**
     * This Java function counts the number of Magazine objects in an array of
     * BibliographicProduct
     * objects.
     * 
     * @return The method `amountOfMagazines()` returns an integer value which
     *         represents the number of
     *         magazines in the `products` array.
     */
    public int amountOfMagazines() {
        int counter = 0;
        for (BibliographicProduct entry : products) {
            if (entry != null && entry instanceof Magazine)
                counter++;
        }
        return counter;
    }

}
