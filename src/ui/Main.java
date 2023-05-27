package ui;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Scanner;

import model.Controller;
import Utils.Utils;

/**
 * This class is encharged of displaying all the UI designm
 * 
 * @author Juan Jose De La Pava
 */
public class Main {
    private Scanner input;
    private Controller driver;

    // Constructor
    public Main() {
        input = new Scanner(System.in);
        driver = new Controller();
        initProgram.initModel(driver); // This should be commented if you want to test the first requieremtn.
    }

    public static void main(String[] args) {
        Main view = new Main();
        int option = view.menu();
        while (option != 9) {
            view.executeOption(option);
            option = view.menu();
        }
        System.out.println("\nEXITING...");
        view.input.close();
    }

    /**
     * This option is the one encharged of running the option inserted by the user
     * in the Menu
     * 
     * @param option -> It will be a number between 1 and 6 representing what the
     *               user want's to do with the software.
     */
    public void executeOption(int option) {
        switch (option) {

            case 1:
                registerUser();
                break;
            case 2:
                registerBibliographicProduct();
                break;

            case 3:
                if (driver.areTherProducts()) {
                    deleteProduct();
                } else {
                    System.out.println("It wasn't possible to find any products in the data base");
                }
                break;

            case 4:
                if (driver.areThereUsers()) {
                    changeProduct();
                } else {
                    System.out.println("It wasn't possible to find any users in the data base.");
                }
                break;
            case 5:
                if (driver.areTherProducts()) {
                    obtainProduct();
                } else {
                    System.out.println("It wasn't possible to find any products in the data base");
                }
                break;
            case 6:
                if (driver.areTherProducts()) {
                    unsubscribeMagazine();
                } else {
                    System.out.println("It wasn't possible to find any products in the data base");
                }
                break;
            case 7:
                if (driver.areTherProducts()) {
                    startAReadingMatrix();
                } else {
                    System.out.println("It wasn't possible to find any products in the data base");
                }
                break;
            case 8:
                reportMenu();
                break;
            default:
                System.out.println("Option not recognized.");
                break;
        }

    }

    /**
     * 
     * Shows a menu with options related to project and capsule management, and
     * information retrieval.
     * 
     * @return the selected option by the user that will be later used in the method
     *         executeOption
     */
    public int menu() {
        int option = 0;
        do {
            System.out.println(
                    "<< --------------------------------------------------------------------- >>\n" +
                            "<< -                                Welcome                            - >>\n" +
                            "<< --------------------------------------------------------------------- >>\n" +
                            "1. Register user \n" +
                            "2. Add a book or a magazine\n" +
                            "3. Delete book or magazine \n" +
                            "4. Modify a book or a magazine\n" +
                            "5. Purchase product\n" +
                            "6. Unsubscribe from a magazine\n" +
                            "7. Open a user's library\n" +
                            "8. Create a report\n" +

                            "\n9. EXIT.\n");

            System.out.print("\n>> ");
            option = validateIntegerInput();
            if (option < 1 || option > 9) {
                System.out.println("Please select a valid option \n");
            }
        } while (option < 1 || option > 9);
        return option;
    }

    /**
     * 
     * Validates the input from the user to make sure it is an integer.
     * If what the user is not an integer input it will display a message and the
     * option will be -1
     * 
     * @return an integer input from the user
     */
    public int validateIntegerInput() {
        int option = 0;
        if (input.hasNextInt()) {
            option = input.nextInt();
            input.nextLine();
        } else {
            input.nextLine();// clean the Scanner
            option = -1;
            System.out.println("Insert a numeric value.");
        }
        return option;
    }

    /**
     * 
     * Validates the input from the user to make sure it is a double.
     * If what the user is not a daouble input it will display a message and the
     * option will be -1
     * 
     * @return a double input from the user
     */
    public double validateDoubleInput() {
        double option = 0;
        if (input.hasNextDouble()) {
            option = input.nextDouble();
            input.nextLine();
        } else {
            input.nextLine();// clean the Scanner
            option = -1;
            System.out.println("Insert a numeric value.");
        }
        return option;
    }

    /**
     * This function registers a user by taking their ID, name, and type of user as
     * input and sends them to the driver to be created and stored
     */
    public void registerUser() {
        String ID;
        String name;
        int typeOfUser;
        System.out.println("Please insert the user's ID: ");
        ID = input.nextLine();
        System.out.println("Please insert the users's name:  ");
        name = input.nextLine();
        do {

            System.out.println("What type is the user?" + "\n" + "1. Standar" + "\n" + "2. Premium");
            typeOfUser = validateIntegerInput();
        } while (typeOfUser < 1 || typeOfUser > 2);

        if (driver.addUser(driver.createUser(name, ID, typeOfUser))) {
            System.out.println("The user was added succesfully");
            System.out.println("\tUser's information: " + "\n" + driver.showUserInfo(ID));
        } else {
            System.out.println("There was an error adding the user!");
        }
    }

    /**
     * This function allows a user to register a bibliographic product by inputting
     * its name, number of
     * pages, publication date, and type (book or magazine). The it uses 2 helper
     * functions createBook and createMagazine
     */
    public void registerBibliographicProduct() {
        int bookOrMagazine;
        String productName;
        int numberOfPages;
        Calendar publicationDate;
        do {

            System.out.println("\n\tWould you like to: " +
                    "\n1.Register a book" +
                    "\n2.Register a magazine");
            bookOrMagazine = validateIntegerInput();
        } while (bookOrMagazine < 1 || bookOrMagazine > 2);

        System.out.println("Please insert the product's name: ");
        productName = input.nextLine();

        do {

            System.out.println("Please insert the number of pages: ");
            numberOfPages = validateIntegerInput();
        } while (numberOfPages < 0);

        while (true) {
            try {
                System.out.println("Please insert the publication date (dd/MM/yyyy):");
                publicationDate = Utils.stringToCalendar(input.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("\tERROR please inserte the right format (dd/MM/yyyy)");
            }
        }
        switch (bookOrMagazine) {
            case 1:
                createBook(productName, numberOfPages, publicationDate, bookOrMagazine);
                break;
            case 2:
                craeteMagazine(productName, numberOfPages, publicationDate, bookOrMagazine);
                break;
            default:
                System.out.println("Option not available");
        }

        System.out.println(driver.showProductInfoByName(productName));

    }

    /**
     * This function creates a book or magazine product by taking user inputs for
     * various attributes such
     * as URL, value, review, and genre.
     * 
     * @param productName     A string representing the name of the book.
     * @param numberOfPages   An integer representing the number of pages in the
     *                        book.
     * @param publicationDate A Calendar object representing the date of publication
     *                        of the book.
     * @param bookOrMagazine  This parameter is an integer that specifies whether
     *                        the product being created
     *                        is a book or a magazine. A value of 1 indicates a
     *                        book, while a value of 2 indicates a magazine.
     * @return The method is returning a String, which represents if it was possible
     *         or not to create the product
     */
    private String createBook(String productName, int numberOfPages,
            Calendar publicationDate,
            int bookOrMagazine) {
        String URL;
        double value;
        String review;
        int genre;

        System.out.println("Please insert the URL leading to the repository: ");
        URL = input.nextLine();

        do {
            System.out.println("Please insert the value of retail: ");
            value = validateDoubleInput();
        } while (value < 0);

        System.out.println("Please insert the review of the book: ");
        review = input.nextLine();

        do {
            System.out.println(
                    "Please select the genre of the book: " + "\n" + "1.Science fiction" + "\n" + "2.Fantasy" + "\n"
                            + "3.Historical novel");
            genre = validateIntegerInput();
        } while (genre < 1 || genre > 3);

        return driver
                .addProduct(driver.createBibliographicProduct(productName, numberOfPages, publicationDate, URL, value,
                        review, genre, bookOrMagazine));
    }

    /**
     * This function creates a magazine by prompting the user for various
     * inputs such as the magazine
     * cover URL, subscription value, frequency of issuance, and category.
     * 
     * @param productName     A String representing the name of the magazine being
     *                        created.
     * @param numberOfPages   The number of pages in the magazine.
     * @param publicationDate A Calendar object representing the date of publication
     *                        of the magazine.
     * @param bookOrMagazine  An integer value indicating whether the product being
     *                        created is a book or a
     *                        magazine. A value of 1 indicates a book, while a value
     *                        of 2 indicates a magazine.
     * @return The method is returning a String, which represents the output of the
     *         opperation
     */
    private String craeteMagazine(String productName, int numberOfPages,
            Calendar publicationDate,
            int bookOrMagazine) {
        String URL;
        double value;
        String frecuencyOfIssuance;
        int category;

        System.out.println("Please insert the URL leading to the magazine cover: ");
        URL = input.nextLine();
        do {
            System.out.println("Please insert the value of subscription: ");
            value = validateDoubleInput();
        } while (value < 0);

        System.out.println("Plase insert the frecuency of issuance: (EX. Monthly,weekly,yearly)");
        frecuencyOfIssuance = input.nextLine();
        do {

            System.out.println(
                    "Please select the category of the magazine: " + "\n" + "1.Varieties" + "\n" + "2.Design" + "\n"
                            + "3.Scientific");
            category = validateIntegerInput();
        } while (category < 1 || category > 3);
        return driver
                .addProduct(driver.createBibliographicProduct(productName, numberOfPages, publicationDate, URL, value,
                        frecuencyOfIssuance, category, bookOrMagazine));
    }

    /**
     * This Java prompts the user to input a product name and ID, displays
     * similar product names,
     * and deletes the product with the specified ID.
     */
    public void deleteProduct() {
        String productsName;
        String productID;
        System.out.println("Please insert the Product name");
        productsName = input.nextLine();
        System.out.println(
                "\n\tThese are product with similar names: \n" + driver.showProductsWithSimilarName(productsName));
        System.out.println("\nPlease insert the ID of the product you want to delete.");
        productID = input.nextLine();
        System.out.println(driver.deleteProduct(productID));
    }

    /**
     * This function allows the user to change the attributes of a product by
     * selecting the product and the
     * attribute to be changed, and then entering a new value for that attribute.
     */
    public void changeProduct() {
        String productsName;
        String productID;
        int option;
        String newValue = "00/00/0000";
        String msg = "";

        System.out.println("Please insert the product name");
        productsName = input.nextLine();

        System.out.println(
                "\n\tThese are product with similar names: \n" + driver.showProductsWithSimilarName(productsName));

        System.out.println("\nPlease insert the ID of the product you want to modify: ");
        productID = input.nextLine();
        do {

            System.out.println("\nWhat would you look to change:  " + driver.showProductLabeledAttributes(productID));
            option = validateIntegerInput();
        } while (option < 1 || option > 7);

        System.out.println(driver.displayInformationByProduct(productID, option));

        System.out.print("What would you like to change it to: \n>>");
        newValue = input.nextLine();

        msg = driver.changeProduct(productID, option, newValue);
        System.out.println(msg);
    }

    /**
     * This function allows a user to purchase products by inputting the user ID and
     * the
     * product ID, and can add. The user has the possibility to buy multiple
     * products at once.
     */
    public void obtainProduct() {
        String userID;
        String productID;
        String productName;
        char addAnotherProduct = ' ';
        System.out.println("Please insert the ID of the user");
        userID = input.nextLine();

        do {
            System.out.println("Please insert the product name");
            productName = input.nextLine();

            System.out.println(
                    "\n\tThese are product with similar names: \n" + driver.showProductsWithSimilarName(productName));

            System.out.println("\nPlease insert the ID of the product you want to obtain: ");
            productID = input.nextLine();

            System.out.println(driver.addProductToUser(userID, productID));

            System.out.println("Would you like to add another product? (Y/n)");
            try {
                addAnotherProduct = input.nextLine().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                addAnotherProduct = 'y';
            }

        } while (addAnotherProduct == 'y' || addAnotherProduct == 'Y');
        System.out.println(driver.checkOutShoppingCart(userID));

    }

    /**
     * This function allows a user to unsubscribe from a magazine by inputting their
     * user ID and the ID of
     * the magazine they want to unsubscribe from.
     */

    public void unsubscribeMagazine() {
        String userID;
        String productID;
        String productName;
        System.out.println("Please insert the ID of the user");
        userID = input.nextLine();
        System.out.println("Please insert the product name");
        productName = input.nextLine();

        System.out.println(
                "\n\tThese are product with similar names: \n" + driver.showProductsWithSimilarName(productName));

        System.out.println("\nPlease insert the ID of the magazine you want to unsibscribe from: ");
        productID = input.nextLine();
        System.out.println(driver.unsubscribeMagazine(userID, productID));

    }

    /**
     * This function starts a reading session for a user by prompting for their ID
     * and a product ID, and
     * then allowing them to interact with the product through a series of options
     * until they choose to
     * exit.
     */
    public void startAReadingSesion(String userID, String selection) {
        char option = '.';
        do {
            try {
                System.out.println(driver.startAReagindSession(selection, option, userID));
                option = input.nextLine().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please insert an option");
            }

        } while (option != 'B' && option != 'b');

    }

    /**
     * This function starts a reading matrix session for a user with a given user
     * ID.
     */
    public void startAReadingMatrix() {
        String userID;
        String option = "....";
        System.out.println("Please insert the user ID");
        userID = input.nextLine();
        if (driver.getUsers().get(userID.toLowerCase()) != null) {
            try {
                do {
                    System.out.println(driver.startMatrixReadingSession(option, userID));
                    option = input.nextLine();
                    if (isTheUserTryingToStartAreadingSession(option, userID)) {
                        startAReadingSesion(userID, option);
                        option = "...";
                    }

                } while (option.charAt(0) != 'e' && option.charAt(0) != 'E');
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please insert an option");
            }
        } else {
            System.out.println("User not found");
        }
    }

    /**
     * The function checks if the user is trying to start a reading session by
     * searching for a product with
     * the given option and userID. And if it finds any it will start a readin
     * session
     * 
     * @param option The option parameter is a String that represents the type of
     *               reading session the user
     *               is trying to start. It could be something like "fiction",
     *               "non-fiction", "mystery", etc.
     * @param userID The userID parameter is a String that represents the unique
     *               identifier of a user.
     * @return A boolean value is being returned.
     */

    public boolean isTheUserTryingToStartAreadingSession(String option, String userID) {
        return driver.searchProduct(userID, option) != null;
    }

    /**
     * This function displays a menu of report options and executes the selected
     * report.
     */
    public void reportMenu() {
        int option = -1;
        do {
            System.out.println("What type of report would you like to create?\n" +
                    "\n" + "1. Consult total pages read by every type of product" +
                    "\n" + "2. Genre and Category with the most pages read" +
                    "\n" + "3. Top 5 books and Top 5 magazines read" +
                    "\n" + "4. Book with the most amount of sellings per genre" +
                    "\n" + "5. Inform subscriptions active and total paid per category" +
                    "\n" + "\n0. Return to main menu");
            option = validateIntegerInput();
        } while ((option < 0 || option > 5) && option != 0);
        executeReport(option);
    }

    /**
     * This function executes a report based on the option selected by calling
     * different methods.
     * 
     * @param option an integer representing the user's choice of report to execute.
     *               The method uses a
     *               switch statement to call different report methods based on the
     *               value of this parameter.
     */
    public void executeReport(int option) {
        switch (option) {
            case 1:
                amountOfPagesByProduct();
                break;
            case 2:
                genreAndCategoryWithTheMostAmountOfPagesRead();
                break;
            case 3:
                top5BooksAndTop5MagazinesRead();
                break;
            case 4:
                salesByGenre();
                break;
            case 5:
                salesByCategory();
                break;
            case 0:
                break;
            default:
                System.out.println("Option not recognized");

        }
    }

    /**
     * This function prints the amount of pages for a specific product.
     */
    public void amountOfPagesByProduct() {
        System.out.println(driver.amountOfPagesByProduct());
    }

    /**
     * This function prints the genre and category with the most amount of pages
     * read.
     */
    public void genreAndCategoryWithTheMostAmountOfPagesRead() {
        System.out.println(driver.genreAndCategoryWithTheMostAmountOfPagesRead());
    }

    /**
     * This function prints the top 5 books and top 5 magazines read using the
     * "driver" object.
     */
    public void top5BooksAndTop5MagazinesRead() {
        System.out.println("\tTop 5 books: \n" + driver.top5books() +
                "\n\tTop 5 magazines: \n" + driver.top5magazines());
    }

    /**
     * This function prints out the sales by genre using a driver object.
     */

    public void salesByGenre() {
        System.out.println(driver.salesByGenre());
    }

    /**
     * This function prints the sales by category using a driver object.
     */
    public void salesByCategory() {
        System.out.println(driver.salesByCategory());
    }
}
