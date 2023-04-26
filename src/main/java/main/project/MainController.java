package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Objects;
import java.util.ResourceBundle;


public class MainController implements Initializable {


    //Buttons
    @FXML private Button addPartsButton;
    @FXML private Button addProductsButton;
    @FXML private Button mainScreenExitButton;
    @FXML private Button deleteParts;
    @FXML private Button modify;

    //Text Fields
    @FXML private TextField partSearchTextField;
    @FXML private TextField productSearchTextField;


    //Parts Table & Columns
    @FXML private TableView<Part> partsTableView;
    @FXML private TableColumn<Part, Integer> partsIdColumn;
    @FXML private TableColumn<Part, Integer> partsInventoryLevelColumn;
    @FXML private TableColumn<Part, String> partsNameColumn;
    @FXML private TableColumn<Part, Double> partsPriceColumn;

    //Product Table & Columns
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, Integer> productsIdColumn;
    @FXML private TableColumn<Product, Integer> productsInventoryLevelColumn;
    @FXML private TableColumn<Product, String> productsNameColumn;
    @FXML private TableColumn<Product, Double> productsPriceColumn;

    //Instances
    private static Part partsModified;
    private static Product productModified;

    //Assigns method
    public static Part getPartsModified() {
        return partsModified;
    }
    public static Product getProductModified(){
        return productModified;
    }



    //Initializes the addPartsButton and switches to "AddParts.fxml" Scene.
    @FXML void switchToAddParts(ActionEvent event) throws IOException {
            Parent addParts = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddParts.fxml")));
            Scene scene = new Scene(addParts);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();}

    //Initializes the "delete" button on Parts table view
    @FXML void deleteSelectedParts(ActionEvent event) {
        Part partSelected = partsTableView.getSelectionModel().getSelectedItem();
        if (partSelected == null) {
            Inventory error = new Inventory();
            error.errorMessage("Error: Please select a part");}
            else {
                Inventory.confirmation("Confirm: Delete");
                Inventory.delete(partSelected);}}

    //Initializes the "delete" button on Products table view
    @FXML
    void deleteSelectedProducts(ActionEvent event) {
    Product productSelected = productsTable.getSelectionModel().getSelectedItem();
    if (productSelected == null){
    Inventory display = new Inventory();
        display.errorMessage("Error: Please Select a product");}
    else {
        Inventory.confirmation("Confirm");}
        Inventory.delete(productSelected);}


        //Initializes the "modify" button and displays the selected part
    @FXML void modifyPartsAction(ActionEvent event) throws IOException {
        partsModified = partsTableView.getSelectionModel().getSelectedItem();
        if (partsModified == null) {
            Inventory display = new Inventory();
            display.errorMessage("Error: Please select a part to modify");
        } else {
            Parent modify = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyParts.fxml")));
            Scene scene = new Scene(modify);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();}}

//Method modify product button
    @FXML void modifyProductButton(ActionEvent event) throws IOException {
        productModified = productsTable.getSelectionModel().getSelectedItem();

        if(productModified == null){
            Inventory display = new Inventory();
            display.errorMessage("Error: Please select a product to modify");
        } else {
            Parent modify = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyProducts.fxml")));
            Scene scene = new Scene(modify);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();}
    }


    //Search Method for parts tableview
    @FXML void partSearchButton(ActionEvent event) {
        ObservableList<Part> totalParts = Inventory.getTotalParts();
        ObservableList<Part> partsResult = FXCollections.observableArrayList();
        String searchResult = partSearchTextField.getText().trim().toLowerCase();

        //Method to check search (removes whitespaces errors and case sensitivity)
        for (Part part : totalParts) {
            if (String.valueOf(part.getId()).contains(searchResult) ||
                    part.getName().toLowerCase().contains(searchResult.toLowerCase().replace(" ", ""))) {
                partsResult.add(part);}
        }
        // Sets result to tableview
        partsTableView.setItems(partsResult);

        //checks if empty
        if (partsResult.size() == 0) {
            Inventory display = new Inventory();
            display.errorMessage("Error: not found");
        }
        if (searchResult.isEmpty()){
            Inventory display = new Inventory();
            display.errorMessage("Error: Empty Field");}
    }

    //Checks if field is empty and return table
    @FXML void checkPartSearchField(KeyEvent event) {
        if (partSearchTextField.getText().isEmpty()) {
            partsTableView.setItems(Inventory.getTotalParts());}
    }


    //Search method for product table view
    @FXML void productSearchButton(ActionEvent event) {
        ObservableList<Product> totalProducts = Inventory.getTotalProducts();
        ObservableList<Product> newTotalProducts = FXCollections.observableArrayList();
        String searchResult = productSearchTextField.getText();

        //Method to check search (removes whitespaces errors and case sensitivity)
        for (Product product : totalProducts) {
            if (String.valueOf(product.getId()).contains(searchResult) ||
                    product.getName().toLowerCase().contains(searchResult.toLowerCase().replace(" ", ""))) {
                    newTotalProducts.add(product);}
        }

        productsTable.setItems(newTotalProducts);

        //checks if empty
        if (newTotalProducts.size() == 0) {
            Inventory display = new Inventory();
            display.errorMessage("Error: not found");}

        if (newTotalProducts.isEmpty()){
            Inventory display = new Inventory();
            display.errorMessage("Error: Empty Field");}}

    //Checks if field is empty and return table
    @FXML void productSearchKeyPressed(KeyEvent event) {
        if (productSearchTextField.getText().isEmpty()) {
            productsTable.setItems(Inventory.getTotalProducts());}}



    //Initializes the addProductsButton and switches to "AddProducts.fxml" Scene.
    @FXML void switchToAddProducts(ActionEvent event) throws IOException {
        Parent addProducts = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddProducts.fxml")));
        Scene scene = new Scene(addProducts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    //Initializes the exit button and exists the program from "MainScreen"
    @FXML void exitMainScreen(ActionEvent mainScreenExitButton) {
        boolean confirmExit = Inventory.confirmation("Confirm: Exit");
        if(confirmExit){
        Stage stage = (Stage) ((Node) mainScreenExitButton.getSource()).getScene().getWindow();
        stage.close();}
    }


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

        // Populate parts table
        partsTableView.setItems(Inventory.getTotalParts());
        partsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Populates product table
        productsTable.setItems(Inventory.getTotalProducts());
        productsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));}}




