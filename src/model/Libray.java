package model;

import java.util.ArrayList;
import java.util.Collections;

import Interfaces.INavigable;

public class Libray implements INavigable {
    private static final int ROWS = 5;
    private static final int COLUMNS = 5;
    private ArrayList<BibliographicProduct[][]> library;
    private int currentPage;

    public Libray() {
        library = new ArrayList<>();
        library.add(createPage());
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * This function adds a BibliographicProduct to a library matrix and creates a
     * new page if the current
     * page is full.
     * 
     * @param product an instance of the BibliographicProduct class that needs to be
     *                added to the library.
     */
    public void addProduct(BibliographicProduct product) {
        BibliographicProduct[][] matrix = library.get(library.size() - 1);
        if (isMatrixFull(matrix)) {
            library.add(createPage());
            matrix = library.get(library.size() - 1);
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = product;
                    return;
                }
            }
        }

        sortLibrary();

    }

    /**
     * This function creates a two-dimensional array of BibliographicProduct objects
     * with a specified
     * number of rows and columns.
     * 
     * @return A 2-dimensional array of BibliographicProduct objects with ROWS rows
     *         and COLUMNS columns is
     *         being returned.
     */

    private BibliographicProduct[][] createPage() {
        return new BibliographicProduct[ROWS][COLUMNS];
    }

    /**
     * The function checks if a given matrix is full by iterating through its
     * elements and returning false
     * if any element is null.
     * 
     * @param matrix a 2-dimensional array of BibliographicProduct objects
     *               representing a matrix.
     * @return The method is returning a boolean value, either true or false.
     */
    private boolean isMatrixFull(BibliographicProduct[][] matrix) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (matrix[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This function deletes a product with a given ID from a library and returns
     * true if the product was
     * found and deleted.
     * 
     * @param productID a String representing the ID of the product that needs to be
     *                  deleted from the
     *                  library.
     * @return The method is returning a boolean value indicating whether or not a
     *         product with the given
     *         productID was found and deleted from the library.
     */
    public boolean deleteProduct(String productID) {
        boolean found = false;
        for (int i = 0; i < library.size(); i++) {
            for (int j = 0; j < ROWS && !found; j++) {
                for (int k = 0; k < COLUMNS && !found; k++) {
                    if (library.get(i)[j][k] != null) {
                        if (library.get(i)[j][k].getID().equalsIgnoreCase(productID)) {
                            library.get(i)[j][k] = null;
                            found = true;
                        }
                    }

                }
            }
        }
        if (found) {
            sortLibrary();
        }
        return found;

    }

    /**
     * The function returns the number of non-null elements in a matrix stored in a
     * specific position of a
     * library.
     * 
     * @param position The position parameter is an integer value representing the
     *                 index of a matrix in a
     *                 library.
     * @return The method `sizeOfAMatrix` returns an integer value which represents
     *         the number of non-null
     *         elements in a two-dimensional array of `BibliographicProduct`
     *         objects.
     */
    public int sizeOfAMatrix(int position) {
        int counter = 0;
        BibliographicProduct[][] mat = library.get(position);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (mat[i][j] != null) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * This function searches for a BibliographicProduct in a library by its ID and
     * returns it.
     * 
     * @param productID a String representing the ID of the product being searched
     *                  for in the library.
     * @return The method is returning a BibliographicProduct object.
     */
    public BibliographicProduct getProduct(String productID) {
        BibliographicProduct product = null;
        boolean found = false;
        for (int i = 0; i < library.size() && !found; i++) {
            for (int j = 0; j < ROWS && !found; j++) {
                for (int k = 0; k < COLUMNS && !found; k++) {
                    if (library.get(i)[j][k] != null
                            && library.get(i)[j][k].getID().toLowerCase().equals(productID.toLowerCase())) {
                        found = true;
                        product = library.get(i)[j][k];
                    }

                }
            }
        }
        return product;
    }

    /**
     * The function checks if a product with a given ID exists in the system.
     * 
     * @param productID A String representing the ID of a product.
     * @return A boolean value indicating whether a product with the specified
     *         productID exists in the
     *         system.
     */
    public boolean hasProduct(String productID) {
        return getProduct(productID) != null;
    }

    /**
     * The function displays a matrix of bibliographic products with their IDs and
     * page numbers.
     * 
     * @param currentPage The current page of the library matrix being displayed.
     * @return The method `showMatrix` returns a string that represents a matrix of
     *         bibliographic products
     *         in the library, with their IDs displayed in the corresponding cells.
     *         The string includes row and
     *         column headers, as well as empty cells represented by "---". The
     *         specific matrix displayed depends
     *         on the `currentPage` parameter passed to the method.
     */
    public String showMatrix(int currentPage) {
        String info = "| Y/X | ";

        for (int i = 0; i < COLUMNS; i++) {
            info += "|  " + i + "  | ";
        }
        info += "\n";
        BibliographicProduct[][] matrix = library.get(currentPage);
        for (int i = 0; i < ROWS; i++) {
            info += "|  " + i + "  | ";
            for (int j = 0; j < COLUMNS; j++) {
                if (matrix[i][j] != null) {
                    info += "| " + matrix[i][j].getID() + " | ";
                } else {
                    info += "| --- |" + " ";
                }
            }
            info += "\n";
        }
        return info;
    }

    /**
     * This function counts the number of books in a library represented as a 3D
     * array.
     * 
     * @return The method `amountOfBook()` returns an integer value which represents
     *         the total number of
     *         books in the library.
     */
    public int amountOfBook() {
        int counter = 0;
        for (int i = 0; i < library.size(); i++) {
            for (int j = 0; j < ROWS; j++) {
                for (int k = 0; k < COLUMNS; k++) {
                    if (library.get(i)[j][k] instanceof Book) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    /**
     * This function counts the number of magazines in a library represented as a 3D
     * array.
     * 
     * @return The method `amountOfMagazines()` returns an integer value which
     *         represents the total number
     *         of magazines in the library.
     */
    public int amountOfMagazines() {
        int counter = 0;
        for (int i = 0; i < library.size(); i++) {
            for (int j = 0; j < ROWS; j++) {
                for (int k = 0; k < COLUMNS; k++) {
                    if (library.get(i)[j][k] instanceof Magazine) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    /**
     * The function sorts a library by calling another function to sort each
     * individual material in the
     * library.
     */
    public void sortLibrary() {
        for (int i = 0; i < library.size(); i++) {
            sortMat(i);
        }
    }

    /**
     * The function sorts the non-null elements of a matrix of BibliographicProduct
     * objects in a library.
     * 
     * @param position The position parameter is an integer that represents the
     *                 index of the ArrayList in
     *                 the library that contains the BibliographicProduct matrix to
     *                 be sorted.
     */
    private void sortMat(int position) {
        ArrayList<BibliographicProduct> temp = new ArrayList<>();
        BibliographicProduct[][] mat = library.get(position);
        int rowCount = mat.length;
        int columnCount = mat[0].length;
        int nonNullCount = 0;

        // Copy the non-null elements of the matrix into temp[]
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (mat[i][j] != null) {
                    temp.add(mat[i][j]);
                    nonNullCount++;
                }
            }
        }

        // Sort temp[]
        Collections.sort(temp);

        // Copy the elements of temp[] back into mat[][]
        int index = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (index < nonNullCount) {
                    mat[i][j] = temp.get(index++);
                } else {
                    mat[i][j] = null; // Set remaining positions to null
                }
            }
        }
    }

    public int size() {
        return library.size();
    }

    /**
     * The function adds all the elements of an ArrayList of BibliographicProduct to
     * another list by
     * calling the addProduct method.
     * 
     * @param array An ArrayList of BibliographicProduct objects that contains the
     *              products to be added to
     *              the current list of products.
     */
    public void addAll(ArrayList<BibliographicProduct> array) {
        for (int i = 0; i < array.size(); i++) {
            addProduct(array.get(i));
        }

    }

    public BibliographicProduct getProductByCordinate(int x, int y) {
        return library.get(currentPage)[x][y];
    }

    @Override
    public int nextPage() {
        this.currentPage++;
        if (this.currentPage == library.size()) {
            this.currentPage = 0;
        }
        return this.currentPage;
    }

    @Override
    public int previousPage() {
        this.currentPage--;
        if (this.currentPage <= 0) {
            this.currentPage = 0;
        }
        return this.currentPage;
    }
}
