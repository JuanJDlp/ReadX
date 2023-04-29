package model;

import model.BibliographicProduct;
import Factories.UserFactory;
import java.util.HashMap;

public class Controller {
    private HashMap<String, AbstractUser> users;

    public Controller() {
        users = new HashMap<>();
    }

    public AbstractUser createUser(String name, String ID, int typeOfUser) {
        UserFactory userFactory = new UserFactory();
        return userFactory.createUser(ID, name, typeOfUser);
    }

    public boolean addUser(AbstractUser user) {
        users.put(user.getID(), user);
        return true;
    }
}
