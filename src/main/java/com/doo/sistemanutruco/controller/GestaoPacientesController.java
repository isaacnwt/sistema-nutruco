package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.sqlite.SqlitePacienteDAO;
import com.doo.sistemanutruco.usecases.paciente.AtivarPacienteUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GestaoPacientesController {

    @FXML
    private TableView<Paciente> pacientesTableView;

    @FXML
    private TableColumn<Paciente, String> cpfColumn;

    @FXML
    private TableColumn<Paciente, String> nomeColumn;

    @FXML
    private TableColumn<Paciente, Boolean> statusAtivoColumn;

    private ObservableList<Paciente> pacientes;

    private final SqlitePacienteDAO pacienteDAO = new SqlitePacienteDAO();
    private final AtivarPacienteUseCase ativarPacienteUseCase = new AtivarPacienteUseCase(pacienteDAO);

    public GestaoPacientesController() {
    }

    @FXML
    public void initialize() {
        loadPacientes();
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        statusAtivoColumn.setCellValueFactory(new PropertyValueFactory<>("inativo"));
    }

    private void loadPacientes() {
        List<Paciente> pacientesList = pacienteDAO.findAll();
        pacientes = FXCollections.observableArrayList(pacientesList);
        pacientesTableView.setItems(pacientes);
    }

    @FXML
    private void handleCadastrarPaciente() {
        openCadastroPacienteView();
    }

    @FXML
    private void handleEditarPaciente() {
            Paciente selectedPaciente = pacientesTableView.getSelectionModel().getSelectedItem();
            if (selectedPaciente != null) {
                openEditarPacienteView(selectedPaciente);
            } else {
                showAlert("Por favor, selecione um paciente para editar.");
            }
    }

    @FXML
    private void handleDesativarPaciente() {
        Paciente selectedPaciente = pacientesTableView.getSelectionModel().getSelectedItem();
        if (selectedPaciente != null) {
            try {
                ativarPacienteUseCase.inativar(selectedPaciente);
                refreshTable();
                showAlert("Paciente desativado com sucesso!");
            } catch (IllegalStateException e) {
                showAlert("Erro: " + e.getMessage());
            }
        } else {
            showAlert("Por favor, selecione um paciente para desativar.");
        }
    }

    private void openCadastroPacienteView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doo/sistemanutruco/view/CadastroPacienteView.fxml"));
            Parent parent = loader.load();

            CadastroPacienteController controller = loader.getController();
            controller.setGestaoPacientesController(this);

            Stage stage = new Stage();
            stage.setTitle("Cadastrar Paciente");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openEditarPacienteView(Paciente paciente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doo/sistemanutruco/view/EditarPacienteView.fxml"));
            Parent parent = loader.load();

            EditarPacienteController controller = loader.getController();
            controller.setPaciente(paciente);
            controller.setGestaoPacientesController(this);

            Stage stage = new Stage();
            stage.setTitle("Editar Paciente");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshTable() {
        pacientes.clear();
        loadPacientes();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
