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

        Part partSelected = MainController.getPartsModified();
        
        boolean successful = false;
        if (partSelected instanceof InHouse) {
            parttoggle.setText(String.valueOf(((InHouse) partSelected).getMachineId()));
            successful = true;
        }


        if (partSelected instanceof Outsourced){
            parttoggle.setText(((Outsourced) partSelected).getCompanyName());
        }


        // Set toggle method between radio buttons
        toggleGroup = new ToggleGroup();
        inHouseButton.setToggleGroup(toggleGroup);
        outSourcedButton.setToggleGroup(toggleGroup);

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
