package model;

import Factories.UserFactory;
import MyHashMap.MyHashMap;

public class Controller {
    private MyHashMap<String, AbstractUser> users;

    public Controller() {
        users = new MyHashMap<>();
    }

    public MyHashMap<String, AbstractUser> getUsers() {
        return users;
    }

    public boolean areThereUsers() {
        return !users.isEmpty();
    }

    public AbstractUser createUser(String name, String ID, int typeOfUser) {
        UserFactory userFactory = new UserFactory();
        AbstractUser user = userFactory.createUser(ID, name, typeOfUser);
        return user;
    }

    public boolean addUser(String name, String ID, int typeOfUser) {
        boolean wasAdded = false;
        AbstractUser user = createUser(name, ID, typeOfUser);
        users.put(user.getID(), user);
        if (users.containsKey(user.getID())) {
            wasAdded = true;
        }
        return wasAdded;
    }

    public String showUserInfo(String ID) {
        return users.get(ID).toString();
    }

}
