package com.doo.sistemanutruco.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static java.util.Arrays.stream;

public class GestaoDietaController {
    public GestaoDietaController() {
    }

    @FXML
    private void handleAtribuirDieta() {
        openAtribuirDietaView();
    }

    private void openAtribuirDietaView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doo/sistemanutruco/view/AtribuirDietaView.fxml"));
            Parent parent = loader.load();

            AtribuirDietaController controller = loader.getController();
            controller.setGestaoDietaController(this);

            Stage stage = new Stage();
            stage.setTitle("Atribuir Dieta");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
