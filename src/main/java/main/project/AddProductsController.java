package main.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddProductsController {

    @FXML
    private Button backToMain;

    @FXML
    private TableColumn<?, ?> partsIdColumn;

    @FXML
    private TableColumn<?, ?> partsInventoryLevelColumn;

    @FXML
    private TableColumn<?, ?> partsNameColumn;

    @FXML
    private TableColumn<?, ?> partsPriceColumn;

    @FXML
    private TableView<?> partsTableView;

    @FXML
    private TextField productId;

    @FXML
    private TextField productInventory;

    @FXML
    private TextField productMax;

    @FXML
    private TextField productMin;

    @FXML
    private TextField productName;

    @FXML
    private TextField productPrice;

    @FXML
    private Button saveButton;

    @FXML
    void saveButton(ActionEvent event) {

    }

    @FXML
    void switchToMain(ActionEvent event) throws IOException {
        boolean confirmExit = Inventory.confirmation("Confirm: Return to main menu");
        if(confirmExit){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScreen.fxml")));
            Scene scene = new Scene(root);
            Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
            MainScreenReturn.setScene(scene);
            MainScreenReturn.show();}

    }

}

