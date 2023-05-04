package Tests.modelTests;

import model.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class ProductTest {
    private Controller controller;

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
}