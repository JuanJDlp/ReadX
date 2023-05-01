package ui;

import model.*;

public class initProgram {
    public static void initModel(Controller driver) {
        for (int i = 0; i < 5; i++) {
            driver.addUser(("A0" + i), ("user#" + i), 1);
            for (int j = i; i < 5; i++) {
                driver.addUser(("A0" + j), ("user#" + j), 2);
            }
            ;
        }
        ;

    }
}
