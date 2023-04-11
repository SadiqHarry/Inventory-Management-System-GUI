package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


public class Inventory {

    // Indexes and assigns ID Parts
    private static int partId = 0;
    public static int getNewPartId() {
        return ++partId;
    }

    //Adds inhouse or outsource to part
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    //Update table with all parts
    private static  ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }




    private static int productId = 0;
    public static int getNewProductId() {
        return ++productId;
    }

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }




    //Method to handle error messages
    public void errorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}



