package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import Utils.Utils;
import Factories.BookFactory;
import Factories.MagazineFactory;
import Factories.UserFactory;
import MyHashMap.MyHashMap;
import model.Enumerations.Genre;
import model.Enumerations.Category;

public class Controller {
    private MyHashMap<String, AbstractUser> users;
    private ArrayList<BibliographicProduct> products;

    public Controller() {
        users = new MyHashMap<>();
        products = new ArrayList<>();
    }

    public MyHashMap<String, AbstractUser> getUsers() {
        return users;
    }

    public ArrayList<BibliographicProduct> getProducts() {
        return products;
    }

    public boolean areThereUsers() {
        return !users.isEmpty();
    }

    public boolean areTherProducts() {
        return !products.isEmpty();
    }

    /**
     * This function creates a user object using a user factory based on the given
     * name, ID, and type of
     * user.
     * 
     * @param name       The name of the user being created.
     * @param ID         The ID parameter is a unique identifier for the user being
     *                   created. It could be a string
     *                   or a number that distinguishes one user from another.
     * @param typeOfUser The typeOfUser parameter is an integer value that
     *                   represents the type of user
     *                   being created. The specific values and their meanings would
     *                   depend on the implementation of the
     *                   UserFactory class.
     * @return An instance of the AbstractUser class.
     */
    public AbstractUser createUser(String name, String ID, int typeOfUser) {
        UserFactory userFactory = new UserFactory();
        AbstractUser user = userFactory.createUser(ID, name, typeOfUser);
        return user;
    }

    /**
     * This function adds a new user to a collection of users if the user's ID is
     * not already present.
     * 
     * @param user An object of type AbstractUser that represents a user to be added
     *             to a collection of
     *             users.
     * @return The method is returning a boolean value, which indicates whether the
     *         user was successfully
     *         added to the collection or not. If the user was added, the method
     *         returns true. If the user was not
     *         added (because a user with the same ID already exists in the
     *         collection), the method returns false.
     */
    public boolean addUser(AbstractUser user) {
        boolean wasAdded = false;
        if (!users.containsKey(user.getID())) {
            users.put(user.getID().toLowerCase(), user);
            wasAdded = true;
        }
        return wasAdded;
    }

    /**
     * The function returns a string representation of a user's information based on
     * their ID.
     * 
     * @param ID The ID parameter is a String representing the unique identifier of
     *           a user in a collection
     *           or database of users. The method retrieves the user information
     *           associated with the given ID and
     *           returns it as a String.
     * @return The method is returning a string representation of the user
     *         information associated with the
     *         given ID. The exact format and content of the string will depend on
     *         the implementation of the
     *         `toString()` method for the user object.
     */
    public String showUserInfo(String ID) {
        return users.get(ID.toLowerCase()).toString();
    }

    /**
     * This function creates a bibliographic product based on the type of product
     * specified and returns it.
     * 
     * @param name           The name of the bibliographic product being created.
     * @param numberOfPages  an integer representing the number of pages in the
     *                       bibliographic product.
     * @param pubicationDate A Calendar object representing the publication date of
     *                       the bibliographic
     *                       product.
     * @param URL            A string representing the URL of the bibliographic
     *                       product.
     * @param value          The value parameter is a double data type that
     *                       represents the monetary value of the
     *                       bibliographic product.
     * @param extraArgument  The "extraArgument" parameter is a String that can be
     *                       used to pass any
     *                       additional information or arguments that may be
     *                       required by the specific implementation of the
     *                       BibliographicProduct. It is not used in the provided
     *                       code snippet, but it can be used to provide
     *                       additional details or customization options for the
     *                       created product
     * @param category_genre an integer representing the category or genre of the
     *                       bibliographic product
     *                       being created.
     * @param typeOfProduct  An integer value representing the type of bibliographic
     *                       product to be created.
     *                       A value of 1 represents a book, and a value of 2
     *                       represents a magazine.
     * @return The method is returning a BibliographicProduct object.
     */
    public BibliographicProduct createBibliographicProduct(
            String name,
            int numberOfPages,
            Calendar pubicationDate,
            String URL,
            double value,
            String extraArgument,
            int category_genre,
            int typeOfProduct) {

        BibliographicProduct product;

        switch (typeOfProduct) {
            case 1:
                BookFactory bookFactory = new BookFactory();
                product = bookFactory.createProduct(name, numberOfPages, pubicationDate, URL, value, extraArgument,
                        category_genre);
                break;
            case 2:
                MagazineFactory magazineFactory = new MagazineFactory();
                product = magazineFactory.createProduct(name, numberOfPages, pubicationDate, URL, value, extraArgument,
                        category_genre);
                break;
            default:
                product = null;

        }

        return product;

    }

    /**
     * The function adds a bibliographic product to a list of products, ensuring
     * that the product ID is
     * unique.
     * 
     * @param product an object of type BibliographicProduct, which is a superclass
     *                for Book and Magazine
     *                classes. It contains information about a product such as its
     *                ID, title, author, publisher, and
     *                publication date.
     * @return The method is returning a String, which is the ID of the
     *         BibliographicProduct that was added
     *         to the products list.
     */
    public String addProduct(BibliographicProduct product) {
        while (findProductByID(product.getID()) != -1) {
            if (product instanceof Book) {
                ((Book) product).reCreateID();
            } else if (product instanceof Magazine) {
                ((Magazine) product).reCreateID();
            }
        }
        products.add(product);
        return product.getID();
    }

    /**
     * The function returns product information based on the input ID, or "This item
     * does not exist" if the
     * ID is not found.
     * 
     * @param ID The ID parameter is a String that represents the unique identifier
     *           of a product.
     * @return The method `showProductInfoById` returns a string that contains
     *         either the information of
     *         the product with the given ID or the message "This item does not
     *         exist" if the product is not found.
     */
    public String showProductInfoById(String ID) {
        String info = "This item does not exist";
        if (findProductByID(ID) != -1) {
            info = products.get(findProductByID(ID)).toString();
        }
        return info;
    }

    /**
     * The function searches for a product by name and returns its information if
     * found, otherwise it
     * returns a message indicating that the item does not exist.
     * 
     * @param name The name of the product that the user wants to retrieve
     *             information for.
     * @return The method is returning a String that contains either the information
     *         of the product with
     *         the given name or the message "This item does not exist" if there is
     *         no product with that name in
     *         the list.
     */
    public String showProductInfoByName(String name) {
        String info = "This item does not exist";
        boolean found = false;
        for (int i = 0; i < products.size() && !found; i++)
            if (products.get(i).getName().equals(name)) {
                found = true;
                info = products.get(i).toString();
            }
        return info;
    }

    /**
     * This Java function returns a string containing the names and IDs of products
     * whose names contain a
     * given string.
     * 
     * @param productsName A String representing the name of the product(s) to
     *                     search for. The method will
     *                     return a list of products whose names contain the
     *                     specified string, regardless of case sensitivity.
     * @return The method is returning a String containing the names and IDs of all
     *         products whose name
     *         contains the input string (case-insensitive).
     */
    public String showProductsWithSimilarName(String productsName) {
        String info = "";
        for (int i = 0; i < products.size(); i++)
            if (products.get(i).getName().toLowerCase().contains(productsName.toLowerCase())) {
                info += products.get(i).getName() + " | " + products.get(i).getID() + "\n";
            }
        return info;
    }

    /**
     * This function deletes a product from a list and removes it from all users'
     * lists if it exists.
     * 
     * @param ID ID is a String parameter that represents the unique identifier of a
     *           product that needs to
     *           be deleted from the system.
     * @return The method is returning a message as a String. The message will be
     *         "We could not find this
     *         product" if the product with the given ID is not found, and "Product
     *         deleted" if the product is
     *         successfully deleted.
     */
    public String deleteProduct(String ID) {
        String msg = "We could not find this product";
        BibliographicProduct product = getProductByID(ID);
        if (product != null) {
            products.remove(product);
            for (MyHashMap.Node<String, AbstractUser> entry : users) {
                entry.getValue().removeProduct(ID);
            }
            msg = "Product deleted";
        }
        return msg;
    }

    /**
     * This function searches for a product in a list by its ID and returns its
     * position.
     * 
     * @param ID The ID parameter is a String that represents the unique identifier
     *           of a product. The
     *           method searches for a product in a list of products by comparing
     *           the ID of each product with the
     *           given ID parameter. If a product with the same ID is found, the
     *           method returns the index position of
     *           that product in the
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
     * This function converts an integer input to a corresponding genre enum value.
     * 
     * @param input An integer value representing a genre.
     * @return The method `makeIntInputToGenre` returns a `Genre` object based on
     *         the integer input
     *         provided. If the input is 1, it returns `Genre.SCIENCE_FICTION`, if
     *         it is 2, it returns
     *         `Genre.FANTASY`, if it is 3, it returns `Genre.HISTORICAL_NOVEL`, and
     *         if it is any other value, it
     */
    private Genre makeIntInputToGenre(int input) {
        switch (input) {
            case 1:
                return Genre.SCIENCE_FICTION;
            case 2:
                return Genre.FANTASY;
            case 3:
                return Genre.HISTORICAL_NOVEL;
            default:
                return null;
        }
    }

    /**
     * This function converts an integer input to a corresponding category enum
     * value.
     * 
     * @param input An integer value representing a category.
     * @return The method `makeIntInputToCategory` returns a `Category` enum value
     *         based on the integer
     *         input provided. If the input is 1, it returns `Category.VARIETIES`,
     *         if it is 2, it returns
     *         `Category.DESIGN`, if it is 3, it returns `Category.SCIENTIFIC`, and
     *         if it is any other value, it
     *         returns `null`.
     */
    private Category makeIntInputToCategory(int input) {
        switch (input) {
            case 1:
                return Category.VARIETIES;
            case 2:
                return Category.DESIGN;
            case 3:
                return Category.SCIENTIFIC;
            default:
                return null;
        }
    }

    /**
     * The function returns labeled attributes of a bibliographic product identified
     * by its ID, depending
     * on whether it is a book or a magazine.
     * 
     * @param ID The ID parameter is a String that represents the unique identifier
     *           of a bibliographic
     *           product.
     * @return The method is returning a string containing the labeled attributes of
     *         a product with the
     *         given ID. If the product is a book, it will return the labeled
     *         attributes of a book. If the product
     *         is a magazine, it will return the labeled attributes of a magazine.
     *         If the product is not found, an
     *         empty string will be returned.
     */
    public String showProductLabeledAttributes(String ID) {
        String info = "";
        BibliographicProduct product = getProductByID(ID);
        if (product != null) {
            if (product instanceof Book) {
                info = ((Book) product).labbeldAttributes();
            } else if (product instanceof Magazine) {
                info = ((Magazine) product).labbeldAttributes();
            }
        }
        return info;
    }

    /**
     * The function displays the product information based on the product ID and
     * option selected.
     * This methods is used to make it easier for the user to select the information
     * they want to change.
     * 
     * @param ID     A string representing the ID of a bibliographic product.
     * @param option an integer representing the user's selected option for
     *               displaying information about a
     *               product.
     * @return The method is returning a String variable named "info".
     */
    public String displayInformationByProduct(String ID, int option) {
        String info = "";
        BibliographicProduct product = getProductByID(ID);
        if (option == 6) {
            if (product instanceof Book) {
                info = "What would you like to change the review for: ";
            } else if (product instanceof Magazine) {
                info = "What would you like to change the frecuency of issuance for: ";
            }
        } else if (option == 7) {
            info = displayEnumAtributesBasedOnType(product);
        }

        return info;
    }

    /**
     * The function displays different attributes based on the type of bibliographic
     * product passed as a
     * parameter.
     * 
     * @param product an object of type BibliographicProduct, which is the parent
     *                class of Book and
     *                Magazine.
     * @return The method is returning a string that contains information about the
     *         attributes of a
     *         bibliographic product based on its type (book or magazine). If the
     *         product is a book, the string
     *         will prompt the user to select a new genre from a list of options. If
     *         the product is a magazine, the
     *         string will prompt the user to select a new category from a list of
     *         options.
     */
    private String displayEnumAtributesBasedOnType(BibliographicProduct product) {
        String info = "";
        if (product instanceof Book) {
            info = "Please select the new genre of the book: " + "\n" +
                    "1.Science fiction" + "\n" +
                    "2.Fantasy" + "\n" +
                    "3.Historical novel";

        } else if (product instanceof Magazine) {
            info = "Please select the new category of the magazine: " + "\n" +
                    "1.Varieties" + "\n" +
                    "2.Design" + "\n" +
                    "3.Scientific";

        }
        return info;
    }

    /**
     * This function changes the properties of a product based on the option
     * selected and returns a message
     * indicating the success or failure of the change.
     * 
     * @param productID A String representing the ID of the product to be changed.
     * @param option    An integer representing the option selected by the user to
     *                  change a specific attribute
     *                  of a product. The options are:
     * @param newValue  The new value that will replace the current value of the
     *                  selected product attribute.
     * @return The method is returning a String message indicating the result of the
     *         product change
     *         operation. The message can be one of the following: "We could not
     *         find this product", "Product name
     *         changed", "Product number of pages changed", "Product publication
     *         date changed", "Product URL
     *         changed", "Product value changed", "Product genre changed", or
     *         "Product category changed".
     */
    public String changeProduct(String productID, int option, String newValue) {
        String msg = "We could not find this product";
        BibliographicProduct product = getProductByID(productID);
        if (product != null) {
            switch (option) {
                case 1:
                    product.setName(newValue);
                    msg = "Product name changed";
                    break;
                case 2:
                    try {
                        product.setNumberOfPages(Integer.parseInt(newValue));
                        msg = "Product number of pages changed";
                    } catch (NumberFormatException e) {
                        msg = "It must be a numeric type";
                    }

                    break;
                case 3:
                    try {
                        product.setPublicationDate(Utils.stringToCalendar(newValue));
                        msg = "Product publication date changed";
                        break;
                    } catch (ParseException e) {
                        msg = "The date entered does not match the pattern (dd/MM/yyyy) \nIt wasn't posible to make the change.";
                    }
                case 4:
                    product.setURL((String) newValue);
                    msg = "Product URL changed";
                    break;
                case 5:
                    try {
                        product.setValue(Double.parseDouble(newValue));
                        msg = "Product value changed";
                    } catch (NumberFormatException e) {
                        msg = "It must be a numeric type";

                    }
                    break;
                case 6:
                    if (product instanceof Book) {
                        ((Book) product).setShortReview(newValue);
                    } else if (product instanceof Magazine) {
                        ((Magazine) product).setFrecuencyOfIssuance(newValue);
                    }
                case 7:
                    if (product instanceof Book) {
                        ((Book) product).setGenre((Genre) makeIntInputToGenre(Integer.parseInt(newValue)));
                        msg = "Product genre changed";
                    } else if (product instanceof Magazine) {
                        ((Magazine) product).setCategory((Category) makeIntInputToCategory(Integer.parseInt(newValue)));
                        msg = "Product category changed";
                    }
                    break;
            }

        }
        return msg;
    }

    /**
     * This function returns a BibliographicProduct object based on the ID provided,
     * or null if the ID is
     * not found in the products list.
     * 
     * @param ID ID is a String parameter representing the unique identifier of a
     *           bibliographic product.
     *           The method `getProductByID` takes this ID as input and returns the
     *           corresponding
     *           `BibliographicProduct` object from a list of products.
     * @return The method `getProductByID` returns a `BibliographicProduct` object
     *         with the specified ID if
     *         it exists in the `products` list, otherwise it returns `null`.
     */
    private BibliographicProduct getProductByID(String ID) {
        if (findProductByID(ID) == -1) {
            return null;
        }
        return products.get(findProductByID(ID));
    }

    /**
     * This function checks out the shopping cart of a user with a given userID. It
     * will empty all the products added into the car
     * The method is user to let the user buy multiple products at once.
     * 
     * @param userID The userID parameter is a String that represents the unique
     *               identifier of a user in
     *               the system. It is used to retrieve the user object from the
     *               users map and call the
     *               checkOutShoppingCart method on it.
     * @return The method is returning a String. If the user does not exist, it
     *         returns "The user does not
     *         exist". Otherwise, it calls the checkOutShoppingCart() method of the
     *         user object and returns the
     *         result.
     */
    public String checkOutShoppingCart(String userID) {
        AbstractUser user = users.get(userID.toLowerCase());
        if (user == null) {
            return "The user does not exist";
        }
        return user.checkOutShoppingCart();
    }

    /**
     * This function adds a product to a user's cart and checks for various
     * conditions before doing so.
     * 
     * @param userID    A String representing the ID of the user to whom the product
     *                  is being added.
     * @param productID The unique identifier of the product being added to the
     *                  user's cart.
     * @return The method may return one of the following strings:
     *         - "Either the user or the product ID was not found"
     *         - "The user has already 5 books"
     *         - "The user is already subscribed to 2 magazines"
     *         - "This user already has this product"
     *         - A message returned by the user's addProductToCar method. If the
     *         transaccion was completted.
     */
    public String addProductToUser(String userID, String productID) {
        AbstractUser user = users.get(userID.toLowerCase());
        BibliographicProduct product = getProductByID(productID);
        String msg = "";
        boolean flagForAmountOfProducts = true;

        if (user == null || product == null) {
            return "Either the user or the product ID was not found";
        }

        if (user.hasProduct(productID)) {
            return "This user already has this product";
        }

        if (user instanceof Standar) {
            if (theUserHasMoreThanFiveBooks(user) || theUserHasMoreThanTwoMagazines(user)) {
                msg = "The user has already 5 books or has more that 2 magazines";
                flagForAmountOfProducts = false;
            }
        }
        if (flagForAmountOfProducts) {
            product.setCopiesSold(product.getCopiesSold() + 1);
            msg = user.addProductToCar(product);
        }

        return msg;
    }

    private boolean theUserHasMoreThanFiveBooks(AbstractUser user) {
        boolean answer = false;
        if (((Standar) user).getLibrary().amountOfBook() >= 5
                || ((Standar) user).amountOfBook(user.getCart()) >= 5) {
            answer = true;
        }
        return answer;
    }

    private boolean theUserHasMoreThanTwoMagazines(AbstractUser user) {
        boolean answer = false;
        if (((Standar) user).getLibrary().amountOfMagazines() >= 2
                || ((Standar) user).amountOfMagazines(user.getCart()) >= 2) {
            answer = true;
        }
        return answer;
    }

    /**
     * The function unsubscribes a user from a magazine and updates the copies sold
     * count if the user and
     * magazine ID are found.
     * 
     * @param userID     A string representing the ID of the user who wants to
     *                   unsubscribe from a magazine.
     * @param magazineID The ID of the magazine that the user wants to unsubscribe
     *                   from.
     * @return The method returns a String message indicating whether the user was
     *         successfully
     *         unsubscribed from the magazine or if either the user or the magazine
     *         ID was not found.
     */
    public String unsubscribeMagazine(String userID, String magazineID) {
        String msg = "Either the user or the magazine ID was not found";
        BibliographicProduct product = getProductByID(magazineID);
        AbstractUser user = users.get(userID.toLowerCase());

        if (product != null && user != null) {
            if (product instanceof Magazine) {
                msg = user.removeProduct(magazineID);
                product.setCopiesSold(product.getCopiesSold() - 1);
            } else {
                msg = "The product you selceted is not a magazine";
            }
        }

        return msg;
    }

    /**
     * This function returns the products of a user if the user exists, otherwise it
     * returns a message
     * stating that the user does not exist.
     * 
     * @param userID The userID parameter is a String that represents the unique
     *               identifier of a user in
     *               the system. It is used to retrieve information about the user's
     *               products.
     * @return The method `productsOfAUser` returns a string containing information
     *         about the products of a
     *         user with the given `userID`. If the user does not exist, the method
     *         returns the string "The user
     *         does not exist".
     */
    public String productsOfAUser(String userID) {
        String info = "The user does not exist";
        AbstractUser user = users.get(userID.toLowerCase());
        if (user != null) {
            info = user.productsOfAUser();
        }
        return info;

    }

    /**
     * The function allows a user to start a reading session for a specific book,
     * with options to navigate
     * through pages and return to the library.
     * 
     * @param productID A string representing the ID of the product (book) being
     *                  read.
     * @param option    The option selected by the user, which can be 'a', 'A', 's',
     *                  'S', 'B', or 'b'.
     * @param userID    The ID of the user who is starting a reading session.
     * @return The method is returning a String that represents the current reading
     *         session of a user for a
     *         specific book. The String includes information about the book being
     *         read, the current page being
     *         read, and options for the user to navigate through the book or go
     *         back to the library.
     */
    public String startAReagindSession(String productID, char option, String userID) {
        String reading = "";
        AbstractUser user = users.get(userID.toLowerCase());
        BibliographicProduct userProduct = searchProduct(userID, productID);
        int currentPage = 0;

        if (userProduct != null) {
            switch (Character.toUpperCase(option)) {
                case 'A':
                    currentPage = userProduct.previousPage();
                    break;
                case 'S':
                    currentPage = userProduct.nextPage();
                    break;
                case 'B':
                    BibliographicProduct product = products.get(findProductByID(productID));
                    product.setNumberOfPagesRead(product.getNumberOfPagesRead() + userProduct.getNumberOfPagesRead());
                    break;
                default:

            }

            reading = "\n\tReading session in progress...\n" +
                    "\nReading: " + userProduct.getName() +
                    "\nReagind page # " + userProduct.getCurrentPageBeingRead() + " of "
                    + userProduct.getNumberOfPages() +
                    "\n\nPress 'A' to go to the prevoius page" +
                    "\nPress 'S' to go to the next page" +
                    "\nPress 'B' to go back to the library" + "\n\n" +
                    showPublicity(currentPage, user);
        } else {
            reading = "The user does not have this product";
        }
        return reading;

    }

    public String startMatrixReadingSession(String option, String userID) {
        AbstractUser user = users.get(userID.toLowerCase());
        if (user == null) {
            return "User not found";
        }
        String readingMatrix = user.getName() + "' library\n";
        int currentPage = user.getLibrary().getCurrentPage();

        switch (option.toUpperCase()) {
            case "A":
                currentPage = user.getLibrary().previousPage();
                break;
            case "S":
                currentPage = user.getLibrary().nextPage();
                break;
            case "E":
                break;
            default:
        }

        readingMatrix += user.getLibrary().showMatrix(currentPage);
        readingMatrix += "\nINSERT THE Y/X CORDINATE OR THE PRODUC'S CODE TO START A READING SESION" +
                "\nPress A to go the previous page" +
                "\nPress S to go to the next page" +
                "\nPress E to exit";

        return readingMatrix;
    }

    public BibliographicProduct searchProduct(String userID, String word) {
        AbstractUser user = users.get(userID.toLowerCase());
        if (word.contains("/")) {
            int x = Character.getNumericValue(word.charAt(0));
            int y = Character.getNumericValue(word.charAt(2));
            return user.getLibrary().getProductByCordinate(x, y);
        } else {
            return user.getProductByID(word.toLowerCase());
        }
    }

    /**
     * The function returns a random advertisement message based on the current page
     * and if the user is Standar.
     * 
     * @param currentPage an integer representing the current page being viewed by
     *                    the user
     * @param user        An instance of the AbstractUser class, which is a parent
     *                    class for different types of
     *                    users in a system. The specific type of user is not
     *                    specified in this method.
     * @return The method returns a string that represents a random advertisement
     *         message from an array of
     *         three possible messages. The message is only returned if the current
     *         page number is a multiple of 5
     *         and the user is an instance of the Standard class. If these
     *         conditions are not met, an empty string
     *         is returned.
     */
    public String showPublicity(int currentPage, AbstractUser user) {
        Random rand = new Random();
        String[] adds = { "Subscribe to Combo Plus and get Disney+ and Star+ at an incredible price!",
                "Now your pets have a favorite app: Laika. The best products for your furry.",
                "We are celebrating our anniversary! Visit your nearest Éxito and be surprised with the best offers." };
        String add = "";
        if (currentPage % 5 == 0 && (user instanceof Standar)) {
            add = adds[rand.nextInt((2 - 1) + 1) + 1];
        }
        return add;
    }

}
