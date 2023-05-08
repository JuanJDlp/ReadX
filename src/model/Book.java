package model;

import java.util.Calendar;
import java.util.Random;

public class Book extends BibliographicProduct {
    private String shortReview;
    private Genre genre;

    public enum Genre { // Is this a good practice?
        SCIENCE_FICTION,
        FANTASY,
        HISTORICAL_NOVEL,
    }

    public Book(
            String name,
            int numberOfPages,
            Calendar publicationDate,
            String URL,
            double value,
            String shortReview,
            Genre genre) {

        super(name, numberOfPages, publicationDate, URL, value);
        super.ID = hexGenerator();
        this.shortReview = shortReview;
        this.genre = genre;

    }

    public Genre getGenre() {
        return genre;
    }

    public String getShortReview() {
        return shortReview;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setShortReview(String shortReview) {
        this.shortReview = shortReview;
    }

    private String hexGenerator() {
        Random random = new Random();
        String hexadecimal = "ABCDEF0123456789";
        String hexId = "";
        for (int i = 0; i < 3; i++) {
            hexId += hexadecimal.charAt((int) (random.nextInt(hexadecimal.length())));
        }
        return hexId;
    }

    public void reCreateID() {
        super.ID = hexGenerator();
    }

    public String labbeldAttributes() {
        return super.labbeldAttributes() + "\n" +
                "6.Short Review: '" + shortReview + "'" +
                "\n7.Genre: " + genre;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Short Review: '" + shortReview + "'" +
                "\nGenre: " + genre;
    }

}
