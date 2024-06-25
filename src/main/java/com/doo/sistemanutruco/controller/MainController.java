package com.doo.sistemanutruco.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private void handleGestaoPacientes() {
        loadWindow("/com/doo/sistemanutruco/view/GestaoPacientesView.fxml", "Gestão de Pacientes");
    }

    @FXML
    private void handleAtribuirDieta() {
        loadWindow("/com/doo/sistemanutruco/view/AtribuirDietaView.fxml", "Atribuir Dieta");
    }

    @FXML
    private void handleGestaoRefeicoes() {
        loadWindow("/com/doo/sistemanutruco/view/GestaoRefeicoesView.fxml", "Gestão de Refeições");
    }

    @FXML
    private void handleImportarAlimentos() {
        loadWindow("/com/doo/sistemanutruco/view/ImportarAlimentosView.fxml", "Importar Alimentos");
    }

    private void loadWindow(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
