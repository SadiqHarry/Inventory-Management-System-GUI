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
import java.util.Objects;
import java.util.ResourceBundle;

public class AddProductsController implements Initializable {


    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    @FXML private TableView<Part> associatedPartTable;
    @FXML private TableColumn<Part, Integer> associatedPartIdColumn;
    @FXML private TableColumn<Part, Integer> associatedPartInventoryColumn;
    @FXML private TableColumn<Part, String> associatedPartNameColumn;
    @FXML private TableColumn<Part, Double> associatedPartPriceColumn;


    @FXML private TableView<Part> partsTableView;
    @FXML private TableColumn<Part, Integer> partsIdColumn;
    @FXML private TableColumn<Part, Integer> partsInventoryLevelColumn;
    @FXML private TableColumn<Part, String> partsNameColumn;
    @FXML private TableColumn<Part, Double> partsPriceColumn;


    @FXML private TextField productId;
    @FXML private TextField productInventory;
    @FXML private TextField productMax;
    @FXML private TextField productMin;
    @FXML private TextField productName;
    @FXML private TextField productPrice;

    @FXML private Button saveButton;
    @FXML private Button backToMain;

    @FXML private TextField partSearchTextField;




    @FXML
    void saveButton(ActionEvent event) throws IOException {

        try {
            int id = 0;
            String name = productName.getText();
            double price = Double.parseDouble(productPrice.getText());
            int stock = Integer.parseInt(productInventory.getText());
            int min = Integer.parseInt(productMin.getText());
            int max = Integer.parseInt(productMax.getText());

            if (name == null || name.trim().isEmpty()) {
                Inventory display = new Inventory();
                display.errorMessage("Error: Please Enter Name");
            } else {
                if (min > max) {
                    Inventory display = new Inventory();
                    display.errorMessage("Error: Inventory minimum must be less than maximum!");
                    return;
                }
                if (stock < min || stock > max) {
                    Inventory display = new Inventory();
                    display.errorMessage("Error: Inventory must be in between minimum and maximum!");
                    return;
                }


                Product newTotaProduct = new Product(id, name, price, stock, min, max);

                for (Part part : associatedParts) {
                    newTotaProduct.newAssociatedParts(part);
                }

                newTotaProduct.setId(Inventory.getNewProductId());
                Inventory.addProduct(newTotaProduct);
                returnMain(event);

            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }
    void returnMain(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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

    @FXML
    void addPartButton(ActionEvent event) {

        Part partSelected= partsTableView.getSelectionModel().getSelectedItem();

        if (partSelected == null) {
            Inventory display = new Inventory();
            display.errorMessage("Error: No Selected");
        } else {
            associatedParts.add(partSelected);
            associatedPartTable.setItems(associatedParts);
        }

    }
    @FXML
    void removeAssociatedPart(ActionEvent event) {
        Part partSelected = associatedPartTable.getSelectionModel().getSelectedItem();

        if (partSelected == null) {
            Inventory display = new Inventory();
            display.errorMessage("Error: No Selected ");
        } else {
            Inventory.confirmation("Confirm: Delete");
            associatedParts.remove(partSelected);
            associatedPartTable.setItems(associatedParts);
        }
    }

    @FXML
    void partSearchButton(ActionEvent event) {
        ObservableList<Part> totalParts = Inventory.getTotalParts();
        ObservableList<Part> partsResult = FXCollections.observableArrayList();
        String search = partSearchTextField.getText().trim().toLowerCase();

        //Method to check search (removes whitespaces errors and case sensitivity)
        for (Part part : totalParts) {
            if (String.valueOf(part.getId()).contains(search) ||
                    part.getName().toLowerCase().indexOf(search.replace(" ", "")) >= 0) {
                partsResult.add(part);
            }

        }
        // Sets result to tableview
        partsTableView.setItems(partsResult);

        //checks if
        if (partsResult.size() == 0) {
            Inventory display = new Inventory();
            display.errorMessage("Error: not found");
        }
        if (search.isEmpty()) {
            Inventory display = new Inventory();
            display.errorMessage("Error: Empty Field");
        }
    }

    @FXML
    void checkPartSearchField(KeyEvent event) {
        if (partSearchTextField.getText().isEmpty()) {
            partsTableView.setItems(Inventory.getTotalParts());
        }
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTableView.setItems(Inventory.getTotalParts());

        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

}

