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
        InHouse sample1 = new InHouse(setPartId, "Inhouse Chain", 10, 9,2,100,1022);
        sample1.setId(Inventory.getNewPartId());

        Outsourced sample2 = new Outsourced(setPartId, "Outsourced Pipes", 5, 19,2,35,"Steel");
        sample2.setId(Inventory.getNewPartId());

        Inventory.addNewPart(sample1);
        Inventory.addNewPart(sample2);


        int newProductId = 0;
        Product sample3 = new Product(newProductId, "Belts", 23.75, 22, 12, 95);
        sample3.setId(Inventory.getNewProductId());

        Product sample4 = new Product(newProductId, "fans",15.45,55,1,97);
        sample4.setId(Inventory.getNewProductId());

        Inventory.addProduct(sample3);
        Inventory.addProduct(sample4);

        launch();

    }
}

