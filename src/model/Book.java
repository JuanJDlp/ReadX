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
        Random rand = new Random();
        int myRandomNumber = rand.nextInt(0x1000);
        return Integer.toHexString(myRandomNumber).substring(0, 3);
    }

    public String toString() {
        return super.toString() + "\n" +
                "Short Review: '" + shortReview +
                "\nGenre: " + genre;
    }

}
