package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;


public class Inventory {

    // Indexes and assigns ID Parts
    private static int partId = 0;

    public static int getNewPartId() {
        return ++partId;
    }

    //Adds inhouse or outsource to part
    public static void addNewPart(Part newPart) {
        totalParts.add(newPart);
    }

    //Update table with all parts
    private static final ObservableList<Part> totalParts = FXCollections.observableArrayList();

    public static ObservableList<Part> getTotalParts() {
        return totalParts;
    }


    // Indexes and assigns ID Product
    private static int productId = 0;

    public static int getNewProductId() {
        return ++productId;
    }


    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }


    //Update table with all products
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static ObservableList<Product> getTotalProducts() {
        return allProducts;
    }


    //Method to handle error messages
    public void errorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //Method to executes the delete method
    public static void delete(Part partSelected) {
        totalParts.remove(partSelected);
    }

    //Method to executes the delete method
    public static void delete(Product productSelected) {
        allProducts.remove(productSelected);
    }


    //Method for confirmation window
    public static boolean confirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention!");
        alert.setContentText(null);
        alert.setHeaderText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;

    }


    public static void deleteDuplicate(Part partSelected) {
        totalParts.remove(partSelected);

    }


    public static void deleteDuplicate(Product productSelected) {
        allProducts.remove(productSelected);
    }
}



