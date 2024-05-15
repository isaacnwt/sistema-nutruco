module com.doo.sistemanutruco {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.doo.sistemanutruco to javafx.fxml;
    exports com.doo.sistemanutruco;
}