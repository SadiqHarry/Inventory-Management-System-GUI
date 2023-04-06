module main.project {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.project to javafx.fxml;
    exports main.project;
}