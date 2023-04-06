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
import java.util.ResourceBundle;


public class MainScreenController implements Initializable {


    /***
     * Buttons
     */
    @FXML private Button addPartsButton;
    @FXML private Button addProductsButton;
    @FXML private Button mainScreenExitButton;

    /***
     * Add Parts Table & Columns
     */
    @FXML private TableView<Part> partsTableView;
    @FXML private TableColumn<?, ?> partsIdColumn;
    @FXML private TableColumn<?, ?> partsInventoryLevelColumn;
    @FXML private TableColumn<?, ?> partsNameColumn;
    @FXML private TableColumn<?, ?> partsPriceColumn;


    /***
     * Initializes the addPartsButton and switches to "AddParts.fxml" Scene.
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToAddParts(ActionEvent event) throws IOException {
            Parent addParts = FXMLLoader.load(getClass().getResource("AddParts.fxml"));
            Scene scene = new Scene(addParts);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

    }

    /***
     * Initializes the addProductsButton and switches to "AddProducts.fxml" Scene.
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToAddProducts(ActionEvent event) throws IOException {
        Parent addProducts = FXMLLoader.load(getClass().getResource("AddProducts.fxml"));
        Scene scene = new Scene(addProducts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    /***
     * Initializes and Exists the program from "MainScreen"
     * @param mainScreenExitButton
     */
    @FXML
    void exitMainScreen(ActionEvent mainScreenExitButton) {
        Stage stage = (Stage) ((Node) mainScreenExitButton.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Populate parts table view
        partsTableView.setItems(Inventory.getAllParts());
        partsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


}
