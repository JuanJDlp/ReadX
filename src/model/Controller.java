package model;

import model.BibliographicProduct;
import Factories.UserFactory;
import java.util.HashMap;

public class Controller {
    private HashMap<String, AbstractUser> users;

    public Controller() {
        users = new HashMap<>();
    }

    public HashMap<String, AbstractUser> getUsers() {
        return users;
    }

    public String createUser(String name, String ID, int typeOfUser) {
        UserFactory userFactory = new UserFactory();
        addUser(userFactory.createUser(ID, name, typeOfUser));
        return "User created";
    }

    public void addUser(AbstractUser user) {
        users.put(user.getID(), user);
    }
}
