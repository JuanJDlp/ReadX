package model;

import java.util.ArrayList;
import java.util.Calendar;
import MyHashMap.MyHashMap;
import Utils.Utils;

public abstract class AbstractUser {
    protected String ID;
    protected String name;
    protected Calendar dateOfEntering;
    protected MyHashMap<String, BibliographicProduct> products;
    protected ArrayList<Recipt> recipts;

    public AbstractUser(String ID, String name, Calendar dateOfEntering) {
        this.ID = ID;
        this.name = name;
        this.dateOfEntering = dateOfEntering;
        products = new MyHashMap<>();
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

    public MyHashMap<String, BibliographicProduct> getProducts() {
        return products;
    }

    public ArrayList<Recipt> getRecipts() {
        return recipts;
    }

    public String addProduct(BibliographicProduct product) {
        String msg = "";
        products.put(product.getID(), product);
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
        for (MyHashMap.Node<String, BibliographicProduct> entry : products) {
            if (entry != null && entry.getValue() instanceof Book)
                counter++;
        }
        return counter;
    }

    public int amountOfMagazines() {
        int counter = 0;
        for (MyHashMap.Node<String, BibliographicProduct> entry : products) {
            if (entry != null && entry.getValue() instanceof Magazine)
                counter++;
        }
        return counter;

    }

    public String removeProduct(String productID) {
        String msg = "This user does not have this magazine";

        if (products.containsKey(productID)) {
            products.remove(ID);
            msg = "User " + name + " was unsubscribed to the magazine!";
        }
        return msg;
    }

    public String toString() {
        return "ID: " + ID + "\nName: " + name + "\nDate of entering: " + Utils.format(dateOfEntering.getTime());
    }
}
