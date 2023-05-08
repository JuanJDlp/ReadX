package model;

import java.util.Calendar;
import java.util.Random;

import model.Enumerations.Category;

public class Magazine extends BibliographicProduct {
    private String frecuencyOfIssuance;
    private Category category;

    public Magazine(
            String name,
            int numberOfPages,
            Calendar publicationDate,
            String URL,
            double value,
            String frecuencyOfIssuance,
            Category category) {

        super(name, numberOfPages, publicationDate, URL, value);
        super.ID = idGenerator();
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

    public String idGenerator() {
        Random random = new Random();
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            idBuilder.append(randomChar);
        }
        return idBuilder.toString();
    }

    public void reCreateID() {
        super.ID = idGenerator();
    }

    public String labbeldAttributes() {
        return super.labbeldAttributes() +
                "\n6.Frecuency of issuance: " + frecuencyOfIssuance +
                "\n7.Category: " + category;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Frecuency of issuance: " + frecuencyOfIssuance +
                "\nCategory: " + category;

    }
}
