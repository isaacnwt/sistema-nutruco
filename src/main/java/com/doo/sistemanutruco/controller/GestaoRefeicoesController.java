package com.doo.sistemanutruco.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GestaoRefeicoesController {

    @FXML
    private void handleCadastrarRefeicao() {
        loadWindow("/com/doo/sistemanutruco/view/CadastroRefeicaoView.fxml", "Cadastrar Refeição");
    }

    @FXML
    private void handleEditarRefeicao() {
        // Implementar lógica para editar refeição
    }

    @FXML
    private void handleExcluirRefeicao() {
        // Implementar lógica para excluir refeição
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
