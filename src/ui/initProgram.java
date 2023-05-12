package ui;

import model.*;

import java.util.Calendar;

import Factories.*;
import Interfaces.IAbstractBibliograpgicProductFractory;
import MyHashMap.MyHashMap;

public class initProgram {
    public static void initModel(Controller driver) {
        initUsers(driver);
        initProducts(driver);
        initProductsOfAnUser(driver);
    }

    public static void initUsers(Controller driver) {
        for (int i = 0; i < 10; i++) {
            driver.addUser(driver.createUser(("A0" + i), ("user#" + i), 1));
        }
        for (int j = 5; j < 10; j++) {
            driver.addUser(driver.createUser(("A0" + j), ("user#" + j), 2));
        }
    }

    public static void initProducts(Controller driver) {
        IAbstractBibliograpgicProductFractory bookFactory = new BookFactory();
        IAbstractBibliograpgicProductFractory magazineFactory = new MagazineFactory();
        for (int i = 0; i < 1000; i++) {
            driver.getProducts().add(bookFactory.createProduct("Book#" + i, i, Calendar.getInstance(),
                    "hhtp://www.book# [ " + i + " ].com", i, "book#" + i + " talks about a book!", 1));
        }

        for (int i = 0; i < 100; i++) {
            driver.getProducts().add(magazineFactory.createProduct("Magazine#" + i, i, Calendar.getInstance(),
                    "hhtp://www.book# [ " + i + " ].com", i, "Muensualy", 1));
        }
    }

    public static void initProductsOfAnUser(Controller driver) {
        int h = 0;
        for (MyHashMap.Node<String, AbstractUser> entry : driver.getUsers()) {
            for (int i = 0; i < 4; i++) {
                entry.getValue().addProduct(driver.getProducts().get(i + h));
            }
            h = h + 5;
        }
    }
}
