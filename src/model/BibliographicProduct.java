package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class BibliographicProduct {
    String ID;
    String name;
    int numberOfPages;
    Calendar publicationDate;
    String URL;
    double value;
    int copiesSold;
    int numberOfPagesRead;
    private SimpleDateFormat sdf;

    public BibliographicProduct(
            String name,
            int numberOfPages,
            Calendar publicationDate,
            String URL,
            double value) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.publicationDate = publicationDate;
        this.URL = URL;
        this.value = value;
        this.copiesSold = 0;
        this.numberOfPagesRead = 0;
        sdf = new SimpleDateFormat("dd/MM/yyyy");

    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String toString() {
        return "ID:" + ID + "\nName: " + name + "\nNumber of pages: " + numberOfPages + "\nPublication date:"
                + sdf.format(publicationDate.getTime()) + "\nURL:" + URL + "\nValue:" + value +
                "\nCopies sold:" + copiesSold + "\nnumber of pages read: " + numberOfPagesRead;
    }
}
