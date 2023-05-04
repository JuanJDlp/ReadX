package model;

import java.util.ArrayList;
import java.util.Calendar;

import Factories.*;
import MyHashMap.MyHashMap;

public class Controller {
    private MyHashMap<String, AbstractUser> users;
    private ArrayList<BibliographicProduct> products;

    public Controller() {
        users = new MyHashMap<>();
        products = new ArrayList<>();
    }

    public MyHashMap<String, AbstractUser> getUsers() {
        return users;
    }

    public ArrayList<BibliographicProduct> getProducts() {
        return products;
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

    public String addProduct(BibliographicProduct product) {
        products.add(product);
        return product.getID();
    }

    public String showProductInfoById(String ID) {
        String info = "This item does not exist";
        boolean found = false;
        for (int i = 0; i < products.size() && !found; i++)
            if (products.get(i).getID().equals(ID)) {
                found = true;
                info = products.get(i).toString();
            }
        return info;
    }

    public String showProductInfoByName(String name) {
        String info = "This item does not exist";
        boolean found = false;
        for (int i = 0; i < products.size() && !found; i++)
            if (products.get(i).getName().equals(name)) {
                found = true;
                info = products.get(i).toString();
            }
        return info;
    }

    public void deleteProduct(String ID) {
        boolean found = false;
        int position = -1;
        for (int i = 0; i < products.size() && !found; i++)
            if (products.get(i).getID().equals(ID)) {
                found = true;
                position = i;
            }
        products.remove(position);
    }
}
