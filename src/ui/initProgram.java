package ui;

import model.*;

import java.util.Calendar;

import Factories.*;

public class initProgram {
    public static void initModel(Controller driver) {
        for (int i = 0; i < 5; i++) {
            driver.addUser(driver.createUser(("A0" + i), ("user#" + i), 1));
        }
        for (int j = 5; j < 10; j++) {
            driver.addUser(driver.createUser(("A0" + j), ("user#" + j), 2));
        }

        initProducts(driver);
    }

    public static void initProducts(Controller driver) {
        BookFactory bookFactory = new BookFactory();
        MagazineFactory magazineFactory = new MagazineFactory();
        for (int i = 0; i < 100; i++) {
            driver.getProducts().add(bookFactory.createProduct("Book#" + i, i, Calendar.getInstance(),
                    "hhtp://www.book# [ " + i + " ].com", i, "book#" + i + " talks about a book!", 1));
        }

        for (int i = 0; i < 100; i++) {
            driver.getProducts().add(magazineFactory.createProduct("Magazine#" + i, i, Calendar.getInstance(),
                    "hhtp://www.book# [ " + i + " ].com", i, "Muensualy", 1));
        }
    }
}
