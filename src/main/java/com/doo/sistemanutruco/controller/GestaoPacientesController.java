package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.sqlite.SqlitePacienteDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class GestaoPacientesController {

    @FXML
    private TableView<Paciente> pacientesTableView;

    @FXML
    private TableColumn<Paciente, String> nomeColumn;

    @FXML
    private TableColumn<Paciente, String> cpfColumn;

    @FXML
    private TableColumn<Paciente, String> emailColumn;

    @FXML
    private TableColumn<Paciente, Long> telefoneColumn;

    private ObservableList<Paciente> pacientes;

    public GestaoPacientesController() {
    }

    @FXML
    public void initialize() {
        SqlitePacienteDAO pacienteDAO = new SqlitePacienteDAO();
        List<Paciente> pacientesList = pacienteDAO.findAll();

        pacientes = FXCollections.observableArrayList(pacientesList);
        pacientesTableView.setItems(pacientes);

        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    }

    @FXML
    private void handleCadastrarPaciente() {
        loadWindow("/com/doo/sistemanutruco/view/CadastroPacienteView.fxml", "Cadastrar Paciente");
    }

    @FXML
    private void handleEditarPaciente() {
        // Implementar a navegação para a tela de edição de paciente
    }

    @FXML
    private void handleBuscarPaciente() {
        // Implementar a navegação para a tela de busca de paciente
    }

    @FXML
    private void handleAtivarDesativarPaciente() {
        // Implementar a ativação/desativação de um paciente selecionado
    }

    private void loadWindow(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
