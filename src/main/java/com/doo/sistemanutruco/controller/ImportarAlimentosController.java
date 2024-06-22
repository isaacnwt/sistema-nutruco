package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.repository.sqlite.SqliteAlimentoDAO;
import com.doo.sistemanutruco.usecases.alimento.ImportarAlimentosUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImportarAlimentosController {
    @FXML
    private TextField pathTextField;

    @FXML
    private Label statusLabel;

    private ImportarAlimentosUseCase importarAlimentosUseCase;

    public ImportarAlimentosController() {
        this.importarAlimentosUseCase = new ImportarAlimentosUseCase(new SqliteAlimentoDAO());
    }

    @FXML
    private void handleSelecionarArquivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            pathTextField.setText(selectedFile.getAbsolutePath());
        }
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
