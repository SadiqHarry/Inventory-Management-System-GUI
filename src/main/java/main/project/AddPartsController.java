package main.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddPartsController implements Initializable {

    @FXML
    private Button backToMain;

    @FXML
    private RadioButton inHouseButton;

    @FXML
    private RadioButton outsourcedButton;

    @FXML
    private TextField partId;

    @FXML
    private TextField partInventory;

    @FXML
    private TextField partMax;

    @FXML
    private TextField partMin;

    @FXML
    private TextField partName;

    @FXML
    private TextField partPrice;

    @FXML
    private TextField parttoggle;

    @FXML
    private Button saveButton;

    @FXML
    private Text toggleText;


    @FXML
    void inHouse(ActionEvent event) {
        toggleText.setText("Machine ID");
    }

    @FXML
    void outSource(ActionEvent event) {
        toggleText.setText("Company Name");
    }


    /***
     * Cancel and return to MainScreen
     * @param event
     * @throws IOException
     */
    @FXML
    void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();

    }

    @FXML
    void saveButton(ActionEvent event) throws IOException {
        System.out.println("Save button clicked");
        int id = 0;
        String name = partName.getText();
        double price = Double.parseDouble(partPrice.getText());
        int stock = Integer.parseInt(partInventory.getText());
        int min = Integer.parseInt(partMin.getText());
        int max = Integer.parseInt(partMax.getText());
        int machineId = 0;
        String companyName = "";
        boolean partAddSuccessful = false;

        if (inHouseButton.isSelected()) {
            machineId = Integer.parseInt(parttoggle.getText());
            InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
            newInHousePart.setId(Inventory.getNewPartId());
            Inventory.addPart(newInHousePart);
            partAddSuccessful = true;
        }

        if (outsourcedButton.isSelected()) {
            companyName = parttoggle.getText();
            Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
            newOutsourcedPart.setId(Inventory.getNewPartId());
            Inventory.addPart(newOutsourcedPart);
            partAddSuccessful = true;
        }
        if (partAddSuccessful) {
            returnToMainScreen(event);

        }
    }

    private void returnToMainScreen(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }




    @Override
        public void initialize (URL url, ResourceBundle resourceBundle){

            inHouseButton.setSelected(true);
        }


    }
