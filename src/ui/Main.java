package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import model.Controller;

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
                if (driver.areThereUsers()) {
                    registerBibliographicProduct();
                } else {
                    System.out.println("It wasn't possible to find any users in the data base.");
                }
                break;

            case 3:
                if (driver.areThereUsers()) {
                    deleteProduct();
                } else {
                    System.out.println("It wasn't possible to find any users in the data base.");
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
                if (driver.areThereUsers()) {
                    obtainProduct();
                } else {
                    System.out.println("It wasn't possible to find any users in the data base.");
                }
                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

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
                            "4. Change a book or a magazine\n" +
                            "5. Sync a product and a user\n" +
                            "6. \n" +
                            "7. \n" +
                            "8. \n" +

                            "9. EXIT.\n");

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

    public void registerBibliographicProduct() {
        int bookOrMagazine;
        String productName;
        int numberOfPages;
        Calendar publicationDate;
        do {

            System.out.println("\n\tWould you like to: " +
                    "\n1.Buy a book" +
                    "\n2.Subscribe to a magazine");
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
                publicationDate = stringToCalendar(input.nextLine());
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

    public void obtainProduct() {
        String userID;
        String productID;
        String productName;
        System.out.println("Please insert the ID of the user");
        userID = input.nextLine();
        System.out.println("Please insert the product name");
        productName = input.nextLine();

        System.out.println(
                "\n\tThese are product with similar names: \n" + driver.showProductsWithSimilarName(productName));

        System.out.println("\nPlease insert the ID of the product you want to obtain: ");
        productID = input.nextLine();

        System.out.println(driver.addProductToUser(userID, productID));
    }

    private Calendar stringToCalendar(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(dateString));
        return calendar;
    }
}
