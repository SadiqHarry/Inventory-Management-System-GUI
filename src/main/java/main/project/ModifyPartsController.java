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
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModifyPartsController implements Initializable {

    Part partSelected = MainController.getPartsModified();
    @FXML private Button backToMain;
    @FXML private RadioButton inHouseButton;
    @FXML private RadioButton outSourcedButton;
    @FXML private TextField partId;
    @FXML private TextField partInventory;
    @FXML private TextField partMax;
    @FXML private TextField partMin;
    @FXML private TextField partName;
    @FXML private TextField partPrice;
    @FXML private TextField parttoggle;
    @FXML private Button saveButton;
    @FXML private Text toggleText;
    @FXML private ToggleGroup toggleGroup;



    //Assigns text to inHouseButton
    @FXML void inHouse(ActionEvent event) {
        toggleText.setText("Machine ID");
    }
    //Assigns text to outsourcedButton
    @FXML void outSource(ActionEvent event) {
        toggleText.setText("Company Name");
    }


    @FXML
    void saveButton(ActionEvent event) {
        try {
            int id = partSelected.getId();
            String name = partName.getText();
            double price = Double.parseDouble(partPrice.getText());
            int stock = Integer.parseInt(partInventory.getText());
            int min = Integer.parseInt(partMin.getText());
            int max = Integer.parseInt(partMax.getText());
            int machineId;
            String companyName;
            boolean Successful = false;

            if (name == null || name.trim().isEmpty()) {
                Inventory display = new Inventory();
                display.errorMessage("Error: Please Enter Name");
            }

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
            if (inHouseButton.isSelected()) {
                try {
                    machineId = Integer.parseInt(parttoggle.getText());
                    InHouse inHouse = new InHouse(id, name, price, stock, min, max, machineId);
                    Inventory.addNewPart(inHouse);
                    Successful = true;
                } catch (Exception e) {
                    Inventory display = new Inventory();
                    display.errorMessage("Error: Machine Id is numeric");
                }
            }

            if (outSourcedButton.isSelected()) {
                    try {
                    companyName = toggleText.getText();
                    Outsourced outsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                    Inventory.addNewPart(outsourced);
                    Successful = true;
                } catch (Exception e) {
                    Inventory display = new Inventory();
                    display.errorMessage("Error");
                }
            }

            if (Successful) {
                Inventory.deleteDuplicate(partSelected);
                returnMain(event);
            }
        } catch (IOException e) {
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




    //Initializes the Cancel button and return to MainScreen
    @FXML void switchToMain(ActionEvent event) throws IOException {
        boolean confirmExit = Inventory.confirmation("Confirm: Return to main menu");
        if(confirmExit){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScreen.fxml")));
            Scene scene = new Scene(root);
            Stage MainScreenReturn = (Stage) ((Node) event.getSource()).getScene().getWindow();
            MainScreenReturn.setScene(scene);
            MainScreenReturn.show();}

    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {


        // Set toggle method between radio buttons
        toggleGroup = new ToggleGroup();
        inHouseButton.setToggleGroup(toggleGroup);
        outSourcedButton.setToggleGroup(toggleGroup);


        boolean successful = false;
        if (partSelected instanceof InHouse) {
            parttoggle.setText(String.valueOf(((InHouse) partSelected).getMachineId()));
            successful = true;
        }
        if (partSelected instanceof Outsourced){
            parttoggle.setText(((Outsourced) partSelected).getCompanyName());
        }
        if (successful) {
            toggleGroup.selectToggle(inHouseButton);

        } else {
            toggleGroup.selectToggle(outSourcedButton);
        }





        // Get part values
        partId.setText(String.valueOf(partSelected.getId()));
        partName.setText(partSelected.getName());
        partInventory.setText(String.valueOf(partSelected.getStock()));
        partPrice.setText(String.valueOf(partSelected.getPrice()));
        partMax.setText(String.valueOf(partSelected.getMax()));
        partMin.setText(String.valueOf(partSelected.getMin()));


    }
}
