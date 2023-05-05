package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class BibliographicProduct {
    protected String ID;
    protected String name;
    protected int numberOfPages;
    protected Calendar publicationDate;
    protected String URL;
    protected double value;
    protected int copiesSold;
    protected int numberOfPagesRead;
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
        return sdf.format(publicationDate.getTime());
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

    public String labbeldAttributes() {

        return "\n1.Name: " + name + "\n2.Number of pages: " + numberOfPages + "\n3.Publication date:"
                + sdf.format(publicationDate.getTime()) + "\n4.URL:" + URL + "\n5.Value:" + value;

    }

    public String toString() {
        return "ID:" + ID + "\nName: " + name + "\nNumber of pages: " + numberOfPages + "\nPublication date:"
                + sdf.format(publicationDate.getTime()) + "\nURL:" + URL + "\nValue:" + value +
                "\nCopies sold:" + copiesSold + "\nnumber of pages read: " + numberOfPagesRead;
    }

}
