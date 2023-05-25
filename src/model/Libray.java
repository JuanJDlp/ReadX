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

        sortMat(library.size() - 1);

    }

    private BibliographicProduct[][] createPage() {
        return new BibliographicProduct[ROWS][COLUMNS];
    }

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

    public boolean deleteProduct(String productID) {
        boolean found = false;
        for (int i = 0; i < library.size() && !found; i++) {
            for (int j = 0; j < ROWS && !found; j++) {
                for (int k = 0; k < COLUMNS && !found; k++) {
                    if (library.get(i)[j][k] != null) {
                        if (library.get(i)[j][k].getID().toLowerCase().equalsIgnoreCase(productID)) {
                            library.get(i)[j][k] = null;
                            found = true;
                        }
                    }

                }
            }
        }
        sortMat(library.size() - 1);
        return found;

    }

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

    public boolean hasProduct(String productID) {
        return getProduct(productID) != null;
    }

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

    public void sortMat(int position) {
        ArrayList<BibliographicProduct> temp = new ArrayList<>();
        BibliographicProduct[][] mat = library.get(position);
        int k = 0;

        // copy the elements of matrix
        // one by one into temp[]
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++)
                if (mat[i][j] != null) {
                    temp.add(mat[i][j]);
                }

        // sort temp[]
        Collections.sort(temp);
        // copy the elements of temp[]
        // one by one in mat[][]
        k = 0;
        for (int i = 0; i < ROWS && !temp.isEmpty(); i++)
            for (int j = 0; j < COLUMNS && !temp.isEmpty(); j++) {
                mat[i][j] = temp.get(k);
                k++;
            }
    }

    public int size() {
        return library.size();
    }

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
