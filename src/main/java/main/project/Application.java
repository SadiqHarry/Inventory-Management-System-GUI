package main.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        int setPartId = 0;
        InHouse sample1 = new InHouse(setPartId, "Chains", 10, 9,2,2,0);
        sample1.setId(Inventory.getNewPartId());

        Outsourced sample2 = new Outsourced(setPartId, "Pipes", 5, 19,2,2,"aim");
        sample2.setId(Inventory.getNewPartId());

        Inventory.addNewPart(sample1);
        Inventory.addNewPart(sample2);

        launch();

    }
}

