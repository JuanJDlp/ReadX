package model;

import java.util.Calendar;

import Interfaces.INavigable;
import Utils.Utils;

public abstract class BibliographicProduct implements Cloneable, INavigable, Comparable<BibliographicProduct> {
    protected String ID;
    protected String name;
    protected int numberOfPages;
    protected Calendar publicationDate;
    protected String URL;
    protected double value;
    protected int copiesSold;
    protected int numberOfPagesRead;
    protected int currentPageBeingRead;
    protected int NumberLastPageRead;

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
        this.currentPageBeingRead = 0;
        this.NumberLastPageRead = -1;

    }

    public int getCurrentPageBeingRead() {
        return currentPageBeingRead;
    }

    public void setCurrentPageBeingRead(int currentPageBeingRead) {
        this.currentPageBeingRead = currentPageBeingRead;
    }

    public int getNumberLastPageRead() {
        return NumberLastPageRead;
    }

    public void setNumberLastPageRead(int numberLastPageRead) {
        NumberLastPageRead = numberLastPageRead;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public int getCopiesSold() {
        return copiesSold;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getNumberOfPagesRead() {
        return numberOfPagesRead;
    }

    public Calendar getPublicationDate() {
        return publicationDate;
    }

    public String getPublicationDateString() {
        return Utils.format(publicationDate.getTime());
    }

    public String getURL() {
        return URL;
    }

    public double getValue() {
        return value;
    }

    public void setCopiesSold(int copiesSold) {
        this.copiesSold = copiesSold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setNumberOfPagesRead(int numberOfPagesRead) {
        this.numberOfPagesRead = numberOfPagesRead;
    }

    public void setPublicationDate(Calendar publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public abstract String idGenerator();

    /**
     * This function overrides the clone method of the Object class to create a
     * clone of a
     * BibliographicProduct object.
     * 
     * @return A cloned instance of the BibliographicProduct class is being
     *         returned.
     */

    public BibliographicProduct clone() throws CloneNotSupportedException {
        return (BibliographicProduct) super.clone();
    }

    /**
     * The function increments the current page being read and updates the number of
     * pages read and last
     * page read if necessary, and resets the current page being read if it exceeds
     * the total number of
     * pages. This methods is used for the readin session.
     * 
     * @return The method `nextPage()` returns an integer value, which is the
     *         current page being read after
     *         the method updates the `currentPageBeingRead`, `NumberLastPageRead`,
     *         and `numberOfPagesRead`
     *         variables.
     */
    public int nextPage() {

        this.currentPageBeingRead++;

        if (this.currentPageBeingRead > this.NumberLastPageRead) {
            this.NumberLastPageRead = this.currentPageBeingRead;
            this.numberOfPagesRead++;
        }

        if (this.currentPageBeingRead > this.numberOfPages) {
            this.currentPageBeingRead = 0;
        }

        return this.currentPageBeingRead;
    }

    /**
     * This function decreases the current page being read by one and returns the
     * new value, ensuring that
     * the value does not go below zero.
     * It is used for the readin session.
     * 
     * @return The method `previousPage()` returns an integer value which represents
     *         the current page being
     *         read after decrementing it by 1.
     */
    public int previousPage() {
        this.currentPageBeingRead--;
        if (this.currentPageBeingRead < 0) {
            this.currentPageBeingRead = 0;
        }
        return this.currentPageBeingRead;
    }

    /**
     * The function returns a string containing labeled attributes of a prodict.
     * 
     * @return A string containing the labeled attributes of an object, including
     *         its name, number of
     *         pages, publication date, URL, and value.
     */
    public String labbeldAttributes() {
        return "\n1.Name: " + name + "\n2.Number of pages: " + numberOfPages + "\n3.Publication date:"
                + Utils.format(publicationDate.getTime()) + "\n4.URL:" + URL + "\n5.Value:" + value;
    }

    /**
     * This function compares the publication dates of two BibliographicProduct
     * objects.
     * 
     * @param product The parameter "product" is an object of the class
     *                "BibliographicProduct" which is
     *                being compared to the current object using the "compareTo"
     *                method.
     * @return The method is returning an integer value that represents the
     *         comparison between the
     *         publication dates of two BibliographicProduct objects. The value
     *         returned will be negative if
     *         the publication date of the current object is earlier than the
     *         publication date of the compared
     *         object, zero if they are the same, and positive if the publication
     *         date of the current object is
     *         later than the publication date of the compared object.
     */
    @Override
    public int compareTo(BibliographicProduct product) {
        return this.getPublicationDate().compareTo(product.getPublicationDate());
    }

    public String toString() {
        return "ID:" + ID + "\nName: " + name + "\nNumber of pages: " + numberOfPages + "\nPublication date:"
                + Utils.format(publicationDate.getTime()) + "\nURL:" + URL + "\nValue:" + value +
                "\nCopies sold:" + copiesSold + "\nnumber of pages read: " + numberOfPagesRead;
    }

}
