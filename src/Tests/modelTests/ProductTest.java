package Tests.modelTests;

import model.*;
import model.Book.Genre;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class ProductTest {
    private Controller controller;

    // ADDING PART
    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @Test
    void whenCreatedAnObjectMakeSureItSnOTNull() {
        // Arrange
        String productName = "Game of thrones";
        int numberOfPages = 500;
        Calendar publicationDate = Calendar.getInstance();
        String URL = "wwTP://www.gameofthrones.com";
        double value = 10.0;
        String review = "The best book ever";
        int genre = 1;
        int bookOrMagazine = 1;
        // Act
        BibliographicProduct product = controller.createBibliographicProduct(productName, numberOfPages,
                publicationDate, URL, value,
                review, genre, bookOrMagazine);

        Assertions.assertNotNull(product);
    }

    @Test
    void validateBookwasadded() {
        // Arrange
        String productName = "Game of thrones";
        int numberOfPages = 500;
        Calendar publicationDate = Calendar.getInstance();
        String URL = "wwTP://www.gameofthrones.com";
        double value = 10.0;
        String review = "The best book ever";
        int genre = 1;
        int bookOrMagazine = 1;
        // Act
        BibliographicProduct product = controller.createBibliographicProduct(productName, numberOfPages,
                publicationDate, URL, value,
                review, genre, bookOrMagazine);

        controller.addProduct(product);

        // Assert
        Assertions.assertTrue(controller.getProducts().contains(product) && product instanceof Book);
    }

    @Test
    void validateMagazinewasadded() {
        // Arrange
        String productName = "Game of thrones";
        int numberOfPages = 500;
        Calendar publicationDate = Calendar.getInstance();
        String URL = "wwTP://www.gameofthrones.com";
        double value = 10.0;
        String frecuencyOfIssuance = "weekly";
        int category = 1;
        int bookOrMagazine = 2;
        // Act
        BibliographicProduct product = controller.createBibliographicProduct(productName, numberOfPages,
                publicationDate, URL, value,
                frecuencyOfIssuance, category, bookOrMagazine);
        controller.addProduct(product);
        // Assert
        Assertions.assertTrue(controller.getProducts().contains(product) && product instanceof Magazine);
    }

    // This a test that sometimes might past and sometimes not depending if there is
    // a duplicated ID.
    @Test
    void whenAddingAVastAmountOfItemsAreDuplicatesGenerated() {
        Controller driver = new Controller();
        ArrayList<BibliographicProduct> products = new ArrayList<>();
        ArrayList<String> seenIDs = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            BibliographicProduct product = driver.createBibliographicProduct("Juegos del hambre " + i, i,
                    Calendar.getInstance(), "www.juegosdelhambre" + i + ".com", i, "Review", 1,
                    1);
            products.add(product);
        }

        boolean hasDuplicates = false;

        for (BibliographicProduct entry : products) {
            String id = entry.getID();
            if (seenIDs.contains(id)) {
                hasDuplicates = true;
                break;
            }
            seenIDs.add(id);

        }
        Assertions.assertFalse(hasDuplicates);
    }

    // DELETING PART

    @Test
    public void testDeleteBookProduct() {
        Controller driver = new Controller();
        BibliographicProduct book = new Book("Java for dummies", 1, Calendar.getInstance(), "URL", 12343, "Good",
                Book.Genre.FANTASY);
        book.setID("1");
        driver.addProduct(book);
        String result = driver.deleteProduct("1");
        Assertions.assertEquals("Product deleted", result);
        Assertions.assertEquals(-1, driver.findProductByID("1"));
    }

    @Test
    public void testDeleteMagazineProduct() {
        Controller driver = new Controller();
        BibliographicProduct magazine = new Magazine("News on java", 1, Calendar.getInstance(), "https", 9, "Mensually",
                Magazine.Category.DESIGN);
        magazine.setID("2");
        driver.addProduct(magazine);
        String result = driver.deleteProduct("2");
        Assertions.assertEquals("Product deleted", result);
        Assertions.assertEquals(-1, driver.findProductByID("2"));
    }

    @Test
    public void testDeleteNonExistingProduct() {
        Controller driver = new Controller();
        String result = driver.deleteProduct("3");
        Assertions.assertEquals("We could not find this product", result);
    }

    @Test
    public void testDeleteProductNotAdded() {
        Controller driver = new Controller();
        BibliographicProduct book = new Magazine("News on java", 1, Calendar.getInstance(), "https", 9, "Mensually",
                Magazine.Category.DESIGN);
        driver.addProduct(book);
        String result = driver.deleteProduct("2");
        Assertions.assertEquals("We could not find this product", result);
        Assertions.assertEquals(1, driver.getProducts().size());
    }

    @Test
    public void testWhenHavingMultipleProductWithTheSameIDOnlyDeleteTheFirstOneYouFind() {
        Controller driver = new Controller();

        BibliographicProduct book1 = new Book("Java for dummies", 1, Calendar.getInstance(), "URL", 12343, "Good",
                Book.Genre.FANTASY);
        BibliographicProduct magazine1 = new Magazine("News on java", 1, Calendar.getInstance(), "https", 9,
                "Mensually",
                Magazine.Category.DESIGN);

        magazine1.setID("1");
        book1.setID("1");

        driver.addProduct(book1);
        driver.addProduct(magazine1);
        driver.deleteProduct("1");

        Assertions.assertEquals(0, driver.findProductByID("1"));
        Assertions.assertEquals(1, driver.getProducts().size());
    }

    // TEST CHANGES
    @Test
    public void testChangeProductName() {
        // Arrange
        Controller library = new Controller();
        Book book = new Book("Java for dummies", 1, Calendar.getInstance(), "URL", 12343, "Good",
                Book.Genre.FANTASY);

        library.addProduct(book);

        // Act
        String result = library.changeProduct(book.getID(), 1, "New Book Name");

        // Assert
        Assertions.assertEquals("Product name changed", result);
        Assertions.assertEquals("New Book Name", book.getName());
    }

    @Test
    public void testChangeProductNumberOfPages() {
        // Arrange
        Controller library = new Controller();
        Magazine magazine = new Magazine("News on java", 1, Calendar.getInstance(), "https", 9,
                "Mensually",
                Magazine.Category.DESIGN);
        library.addProduct(magazine);

        // Act
        String result = library.changeProduct(magazine.getID(), 2, "100");

        // Assert
        Assertions.assertEquals("Product number of pages changed", result);
        Assertions.assertEquals(100, magazine.getNumberOfPages());
    }

    @Test
    public void testChangeProductPublicationDate() {
        // Arrange
        Controller controller = new Controller();
        Book book = new Book("Java for dummies", 1, Calendar.getInstance(), "URL", 12343, "Good",
                Book.Genre.FANTASY);
        controller.addProduct(book);

        // Act
        String result = controller.changeProduct(book.getID(), 3, "05/05/2023");

        // Assert
        Assertions.assertEquals("Product publication date changed", result);
        Assertions.assertEquals("05/05/2023", book.getPublicationDateString());
    }

    @Test
    public void testChangeProductURL() {
        // Create a test product
        Book book = new Book("Java for dummies", 1, Calendar.getInstance(), "URL", 12343, "Good",
                Book.Genre.FANTASY);
        controller.addProduct(book);

        // Change the URL of the product
        String result = controller.changeProduct(book.getID(), 4, "https://www.test.com/new-url");

        // Assert that the URL was changed successfully
        Assertions.assertEquals("Product URL changed", result);
        Assertions.assertEquals("https://www.test.com/new-url", book.getURL());
    }

    @Test
    public void testChangeProductValue() {
        // Create a test product
        Magazine magazine = new Magazine("News on java", 1, Calendar.getInstance(), "https", 9,
                "Mensually",
                Magazine.Category.DESIGN);
        controller.addProduct(magazine);

        // Change the value of the product
        String result = controller.changeProduct(magazine.getID(), 5, "12.99");

        // Assert that the value was changed successfully
        Assertions.assertEquals("Product value changed", result);
        Assertions.assertEquals(12.99, magazine.getValue(), 0.001);
    }

    @Test
    public void testChangeBookGenre() {
        // Create a test book
        Book book = new Book("Java for dummies", 1, Calendar.getInstance(), "URL", 12343, "Good",
                Book.Genre.SCIENCE_FICTION);
        controller.addProduct(book);

        // Change the genre of the book
        String result = controller.changeProduct(book.getID(), 7, "2");

        // Assert that the genre was changed successfully
        Assertions.assertEquals("Product genre changed", result);
        Assertions.assertEquals(Genre.FANTASY, book.getGenre());
    }

    @Test
    public void testChangeMagazineCategory() {
        // Create a test magazine
        Magazine magazine = new Magazine("News on java", 1, Calendar.getInstance(), "https", 9,
                "Mensually",
                Magazine.Category.DESIGN);
        controller.addProduct(magazine);

        // Change the category of the magazine
        String result = controller.changeProduct(magazine.getID(), 7, "3");

        // Assert that the category was changed successfully
        Assertions.assertEquals("Product category changed", result);
        Assertions.assertEquals(Magazine.Category.SCIENTIFIC, magazine.getCategory());
    }

}