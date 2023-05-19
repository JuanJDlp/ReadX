package ui;

import model.*;

import java.util.Calendar;

import Factories.*;
import Interfaces.IAbstractBibliograpgicProductFractory;
import MyHashMap.MyHashMap;

public class initProgram {
    /**
     * The function initializes the model by calling three other functions to
     * initialize users, products,
     * and products of a user.
     * 
     * @param driver The parameter "driver" is an instance of the Controller class,
     *               which is likely
     *               responsible for managing the overall functionality of the
     *               program or application. It is being passed
     *               as an argument to the initModel() method, which is likely
     *               responsible for initializing various
     *               components of the program's data model.
     */
    public static void initModel(Controller driver) {
        initUsers(driver);
        initProducts(driver);
        initProductsOfAnUser(driver);
    }

    /**
     * The function initializes users by creating and adding them to a controller
     * object, with different
     * user IDs and access levels.
     * 
     * @param driver It is an instance of the Controller class, which is being
     *               passed as a parameter to the
     *               initUsers() method. The Controller class is likely a class that
     *               manages the overall functionality of
     *               the program or system being developed.
     */
    public static void initUsers(Controller driver) {
        for (int i = 0; i < 10; i++) {
            driver.addUser(driver.createUser(("A0" + i), ("user#" + i), 1));
        }
        for (int j = 5; j < 10; j++) {
            driver.addUser(driver.createUser(("A0" + j), ("user#" + j), 2));
        }
    }

    /**
     * The function initializes a list of 1000 books and 100 magazines using their
     * respective factories and
     * adds them to a controller object.
     * 
     * @param driver The controller object that manages the products.
     */
    public static void initProducts(Controller driver) {
        IAbstractBibliograpgicProductFractory bookFactory = new BookFactory();
        IAbstractBibliograpgicProductFractory magazineFactory = new MagazineFactory();
        for (int i = 0; i < 2000; i++) {
            driver.getProducts().add(bookFactory.createProduct("Book#" + i, i, Calendar.getInstance(),
                    "hhtp://www.book# [ " + i + " ].com", i, "book#" + i + " talks about a book!", 1));
        }

        for (int i = 0; i < 2000; i++) {
            driver.getProducts().add(magazineFactory.createProduct("Magazine#" + i, i, Calendar.getInstance(),
                    "hhtp://www.book# [ " + i + " ].com", i, "Muensualy", 1));
        }
    }

    /**
     * The function initializes products for each user in a controller object by
     * adding four products from
     * a list of products.
     * 
     * @param driver The "driver" parameter is an instance of the "Controller"
     *               class, which is likely a
     *               class that manages the overall functionality of a program or
     *               system.
     */

    public static void initProductsOfAnUser(Controller driver) {
        int h = 0;
        for (MyHashMap.Node<String, AbstractUser> entry : driver.getUsers()) {
            for (int i = 0; i < 10; i++) {
                entry.getValue().addProduct(driver.getProducts().get(i + h));
            }
            h = h + 10;
        }
    }
}
