module com.doo.sistemanutruco {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.jetbrains.annotations;

    exports com.doo.sistemanutruco;

    opens com.doo.sistemanutruco.view to javafx.fxml;
    opens com.doo.sistemanutruco.loaders to javafx.graphics;
    opens com.doo.sistemanutruco.controller to javafx.fxml;
}