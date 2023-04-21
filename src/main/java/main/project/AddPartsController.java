package main.project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class AddPartsController implements Initializable {

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

    // Initializes the save button
    @FXML void saveButton(ActionEvent event) throws IOException {

        //Prints to console if save button was pressed
        System.out.println("Save button clicked");

        //Get text field values
        try {
            int id = 0;
            String name = partName.getText();
            double price = Double.parseDouble(partPrice.getText());
            int stock = Integer.parseInt(partInventory.getText());
            int min = Integer.parseInt(partMin.getText());
            int max = Integer.parseInt(partMax.getText());
            int machineId = 0;
            String companyName = "";
            boolean Successful = false;

            //Checks if name field is empty
            if (name == null || name.trim().isEmpty()) {
                Inventory display = new Inventory();
                display.errorMessage("Error: Please enter name");
                return;}

            //Checks if min field is greater than max field
            if (min > max) {
                Inventory display = new Inventory();
                display.errorMessage("Error: Inventory minimum must be less than maximum!");
                return;}

            // Checks to validate that inventory levels are between min and max
            if (stock < min || stock > max) {
                Inventory display =  new Inventory();
                display.errorMessage("Error: Inventory must be in between minimum and maximum!");
                return;}

            // Checks if inHouse radio button is toggled and calls addPart method to add it to table
            try {
                if (inHouseButton.isSelected()) {
                    machineId = Integer.parseInt(parttoggle.getText());
                    InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                    newInHousePart.setId(Inventory.getNewPartId());
                    Inventory.addNewPart(newInHousePart);
                    Successful = true;}

                //Checks machineId is a numeric value
            } catch (Exception e) {
                Inventory display = new Inventory();
                display.errorMessage("Error invalid input: MachineId must be numeric value");}

            //Checks if outSource radio button is toggled and calls addPart method to add it to table
            if (outSourcedButton.isSelected()) {
                companyName = parttoggle.getText();
                Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                newOutsourcedPart.setId(Inventory.getNewPartId());
                Inventory.addNewPart(newOutsourcedPart);
                Successful = true;
            }
            // return to Main Screen if successful
            if (Successful) {
                returnToMainScreen(event);
            }
            // Catch edge cases
        } catch (Exception e) {
            Inventory display = new Inventory();
            display.errorMessage("Text filed missing or invalid entry");
        }
    }

    //    //This method is called if that returns to MainScreen
        void returnToMainScreen(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        }



    // Set toggle method between radio buttons
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleGroup = new ToggleGroup();
        inHouseButton.setToggleGroup(toggleGroup);
        outSourcedButton.setToggleGroup(toggleGroup);
        toggleGroup.selectToggle(inHouseButton);


    }





}


