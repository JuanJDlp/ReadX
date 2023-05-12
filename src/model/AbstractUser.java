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
    protected ArrayList<BibliographicProduct> car;

    public AbstractUser(String ID, String name, Calendar dateOfEntering) {
        this.ID = ID;
        this.name = name;
        this.dateOfEntering = dateOfEntering;
        products = new ArrayList<>();
        recipts = new ArrayList<>();
        car = new ArrayList<>();
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

    public ArrayList<BibliographicProduct> getCar() {
        return car;
    }

    public String checkOutShoppingCart() {
        products.addAll(car);
        Recipt recipt = new Recipt(car);
        recipts.add(recipt);
        car.clear();
        return recipt.getContent();
    }

    public String addProductToCar(BibliographicProduct product) {
        String msg = "The user alredy added this product to his car";
        if (!isProductInCar(product.getID())) {
            try {
                car.add(product.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            if (product instanceof Book) {
                msg = "Product added to user cart";
            } else if (product instanceof Magazine) {
                msg = "The magazine was aded to the car.";
            }
        }

        return msg;
    }

    public boolean isProductInCar(String ID) {
        boolean found = false;
        BibliographicProduct product = null;
        for (int i = 0; i < car.size() && !found; i++) {
            if (car.get(i).getID().toLowerCase().equals(ID.toLowerCase())) {
                found = true;
                product = car.get(i);
            }
        }
        return product != null;
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
        BibliographicProduct product = getProductByID(productID);
        if (products.contains(product) && product != null) {
            products.remove(product);
            msg = "User " + name + " was unsubscribed to the magazine!";
        }
        return msg;
    }

    /**
     * The function adds a bibliographic product to a list and generates a message
     * based on the type of
     * product added.
     * 
     * @param product A BibliographicProduct object that represents the product
     *                being added to the system.
     *                It could be either a Book or a Magazine object.
     * @return The method is returning a message (String) indicating whether a
     *         product was added to the
     *         user or if the user was subscribed to a magazine. The specific
     *         message depends on the type of
     *         product being added (Book or Magazine) and the name of the user.
     */
    public String addProduct(BibliographicProduct product) {
        String msg = "";
        try {
            products.add(product.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        if (product instanceof Book) {
            msg = "Product added to user " + name;
        } else if (product instanceof Magazine) {
            msg = "User " + name + " was subscribed to the magazine!";
        }
        return msg;
    }

    public String toString() {
        return "ID: " + ID + "\nName: " + name + "\nDate of entering: " + Utils.format(dateOfEntering.getTime());
    }
}
