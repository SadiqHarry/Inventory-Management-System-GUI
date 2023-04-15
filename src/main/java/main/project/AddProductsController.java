package main.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    void switchToMain(ActionEvent event) {

    }

}

