package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Factories.BookFactory;
import Factories.MagazineFactory;
import Factories.UserFactory;
import MyHashMap.MyHashMap;
import model.Book.Genre;
import model.Magazine.Category;

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

    public Genre makeIntInputToGenre(int input) {
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

    public Category makeIntInputToCategory(int input) {
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

            } else if (product instanceof Magazine) {

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
                    product.setNumberOfPages(Integer.parseInt(newValue));
                    msg = "Product number of pages changed";
                    break;
                case 3:
                    try {
                        product.setPublicationDate(stringToCalendar(newValue));
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
                    product.setValue(Double.parseDouble(newValue));
                    msg = "Product value changed";
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

    private Calendar stringToCalendar(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(dateString));
        return calendar;
    }

}
