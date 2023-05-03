package model;

import java.util.Calendar;

public class Magazine extends BibliographicProduct {
    private String frecuencyOfIssuance;
    private Category category;

    public enum Category {// Is this a good practice?
        VARIETIES,
        DESIGN,
        SCIENTIFIC
    }

    public Magazine(
            String name,
            int numberOfPages,
            Calendar publicationDate,
            String URL,
            double value,
            String frecuencyOfIssuance,
            Category category) {

        super(name, numberOfPages, publicationDate, URL, value);
        this.frecuencyOfIssuance = frecuencyOfIssuance;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getFrecuencyOfIssuance() {
        return frecuencyOfIssuance;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setFrecuencyOfIssuance(String frecuencyOfIssuance) {
        this.frecuencyOfIssuance = frecuencyOfIssuance;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Frecuency of issuance: " + frecuencyOfIssuance +
                "\nCCategory: " + category;

    }
}