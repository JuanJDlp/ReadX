package model;

import java.util.Calendar;

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

    public String toString() {
        return super.toString() + "\n" +
                "Short Review: '" + shortReview +
                "\nGenre: " + genre;
    }

}
