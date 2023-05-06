package Tests.modelTests;

import model.*;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

class UserTests {
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @Test
    void validateWhenCreatingAnUserItsNotTNull() {
        // Arrange
        String name = "John Doe";
        String ID = "JD123";
        int typeOfUser = 1;
        // Act
        AbstractUser user = controller.createUser(name, ID, typeOfUser);

        // Assert
        Assertions.assertNotNull(user);
    }

    @Test
    void validateUserWasAdded() {
        // Arrange
        String name = "John Doe";
        String ID = "JD123";
        int typeOfUser = 1;
        // Act
        controller.addUser(controller.createUser(name, ID, typeOfUser));

        // Assert
        Assertions.assertTrue(controller.getUsers().containsKey(ID));
    }

    @Test
    void validateTypeOfUserStandar() {
        // Arrange
        String name = "John Doe";
        String ID = "JD123";
        int typeOfUser = 1;
        // Act
        AbstractUser user = controller.createUser(name, ID, typeOfUser);

        // Assert
        Assertions.assertTrue(user instanceof Standar);
    }

    @Test
    void validateTypeOfUserPremium() {
        // Arrange
        String name = "John Doe";
        String ID = "JD123";
        int typeOfUser = 2;
        // Act
        AbstractUser user = controller.createUser(name, ID, typeOfUser);

        Assertions.assertTrue(user instanceof Premium);
    }

    @Test
    void validateItsNotPossibleToCreateUserWithSameID() {
        // Arrange
        String name = "John Doe";
        String ID = "JD123";
        int typeOfUser = 1;
        // Act
        controller.addUser(controller.createUser(name, ID, typeOfUser));

        // Assert
        Assertions.assertFalse(controller.addUser(controller.createUser(name, ID, typeOfUser)));
    }

    @Test
    void validateCorrectAddingOfABook() {
        Controller driver = new Controller();
        BibliographicProduct book = new Book("Java for dummies", 1, Calendar.getInstance(), "URL", 12343, "Good",
                Book.Genre.FANTASY);
        AbstractUser user = controller.createUser("XXXXXXXX", "JD123", 1);
        driver.addUser(user);
        driver.addProduct(book);
        driver.addProductToUser(user.getID(), book.getID());

        Assertions.assertTrue(user.getProducts().containsKey(book.getID()));
        Assertions.assertTrue(user.getRecipts().size() == 1);
    }

    @Test
    void validateCorrectAddingOfAMagazine() {
        Controller driver = new Controller();
        BibliographicProduct magazine = new Magazine("News on java", 1, Calendar.getInstance(), "https", 9, "Mensually",
                Magazine.Category.DESIGN);
        AbstractUser user = controller.createUser("XXXXXXXX", "JD123", 1);
        driver.addUser(user);
        driver.addProduct(magazine);

        driver.addProductToUser(user.getID(), magazine.getID());

        Assertions.assertTrue(user.getProducts().containsKey(magazine.getID()));
        Assertions.assertTrue(user.getRecipts().size() == 1);
    }

    @Test
    void validateUserHasTheCorretRecipt() {
        Controller driver = new Controller();
        BibliographicProduct magazine = new Magazine("News on java", 1, Calendar.getInstance(), "https", 987654,
                "Mensually",
                Magazine.Category.DESIGN);
        AbstractUser user = controller.createUser("XXXXXXXX", "JD123", 1);

        driver.addUser(user);
        driver.addProduct(magazine);

        driver.addProductToUser(user.getID(), magazine.getID());

        Assertions.assertTrue(user.getRecipts().get(0).getAmount().equals(magazine.getValue()));
    }

    @Test
    void theNumberOfProductsSoldChangesWhenItsAddedTotAnUser() {
        Controller driver = new Controller();
        BibliographicProduct magazine = new Magazine("News on java", 1, Calendar.getInstance(), "https", 987654,
                "Mensually",
                Magazine.Category.DESIGN);
        AbstractUser user = controller.createUser("XXXXXXXX", "JD123", 1);

        driver.addUser(user);
        driver.addProduct(magazine);

        Assertions.assertTrue(magazine.getCopiesSold() == 0);

        driver.addProductToUser(user.getID(), magazine.getID());

        Assertions.assertTrue(magazine.getCopiesSold() == 1);
    }

    @Test
    void theNumberOfProductsSoldChangesWhenItsAddedTotAnUserOfAbook() {
        Controller driver = new Controller();

        BibliographicProduct book = new Book("Java for dummies", 1, Calendar.getInstance(), "URL", 12343, "Good",
                Book.Genre.FANTASY);
        AbstractUser user = controller.createUser("XXXXXXXX", "JD123", 1);
        driver.addUser(user);
        driver.addProduct(book);

        Assertions.assertTrue(book.getCopiesSold() == 0);

        driver.addProductToUser(user.getID(), book.getID());

        Assertions.assertTrue(book.getCopiesSold() == 1);
    }

    @Test
    void theUserCanOnlyHaveOneCopyOfThatElement() {
        Controller driver = new Controller();

        BibliographicProduct book = new Book("Java for dummies", 1, Calendar.getInstance(), "URL", 12343, "Good",
                Book.Genre.FANTASY);
        AbstractUser user = controller.createUser("XXXXXXXX", "JD123", 1);
        driver.addUser(user);
        driver.addProduct(book);

        driver.addProductToUser(user.getID(), book.getID());
        driver.addProductToUser(user.getID(), book.getID());

        Assertions.assertTrue(book.getCopiesSold() == 1);

    }
}
