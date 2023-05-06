package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import MyHashMap.MyHashMap;

public abstract class AbstractUser {
    protected String ID;
    protected String name;
    protected Calendar dateOfEntering;
    protected MyHashMap<String, BibliographicProduct> products;
    protected ArrayList<Recipt> recipts;
    private SimpleDateFormat sdf;

    public AbstractUser(String ID, String name, Calendar dateOfEntering) {
        this.ID = ID;
        this.name = name;
        this.dateOfEntering = dateOfEntering;
        sdf = new SimpleDateFormat("dd/MM/yyyy");
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

    public void addProduct(BibliographicProduct product) {
        products.put(product.getID(), product);
        recipts.add(new Recipt(product.getValue()));
    }

    public String toString() {
        return "ID: " + ID + "\nName: " + name + "\nDate of entering: " + sdf.format(dateOfEntering.getTime());
    }
}
