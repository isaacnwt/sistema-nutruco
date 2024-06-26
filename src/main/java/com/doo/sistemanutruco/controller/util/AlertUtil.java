package com.doo.sistemanutruco.controller.util;

import javafx.scene.control.Alert;

public class AlertUtil {
    public static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
