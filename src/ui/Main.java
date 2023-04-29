package ui;

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

                break;

            case 2:

                break;

            case 3:
                break;

            case 4:

                break;

            case 5:

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
                            "2. \n" +
                            "3. \n" +
                            "4. \n" +
                            "5. \n" +
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
        System.out.println("Please insert the user's ID: ");
        name = input.nextLine();
        System.out.println("What type is the user?" + "\n" + "1. Standar" + "\n" + "2. Premium");
        typeOfUser = input.nextInt();
        driver.createUser(name, ID, typeOfUser);
    }

}