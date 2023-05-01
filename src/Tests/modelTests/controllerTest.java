package Tests.modelTests;

import model.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

class controllerTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @Test
    void validateUserWasAdded() {
        // Arrange
        String name = "John Doe";
        String ID = "JD123";
        int typeOfUser = 1;
        // Act
        controller.createUser(name, ID, typeOfUser);

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
}
