package Tests.modelTests;

import model.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

class UserTests {
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
    void validateHashMapHoldsAlotOfUsers() {

        for (int i = 0; i < 500; i++) {
            controller.addUser(controller.createUser(("A0" + i), ("user#" + i), 1));
        }
        for (int j = 500; j < 1000; j++) {
            controller.addUser(controller.createUser(("A0" + j), ("user#" + j), 2));
        }
        Assertions.assertTrue(controller.getUsers().size() == 1000);
    }

}
