package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.alimento.Alimento;
import com.doo.sistemanutruco.repository.sqlite.SqliteAlimentoDAO;
import com.doo.sistemanutruco.usecases.alimento.ImportarAlimentosUseCase;
import com.doo.sistemanutruco.usecases.alimento.ListarAlimentosUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

import static com.doo.sistemanutruco.controller.util.AlertUtil.showAlert;

public class ImportarAlimentosController {

    @FXML
    private TextField pathTextField;

    @FXML
    private TableView<Alimento> alimentosTableView;

    @FXML
    private TableColumn<Alimento, Integer> idColumn;

    @FXML
    private TableColumn<Alimento, String> nomeColumn;

    @FXML
    private TableColumn<Alimento, Double> caloriasColumn;

    @FXML
    private TableColumn<Alimento, Double> carboidratosColumn;

    @FXML
    private TableColumn<Alimento, Double> proteinasColumn;

    @FXML
    private TableColumn<Alimento, Double> sodioColumn;

    @FXML
    private TableColumn<Alimento, Double> gordurasColumn;

    @FXML
    private TableColumn<Alimento, Boolean> glutenColumn;

    @FXML
    private TableColumn<Alimento, Boolean> lactoseColumn;

    private ObservableList<Alimento> alimentosList;

    private final ImportarAlimentosUseCase importarAlimentosUseCase;
    private final ListarAlimentosUseCase listarAlimentosUseCase;

    public ImportarAlimentosController() {
        this.importarAlimentosUseCase = new ImportarAlimentosUseCase(new SqliteAlimentoDAO());
        this.listarAlimentosUseCase = new ListarAlimentosUseCase(new SqliteAlimentoDAO());
    }

    @FXML
    public void initialize() {
        alimentosList = FXCollections.observableArrayList();
        alimentosTableView.setItems(alimentosList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        caloriasColumn.setCellValueFactory(new PropertyValueFactory<>("calorias"));
        carboidratosColumn.setCellValueFactory(new PropertyValueFactory<>("carboidratos"));
        proteinasColumn.setCellValueFactory(new PropertyValueFactory<>("proteinas"));
        sodioColumn.setCellValueFactory(new PropertyValueFactory<>("sodio"));
        gordurasColumn.setCellValueFactory(new PropertyValueFactory<>("gorduras"));
        glutenColumn.setCellValueFactory(new PropertyValueFactory<>("gluten"));
        lactoseColumn.setCellValueFactory(new PropertyValueFactory<>("lactose"));

        loadAlimentosFromDatabase();
    }

    private void loadAlimentosFromDatabase() {
        List<Alimento> alimentos = listarAlimentosUseCase.listarAlimentos();
        alimentosList.setAll(alimentos);
    }

    @FXML
    private void handleSelecionarArquivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            pathTextField.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void handleImportarButtonAction() {
        String filePath = pathTextField.getText();

        if (filePath == null || filePath.isEmpty()) {
            showAlert("Atenção!", "Selecionar aquivo CSV", Alert.AlertType.WARNING);
            return;
        }

        importarAlimentosUseCase.importarAlimentosCSV(filePath);

        loadAlimentosFromDatabase();
        showAlert("Alimentos importados!","Alimentos salvos com sucesso!", Alert.AlertType.INFORMATION);
        close();
    }

    private void close(){
        Stage stage = (Stage) pathTextField.getScene().getWindow();
        stage.close();
    }
}