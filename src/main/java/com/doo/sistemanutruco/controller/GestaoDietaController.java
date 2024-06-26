package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.repository.sqlite.SqliteDietaDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.stream;

public class GestaoDietaController {
    @FXML
    private TableView<Dieta> dietasTableView;
    @FXML
    private TableColumn<Dieta, String> nomeColumn;
    @FXML
    private TableColumn<Dieta, String> objetivoColumn;
    @FXML
    private TableColumn<Dieta, Double> caloriasTotaisColumn;
    @FXML
    private TableColumn<Dieta, Double> carboidratosTotaisColumn;
    @FXML
    private TableColumn<Dieta, Double> proteinasTotaisColumn;
    @FXML
    private TableColumn<Dieta, Double> gordurasTotaisColumn;
    @FXML
    private TableColumn<Dieta, Double> sodioTotalColumn;
    private final SqliteDietaDAO dietaDAO = new SqliteDietaDAO();
    private ObservableList<Dieta> dietas;

    public GestaoDietaController() {
    }

    @FXML
    public void initialize(){
        loadDietas();
        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        objetivoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObjetivo()));
        caloriasTotaisColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCaloriasDaDieta()));
        carboidratosTotaisColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCarboidratosDaDieta()));
        proteinasTotaisColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProteinasDaDieta()));
        gordurasTotaisColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGordurasDaDieta()));
        sodioTotalColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSodioDaDieta()));
    }

    @FXML
    private void handleConfigurarDieta() {
        openAtribuirDietaView();
    }

    public void refreshTable() {
        dietas.clear();
        loadDietas();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadDietas() {
        List<Dieta> dietasList = dietaDAO.findAll();
        dietas = FXCollections.observableArrayList(dietasList);
        dietasTableView.setItems(dietas);
    }

    private void openAtribuirDietaView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doo/sistemanutruco/view/ConfigurarDietaView.fxml"));
            Parent parent = loader.load();

            ConfigurarDietaController controller = loader.getController();
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
