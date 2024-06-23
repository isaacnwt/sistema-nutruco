package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.usecases.paciente.CadastrarPacienteUseCase;
import com.doo.sistemanutruco.repository.sqlite.SqlitePacienteDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class CadastroPacienteController {

    @FXML
    private TextField cpfTextField;
    @FXML
    private TextField nomeTextField;
    @FXML
    private DatePicker dataNascimentoDatePicker;
    @FXML
    private TextField telefoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField pesoTextField;
    @FXML
    private TextField alturaTextField;
    @FXML
    private TextField objetivoTextField;
    @FXML
    private TextField porcentagemGorduraTextField;
    @FXML
    private TextField porcentagemMassaMagraTextField;
    @FXML
    private TextField porcentagemMassaGordaTextField;
    @FXML
    private TextField colesterolLDLTextField;
    @FXML
    private TextField colesterolHDLTextField;
    @FXML
    private CheckBox hipertensoCheckBox;
    @FXML
    private CheckBox diabeticoCheckBox;
    @FXML
    private CheckBox celiacoCheckBox;
    @FXML
    private Label statusLabel;

    private final CadastrarPacienteUseCase cadastrarPacienteUseCase;

    public CadastroPacienteController() {
        this.cadastrarPacienteUseCase = new CadastrarPacienteUseCase(new SqlitePacienteDAO());
    }

    @FXML
    private void handleSalvarPaciente() {
        try {
            String cpf = cpfTextField.getText();
            String nome = nomeTextField.getText();
            LocalDate dataNascimento = dataNascimentoDatePicker.getValue();
            Long telefone = Long.parseLong(telefoneTextField.getText());
            String email = emailTextField.getText();
            Double peso = Double.parseDouble(pesoTextField.getText());
            Double altura = Double.parseDouble(alturaTextField.getText());
            String objetivo = objetivoTextField.getText();
            Integer porcentagemGordura = Integer.parseInt(porcentagemGorduraTextField.getText());
            Integer porcentagemMassaMagra = Integer.parseInt(porcentagemMassaMagraTextField.getText());
            Integer porcentagemMassaGorda = Integer.parseInt(porcentagemMassaGordaTextField.getText());
            Integer colesterolLDL = Integer.parseInt(colesterolLDLTextField.getText());
            Integer colesterolHDL = Integer.parseInt(colesterolHDLTextField.getText());
            boolean hipertenso = hipertensoCheckBox.isSelected();
            boolean diabetico = diabeticoCheckBox.isSelected();
            boolean celiaco = celiacoCheckBox.isSelected();

            Paciente paciente = new Paciente(cpf, nome, dataNascimento, telefone, email, peso, altura, objetivo, porcentagemGordura, porcentagemMassaMagra, porcentagemMassaGorda, colesterolLDL, colesterolHDL, hipertenso, diabetico, celiaco);

            cadastrarPacienteUseCase.cadastrar(paciente);
            statusLabel.setText("Paciente cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: Um dos campos numéricos possui valor inválido.");
        } catch (Exception e) {
            statusLabel.setText("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }
}
