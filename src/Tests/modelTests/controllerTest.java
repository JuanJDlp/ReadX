package Tests.modelTests;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import model.AbstractUser;
import model.Controller;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

class controllerTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @Test
    public void testCreateUser() {
        String name = "John";
        String ID = "123";
        int typeOfUser = 1;
        String expectedResult = "User created";

        String result = controller.createUser(name, ID, typeOfUser);

        assertEquals(expectedResult, result);
        assertEquals(1, controller.getUsers().size());
    }
}
