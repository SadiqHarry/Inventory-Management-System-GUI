package main.project;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class MainController implements Initializable {


    //Buttons
    @FXML private Button addPartsButton;
    @FXML private Button addProductsButton;
    @FXML private Button mainScreenExitButton;
    @FXML private Button deleteParts;
    @FXML private Button modify;

    //Parts Table & Columns
    @FXML private TableView<Part> partsTableView;
    @FXML private TableColumn<?, ?> partsIdColumn;
    @FXML private TableColumn<?, ?> partsInventoryLevelColumn;
    @FXML private TableColumn<?, ?> partsNameColumn;
    @FXML private TableColumn<?, ?> partsPriceColumn;

    //Product Table & Columns
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<?, ?> productsIdColumn;
    @FXML private TableColumn<?, ?> productsInventoryLevelColumn;
    @FXML private TableColumn<?, ?> productsNameColumn;
    @FXML private TableColumn<?, ?> productsPriceColumn;

    //Instances
    private static Part partsModified;

    //Assigns method
    public static Part getPartsModified() {
        return partsModified;
    }



    //Initializes the addPartsButton and switches to "AddParts.fxml" Scene.
    @FXML void switchToAddParts(ActionEvent event) throws IOException {
            Parent addParts = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddParts.fxml")));
            Scene scene = new Scene(addParts);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

    }
    //Initializes the "delete" button on Parts table
    @FXML void deleteSelectedParts(ActionEvent event) {
        Part partSelected = partsTableView.getSelectionModel().getSelectedItem();
        if (partSelected != null) {
           boolean confirmDelete = Inventory.confirmation("Confirm: Delete");
            if (confirmDelete) {
               Inventory.delete(partSelected);
            }
        }
            else {
                Inventory error = new Inventory();
                error.errorMessage("Please select value");
            }
        }


        //Initializes the "modify" button and displays the selected part
    @FXML void modifyPartsAction(ActionEvent event) throws IOException {
        partsModified = partsTableView.getSelectionModel().getSelectedItem();
        if (partsModified == null) {
            Inventory display = new Inventory();
            display.errorMessage("Error: Please Select a part to modify");
        } else {
            Parent modify = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModifyParts.fxml")));
            Scene scene = new Scene(modify);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }



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
        productsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}




