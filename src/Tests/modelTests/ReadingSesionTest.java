package Tests.modelTests;

import model.*;
import model.Enumerations.*;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import org.junit.jupiter.api.Assertions;

public class ReadingSesionTest {

    @Test
    void eachTimeAPageIsReadTheProductCountShouldBeUpdated() {
        Controller driver = new Controller();

        BibliographicProduct magazine = new Magazine("News on java", 1, Calendar.getInstance(), "https", 9,
                "Mensually",
                Category.DESIGN);

        driver.addProduct(magazine);

        AbstractUser user = driver.createUser("XXXXXXXX", "JD123", 1);
        driver.addUser(user);

        driver.addProductToUser(user.getID(), magazine.getID());

        Assertions.assertEquals(0, magazine.getNumberOfPagesRead());

        driver.startAReagindSession(magazine.getID(), 's', user.getID());
        driver.startAReagindSession(magazine.getID(), 'B', user.getID());

        Assertions.assertEquals(1, magazine.getNumberOfPagesRead());

    }

    @Test
    void whenReadingAlotOfPagesOnlyThehightNumberShouldbekept() {
        // Arrange
        Controller driver = new Controller();

        BibliographicProduct magazine = new Magazine("News on java", 100, Calendar.getInstance(), "https", 9,
                "Mensually",
                Category.DESIGN);

        driver.addProduct(magazine);

        AbstractUser user = driver.createUser("XXXXXXXX", "JD123", 1);
        driver.addUser(user);

        driver.addProductToUser(user.getID(), magazine.getID());

        // Act

        for (int i = 0; i < 88; i++) {
            driver.startAReagindSession(magazine.getID(), 's', user.getID());
        }

        driver.startAReagindSession(magazine.getID(), 'B', user.getID());

        // Assert

        Assertions.assertEquals(88, magazine.getNumberOfPagesRead());

    }

    @Test
    void whenAddingAndRemovingPagesThePagesReadshouldBeTheMaximumPageRead() {
        // Arrange
        Controller driver = new Controller();

        BibliographicProduct magazine = new Magazine("News on java", 100, Calendar.getInstance(), "https", 9,
                "Mensually",
                Category.DESIGN);

        driver.addProduct(magazine);

        AbstractUser user = driver.createUser("XXXXXXXX", "JD123", 1);
        driver.addUser(user);

        driver.addProductToUser(user.getID(), magazine.getID());

        for (int i = 0; i < 88; i++) {
            driver.startAReagindSession(magazine.getID(), 's', user.getID());
        }

        for (int i = 0; i < 100; i++) {
            driver.startAReagindSession(magazine.getID(), 'a', user.getID());
        }

        for (int i = 0; i < 80; i++) {
            driver.startAReagindSession(magazine.getID(), 's', user.getID());
        }

        driver.startAReagindSession(magazine.getID(), 'B', user.getID());

        Assertions.assertEquals(88, magazine.getNumberOfPagesRead());

    }

    @Test
    void whenTwoUsersReadTheSameProductThePagesShouldBeAdded() {
        Controller driver = new Controller();

        BibliographicProduct magazine = new Magazine("News on java", 100, Calendar.getInstance(), "https", 9,
                "Mensually",
                Category.DESIGN);

        driver.addProduct(magazine);

        AbstractUser user = driver.createUser("XXXXXXXX", "JD123", 1);
        driver.addUser(user);

        AbstractUser user1 = driver.createUser("XXXXXXXX", "OWO", 2);
        driver.addUser(user1);

        driver.addProductToUser(user.getID(), magazine.getID());
        driver.addProductToUser(user1.getID(), magazine.getID());

        for (int i = 0; i < 80; i++) {
            driver.startAReagindSession(magazine.getID(), 's', user.getID());
        }

        driver.startAReagindSession(magazine.getID(), 'B', user.getID());

        for (int i = 0; i < 5; i++) {
            driver.startAReagindSession(magazine.getID(), 's', user1.getID());
        }

        driver.startAReagindSession(magazine.getID(), 'B', user1.getID());

        Assertions.assertEquals(85, magazine.getNumberOfPagesRead());

    }

}
