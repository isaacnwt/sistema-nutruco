package com.doo.sistemanutruco.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class MainController {
    @FXML
    private void handleAlimentos() {
        loadWindow("/com/doo/sistemanutruco/view/ImportarAlimentosView.fxml", "Importar Alimentos");
    }

    @FXML
    private void handleRefeicoes() {
        loadWindow("/com/doo/sistemanutruco/view/GestaoRefeicoesView.fxml", "Gestão de Refeições");
    }

    @FXML
    private void handleDias() {
        showAlert("Dias");
    }

    @FXML
    private void handleDietas() {
        showAlert("Dietas");
    }

    @FXML
    private void handlePacientes() {
        loadWindow("/com/doo/sistemanutruco/view/GestaoPacientesView.fxml", "Gestão de Pacientes");
    }

    private void showAlert(String category) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText("Categoria " + category + " ainda não implementada.");
        alert.showAndWait();
    }

    private void loadWindow(String path, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

