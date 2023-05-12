package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import Utils.Utils;
import Factories.BookFactory;
import Factories.MagazineFactory;
import Factories.UserFactory;
import MyHashMap.MyHashMap;
import model.Enumerations.Genre;
import model.Enumerations.Category;

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

    public boolean areTherProducts() {
        return !products.isEmpty();
    }

    public AbstractUser createUser(String name, String ID, int typeOfUser) {
        UserFactory userFactory = new UserFactory();
        AbstractUser user = userFactory.createUser(ID, name, typeOfUser);
        return user;
    }

    public boolean addUser(AbstractUser user) {
        boolean wasAdded = false;
        if (!users.containsKey(user.getID())) {
            users.put(user.getID(), user);
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
        while (findProductByID(product.getID()) != -1) {
            if (product instanceof Book) {
                ((Book) product).reCreateID();
            } else if (product instanceof Magazine) {
                ((Magazine) product).reCreateID();
            }
        }
        products.add(product);
        return product.getID();
    }

    public String showProductInfoById(String ID) {
        String info = "This item does not exist";
        if (findProductByID(ID) != -1) {
            info = products.get(findProductByID(ID)).toString();
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

    public String showProductsWithSimilarName(String productsName) {
        String info = "";
        for (int i = 0; i < products.size(); i++)
            if (products.get(i).getName().toLowerCase().contains(productsName.toLowerCase())) {
                info += products.get(i).getName() + " | " + products.get(i).getID() + "\n";
            }
        return info;
    }

    public String deleteProduct(String ID) {
        String msg = "We could not find this product";
        if (findProductByID(ID) != -1) {
            products.remove(findProductByID(ID));
            for (MyHashMap.Node<String, AbstractUser> entry : users) {
                entry.getValue().removeProduct(ID);
            }
            msg = "Product deleted";
        }
        return msg;
    }

    public int findProductByID(String ID) {
        boolean found = false;
        int position = -1;
        for (int i = 0; i < products.size() && !found; i++)
            if (products.get(i).getID().toLowerCase().equals(ID.toLowerCase())) {
                found = true;
                position = i;
            }
        return position;
    }

    private Genre makeIntInputToGenre(int input) {
        switch (input) {
            case 1:
                return Genre.SCIENCE_FICTION;
            case 2:
                return Genre.FANTASY;
            case 3:
                return Genre.HISTORICAL_NOVEL;
            default:
                return null;
        }
    }

    private Category makeIntInputToCategory(int input) {
        switch (input) {
            case 1:
                return Category.VARIETIES;
            case 2:
                return Category.DESIGN;
            case 3:
                return Category.SCIENTIFIC;
            default:
                return null;
        }
    }

    public String showProductLabeledAttributes(String ID) {
        String info = "";
        BibliographicProduct product = getProductByID(ID);
        if (product != null) {
            if (product instanceof Book) {
                info = ((Book) product).labbeldAttributes();
            } else if (product instanceof Magazine) {
                info = ((Magazine) product).labbeldAttributes();
            }
        }
        return info;
    }

    public String displayInformationByProduct(String ID, int option) {
        String info = "";
        BibliographicProduct product = getProductByID(ID);
        if (option == 6) {
            if (product instanceof Book) {
                info = "What would you like to change the review for: ";
            } else if (product instanceof Magazine) {
                info = "What would you like to change the frecuency of issuance for: ";
            }
        } else if (option == 7) {
            info = displayEnumAtributesBasedOnType(product);
        }

        return info;
    }

    private String displayEnumAtributesBasedOnType(BibliographicProduct product) {
        String info = "";
        if (product instanceof Book) {
            info = "Please select the new genre of the book: " + "\n" +
                    "1.Science fiction" + "\n" +
                    "2.Fantasy" + "\n" +
                    "3.Historical novel";

        } else if (product instanceof Magazine) {
            info = "Please select the new category of the magazine: " + "\n" +
                    "1.Varieties" + "\n" +
                    "2.Design" + "\n" +
                    "3.Scientific";

        }
        return info;
    }

    public String changeProduct(String productID, int option, String newValue) {
        String msg = "We could not find this product";
        BibliographicProduct product = getProductByID(productID);
        if (product != null) {
            switch (option) {
                case 1:
                    product.setName(newValue);
                    msg = "Product name changed";
                    break;
                case 2:
                    try {
                        product.setNumberOfPages(Integer.parseInt(newValue));
                        msg = "Product number of pages changed";
                    } catch (NumberFormatException e) {
                        msg = "It must be a numeric type";
                    }

                    break;
                case 3:
                    try {
                        product.setPublicationDate(Utils.stringToCalendar(newValue));
                        msg = "Product publication date changed";
                        break;
                    } catch (ParseException e) {
                        msg = "The date entered does not match the pattern (dd/MM/yyyy) \nIt wasn't posible to make the change.";
                    }
                case 4:
                    product.setURL((String) newValue);
                    msg = "Product URL changed";
                    break;
                case 5:
                    try {
                        product.setValue(Double.parseDouble(newValue));
                        msg = "Product value changed";
                    } catch (NumberFormatException e) {
                        msg = "It must be a numeric type";

                    }
                    break;
                case 6:
                    if (product instanceof Book) {
                        ((Book) product).setShortReview(newValue);
                    } else if (product instanceof Magazine) {
                        ((Magazine) product).setFrecuencyOfIssuance(newValue);
                    }
                case 7:
                    if (product instanceof Book) {
                        ((Book) product).setGenre((Genre) makeIntInputToGenre(Integer.parseInt(newValue)));
                        msg = "Product genre changed";
                    } else if (product instanceof Magazine) {
                        ((Magazine) product).setCategory((Category) makeIntInputToCategory(Integer.parseInt(newValue)));
                        msg = "Product category changed";
                    }
                    break;
            }

        }
        return msg;
    }

    private BibliographicProduct getProductByID(String ID) {
        if (findProductByID(ID) == -1) {
            return null;
        }
        return products.get(findProductByID(ID));
    }

    public String addProductToUser(String userID, String productID) {
        AbstractUser user = users.get(userID);
        BibliographicProduct product = getProductByID(productID);

        if (user == null || product == null) {
            return "Either the user or the product ID was not found";
        }

        if (user instanceof Standar) {
            if (user.amountOfBook() >= 5) {
                return "The user has already 5 books";
            }
            if (user.amountOfMagazines() >= 2) {
                return "The user is already subscribed to 2 magazines";
            }
        }

        if (user.hasProduct(productID)) {
            return "This user already has this product";
        }

        product.setCopiesSold(product.getCopiesSold() + 1);
        String msg = user.addProduct(product);
        return msg;
    }

    public String unsubscribeMagazine(String userID, String magazineID) {
        String msg = "Either the user or the magazine ID was not found";
        BibliographicProduct product = getProductByID(magazineID);
        AbstractUser user = users.get(userID);

        if (product != null && user != null) {
            if (product instanceof Magazine) {
                msg = user.removeProduct(magazineID);
                product.setCopiesSold(product.getCopiesSold() - 1);
            } else {
                msg = "The product you selceted is not a magazine";
            }
        }

        return msg;
    }

    public String productsOfAUser(String userID) {
        String info = "The user does not exist";
        AbstractUser user = users.get(userID);
        if (user != null) {
            info = user.productsOfAUser();
        }
        return info;

    }

    public String startAReagindSession(String productID, char option, String userID) {
        String reading = "";
        BibliographicProduct userProduct = users.get(userID).getProductByID(productID);
        AbstractUser user = users.get(userID);
        int currentPage = 0;

        if (userProduct != null && user.hasProduct(productID)) {

            if (option == 'a' || option == 'A') {

                currentPage = userProduct.previousPage();

            } else if (option == 's' || option == 'S') {

                currentPage = userProduct.nextPage();

            } else if (option == 'B' || option == 'b') {
                BibliographicProduct product = products.get(findProductByID(productID));
                product.setNumberOfPagesRead(product.getNumberOfPagesRead() + userProduct.getNumberOfPagesRead());
            }

            reading = "\n\tReading session in progress...\n" +
                    "\nReading: " + userProduct.getName() +
                    "\nReagind page # " + userProduct.getCurrentPageBeingRead() + " of "
                    + userProduct.getNumberOfPages() +
                    "\n\nPress 'a' to go to the prevoius page" +
                    "\nPress 's' to go to the next page" +
                    "\nPress 'B' to go back to the library" + "\n\n" +
                    showPublicity(currentPage, user);
        } else {
            reading = "The user does not have this book";
        }
        return reading;

    }

    public String showPublicity(int currentPage, AbstractUser user) {
        Random rand = new Random();
        String[] adds = { "Subscribe to Combo Plus and get Disney+ and Star+ at an incredible price!",
                "Now your pets have a favorite app: Laika. The best products for your furry.",
                "We are celebrating our anniversary! Visit your nearest Éxito and be surprised with the best offers." };
        String add = "";
        if (currentPage % 5 == 0 && (user instanceof Standar)) {
            add = adds[rand.nextInt((2 - 1) + 1) + 1];
        }
        return add;
    }

}
