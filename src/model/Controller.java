package model;

import java.util.Calendar;

import Factories.*;
import MyHashMap.MyHashMap;

public class Controller {
    private MyHashMap<String, AbstractUser> users;
    private MyHashMap<String, BibliographicProduct> products;

    public Controller() {
        users = new MyHashMap<>();
        products = new MyHashMap<>();
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

    public boolean addUser(AbstractUser user) {
        boolean wasAdded = false;
        users.put(user.getID(), user);
        if (users.containsKey(user.getID())) {
            wasAdded = true;
        }
        return wasAdded;
    }

    public String showUserInfo(String ID) {
        return users.get(ID).toString();
    }

    public BibliographicProduct createBibliographicProduct(
            String name,
            int numberOfPages,
            Calendar pubicationDate,
            String URL,
            double value,
            String extraArgument,
            int category_genre,
            int typeOfProduct) {

        BibliographicProduct product;

        switch (typeOfProduct) {
            case 1:
                BookFactory bookFactory = new BookFactory();
                product = bookFactory.createProduct(name, numberOfPages, pubicationDate, URL, value, extraArgument,
                        category_genre);
                break;
            case 2:
                MagazineFactory magazineFactory = new MagazineFactory();
                product = magazineFactory.createProduct(name, numberOfPages, pubicationDate, URL, value, extraArgument,
                        category_genre);
                break;
            default:
                product = null;

        }

        return product;

    }

    public boolean addProduct(BibliographicProduct product) {
        boolean wasAdded = false;
        products.put(product.getName(), product);
        if (products.containsKey(product.getID())) {
            wasAdded = true;
        }
        return wasAdded;

    }

    public String showProductInfo(String name) {
        return products.get(name).toString();
    }
}
