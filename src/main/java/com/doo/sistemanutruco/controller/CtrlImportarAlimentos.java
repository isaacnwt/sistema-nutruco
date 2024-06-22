package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.repository.memory.InMemoryAlimentoDAO;
import com.doo.sistemanutruco.usecases.alimento.AlimentoDAO;
import com.doo.sistemanutruco.usecases.alimento.ImportarAlimentosUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;

public class CtrlImportarAlimentos {
    @FXML
    private TextField pathTextField;

    @FXML
    private Label statusLabel;

    private ImportarAlimentosUseCase importarAlimentosUseCase;

    public CtrlImportarAlimentos() {
        AlimentoDAO alimentoDAO = new InMemoryAlimentoDAO();
        this.importarAlimentosUseCase = new ImportarAlimentosUseCase(alimentoDAO);
    }

    @FXML
    private void handleImportarButtonAction() {
        String path = pathTextField.getText();
        File file = new File(path);
        if (file.exists() && !file.isDirectory()) {
            importarAlimentosUseCase.importarAlimentosCSV(path);
            statusLabel.setText("Importação concluída com sucesso!");
        } else {
            statusLabel.setText("Arquivo não encontrado.");
        }
    }
}
