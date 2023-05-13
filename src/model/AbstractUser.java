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

    /**
     * The function adds all products in a shopping cart to a list of products,
     * creates a receipt object
     * with the shopping cart items, adds the receipt to a list of receipts, clears
     * the shopping cart, and
     * returns the content of the receipt.
     * 
     * @return The method is returning the content of the receipt generated for the
     *         items in the shopping
     *         cart.
     */
    public String checkOutShoppingCart() {
        products.addAll(car);
        Recipt recipt = new Recipt(car);
        recipts.add(recipt);
        car.clear();
        return recipt.getContent();
    }

    /**
     * The function adds a bibliographic product to a user's cart and returns a
     * message indicating whether
     * the product was successfully added or if it was already in the cart.
     * 
     * @param product a BibliographicProduct object that represents the product
     *                being added to the user's
     *                car. It could be either a Book or a Magazine object.
     * @return The method is returning a message indicating whether the product was
     *         successfully added to
     *         the user's cart or not. The message varies depending on the type of
     *         product being added. If the
     *         product is already in the cart, the message will indicate that the
     *         user has already added it.
     */
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

    /**
     * This function checks if a product with a given ID is present in the shopping
     * cart.
     * 
     * @param ID The ID parameter is a String representing the unique identifier of
     *           a bibliographic
     *           product.
     * @return The method is returning a boolean value indicating whether a product
     *         with the given ID is
     *         present in the "car" list or not.
     */
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

    /**
     * The function counts the number of books in a shopping cart.
     * 
     * @return The method `amountOfBookInCart()` returns an integer value which
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
     * @return The method `amountOfMagazinesInCart()` returns an integer value which
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
        for (BibliographicProduct entry : products) {
            info += "Name: " + entry.getName() + "| ID:  " + entry.getID() + " " + "\n";
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
     * This Java function searches for a product in a list by its ID and returns its
     * position.
     * 
     * @param ID The ID parameter is a String representing the unique identifier of
     *           a product that we want
     *           to find in a list of products.
     * @return The method is returning an integer value which represents the
     *         position of the product in the
     *         list of products with the given ID. If the product is not found, the
     *         method returns -1.
     */
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
        boolean found = false;
        BibliographicProduct product = null;
        for (int i = 0; i < products.size() && !found; i++)
            if (products.get(i).getID().toLowerCase().equals(ID.toLowerCase())) {
                found = true;
                product = products.get(i);
            }
        return product;
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
