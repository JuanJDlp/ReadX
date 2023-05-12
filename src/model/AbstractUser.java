package model;

import java.util.ArrayList;
import java.util.Calendar;
import Utils.Utils;

public abstract class AbstractUser {
    protected String ID;
    protected String name;
    protected Calendar dateOfEntering;
    protected ArrayList<BibliographicProduct> products;
    protected ArrayList<Recipt> recipts;

    public AbstractUser(String ID, String name, Calendar dateOfEntering) {
        this.ID = ID;
        this.name = name;
        this.dateOfEntering = dateOfEntering;
        products = new ArrayList<>();
        recipts = new ArrayList<>();
    }

    public Calendar getDateOfEntering() {
        return dateOfEntering;
    }

    public void setDateOfEntering(Calendar dateOfEntering) {
        this.dateOfEntering = dateOfEntering;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<BibliographicProduct> getProducts() {
        return products;
    }

    public ArrayList<Recipt> getRecipts() {
        return recipts;
    }

    public String addProduct(BibliographicProduct product) {
        String msg = "";
        try {
            products.add(product.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        recipts.add(new Recipt(product.getValue()));
        if (product instanceof Book) {
            msg = "Product added to user " + name;
        } else if (product instanceof Magazine) {
            msg = "User " + name + " was subscribed to the magazine!";
        }
        return msg;
    }

    public int amountOfBook() {
        int counter = 0;
        for (BibliographicProduct entry : products) {
            if (entry != null && entry instanceof Book)
                counter++;
        }
        return counter;
    }

    public int amountOfMagazines() {
        int counter = 0;
        for (BibliographicProduct entry : products) {
            if (entry != null && entry instanceof Magazine)
                counter++;
        }
        return counter;
    }

    public String productsOfAUser() {
        String info = "";
        for (BibliographicProduct entry : products) {
            info += "Name: " + entry.getName() + "| ID:  " + entry.getID() + " " + "\n";
        }
        return info;
    }

    public boolean hasProduct(String ID) {
        return getProductByID(ID) != null;
    }

    public int findProductByID(String ID) {
        boolean found = false;
        int position = -1;
        for (int i = 0; i < products.size() && !found; i++)
            if (products.get(i).getID().toLowerCase().equals(ID.toLowerCase())) {
                found = true;
                position = i;
            }
        return position;
    }

    public BibliographicProduct getProductByID(String ID) {
        boolean found = false;
        BibliographicProduct product = null;
        for (int i = 0; i < products.size() && !found; i++)
            if (products.get(i).getID().toLowerCase().equals(ID.toLowerCase())) {
                found = true;
                product = products.get(i);
            }
        return product;
    }

    public String removeProduct(String productID) {
        String msg = "This user does not have this magazine";
        BibliographicProduct product = products.get(findProductByID(productID));
        if (products.contains(product)) {
            products.remove(product);
            msg = "User " + name + " was unsubscribed to the magazine!";
        }
        return msg;
    }

    public String toString() {
        return "ID: " + ID + "\nName: " + name + "\nDate of entering: " + Utils.format(dateOfEntering.getTime());
    }
}
