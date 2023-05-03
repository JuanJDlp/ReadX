package ui;

import model.*;

public class initProgram {
    public static void initModel(Controller driver) {
        for (int i = 0; i < 5; i++) {
            driver.addUser(driver.createUser(("A0" + i), ("user#" + i), 1));
        }
        for (int j = 5; j < 10; j++) {
            driver.addUser(driver.createUser(("A0" + j), ("user#" + j), 2));
        }

    }
}
