package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.alimento.Alimento;
import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.sqlite.SqliteDiaDAO;
import com.doo.sistemanutruco.repository.sqlite.SqliteDietaDAO;
import com.doo.sistemanutruco.repository.sqlite.SqlitePacienteDAO;
import com.doo.sistemanutruco.repository.sqlite.SqliteRefeicaoDAO;
import com.doo.sistemanutruco.usecases.dieta.AtribuirDietaUseCase;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.List;

public class AtribuirDietaController {

    @FXML
    private ComboBox<Paciente> pacientesComboBox;

    @FXML
    private TextField nomeDietaTextField;

    @FXML
    private TextField objetivoDietaTextField;

    @FXML
    private DatePicker dataInicioDatePicker;

    @FXML
    private DatePicker dataFimDatePicker;

    @FXML
    private Label statusLabel;

    private final AtribuirDietaUseCase atribuirDietaUseCase;

    public AtribuirDietaController() {
        this.atribuirDietaUseCase = new AtribuirDietaUseCase(
                new SqlitePacienteDAO(), new SqliteDietaDAO(), new SqliteDiaDAO(), new SqliteRefeicaoDAO());
    }

    @FXML
    public void initialize() {
        List<Paciente> pacientes = new SqlitePacienteDAO().findAll();
        pacientesComboBox.setItems(FXCollections.observableArrayList(pacientes));
        pacientesComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Paciente paciente) {
                return paciente != null ? paciente.getNome() : "";
            }

            @Override
            public Paciente fromString(String string) {
                return pacientes.stream()
                        .filter(paciente -> paciente.getNome().equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });
    }

    @FXML
    private void handleAtribuirDieta() {
        try {
            Paciente paciente = pacientesComboBox.getValue();
            String nomeDieta = nomeDietaTextField.getText();
            String objetivoDieta = objetivoDietaTextField.getText();
            LocalDate dataInicio = dataInicioDatePicker.getValue();
            LocalDate dataFim = dataFimDatePicker.getValue();

            Dieta dieta = new Dieta(nomeDieta, objetivoDieta, List.of(), dataInicio, dataFim);
            atribuirDietaUseCase.atribuirDieta(paciente, dieta);

            statusLabel.setText("Dieta atribuída com sucesso!");
        } catch (Exception e) {
            statusLabel.setText(e.getMessage());
        }
    }
}
