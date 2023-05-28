package model;

import java.util.ArrayList;
import java.util.Calendar;
import Utils.Utils;

public abstract class AbstractUser {
    protected String ID;
    protected String name;
    protected Calendar dateOfEntering;
    protected Libray library;
    protected ArrayList<Recipt> recipts;

    public AbstractUser(String ID, String name, Calendar dateOfEntering) {
        this.ID = ID;
        this.name = name;
        this.dateOfEntering = dateOfEntering;
        library = new Libray();
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

    public Libray getLibrary() {
        return library;
    }

    public ArrayList<Recipt> getRecipts() {
        return recipts;
    }

    public ArrayList<BibliographicProduct> getCart() {
        return library.getCart();
    }

    /**
     * This function adds a bibliographic product to a library's shopping cart and
     * returns a string
     * indicating success or failure.
     * 
     * @param product The parameter "product" is an object of the class
     *                "BibliographicProduct". It is being
     *                passed as an argument to the method "addProductToCar".
     * @return The method is returning a String value, which is the result of
     *         calling the `addProductToCar`
     *         method of the `library` object and passing in the `product`
     *         parameter.
     */
    public String addProductToCar(BibliographicProduct product) {
        return library.addProductToCar(product);
    }

    /**
     * The function counts the number of books in a shopping cart.
     * 
     * @return The method `amountOfBook` returns an integer value which
     *         represents the number of
     *         books in the `car` list.
     */
    public int amountOfBook(ArrayList<BibliographicProduct> array) {
        int counter = 0;
        for (BibliographicProduct entry : array) {
            if (entry != null && entry instanceof Book)
                counter++;
        }
        return counter;
    }

    /**
     * This function counts the number of magazines in a shopping cart.
     * 
     * @return The method `amountOfMagazines` returns an integer value which
     *         represents the number
     *         of magazines in the `car` list.
     */
    public int amountOfMagazines(ArrayList<BibliographicProduct> array) {
        int counter = 0;
        for (BibliographicProduct entry : array) {
            if (entry != null && entry instanceof Magazine)
                counter++;
        }
        return counter;
    }

    /**
     * The function returns a string containing the names and IDs of all
     * bibliographic products in a list.
     * 
     * @return The method `productsOfAUser()` is returning a string containing
     *         information about the
     *         products of a user, including the name and ID of each product.
     */
    public String productsOfAUser() {
        String info = "";
        for (int i = 0; i < library.size(); i++) {
            info += library.showMatrix(i);
        }
        return info;
    }

    /**
     * The function checks if the user has a product with a given ID .
     * 
     * @param ID ID is a String parameter representing the unique identifier of a
     *           product.
     * @return A boolean value indicating whether a product with the given ID exists
     *         in the system or not.
     */
    public boolean hasProduct(String ID) {
        return getProductByID(ID) != null;
    }

    /**
     * This function searches for a BibliographicProduct object in a list of
     * products by its ID and returns
     * it.
     * 
     * @param ID The ID parameter is a String that represents the unique identifier
     *           of a bibliographic
     *           product. The method searches for a product with the matching ID in
     *           a list of products and returns
     *           the product if found.
     * @return The method is returning a BibliographicProduct object with the
     *         specified ID.
     */
    public BibliographicProduct getProductByID(String ID) {
        return library.getProduct(ID);
    }

    /**
     * The function removes a magazine from a user's subscription list if they have
     * it.
     * 
     * @param productID a String representing the ID of the product (magazine) that
     *                  the user wants to
     *                  unsubscribe from.
     * @return The method is returning a message (String) indicating whether the
     *         user was unsubscribed from
     *         the magazine or not. If the user does not have the magazine, the
     *         message will be "This user does not
     *         have this magazine". If the user has the magazine and it is
     *         successfully removed, the message will
     *         be "User [name] was unsubscribed to the magazine!".
     */
    public String removeProduct(String productID) {
        String msg = "This user does not have this magazine";
        Boolean wasDeleted = library.deleteProduct(productID);
        if (wasDeleted) {
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
            library.addProduct(product.clone());
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

    /**
     * The function checks out a shopping cart from a library and generates a
     * receipt for it.
     * 
     * @return The method `checkOutShoppingCart()` returns the content of a receipt
     *         generated from the
     *         `library.checkOutShoppingCart()` method call.
     */

    public String checkOutShoppingCart() {
        Recipt recipt = new Recipt(library.checkOutShoppingCart());
        recipts.add(recipt);
        return recipt.getContent();
    }

    /**
     * This function returns the list of magazines available in a library.
     * 
     * @return The method `magazinesOfAUser()` is returning a string that represents
     *         the magazines of a
     *         user in the library. The exact content of the string depends on the
     *         implementation of the
     *         `getMagazines()` method in the `library` object.
     */
    public String magazinesOfAUser() {
        return library.getMagazines();
    }

    public String toString() {
        return "ID: " + ID + "\nName: " + name + "\nDate of entering: " + Utils.format(dateOfEntering.getTime());
    }
}
