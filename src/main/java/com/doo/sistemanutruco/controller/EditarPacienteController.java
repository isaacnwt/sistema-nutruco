package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.repository.sqlite.SqlitePacienteDAO;
import com.doo.sistemanutruco.usecases.paciente.EditarPacienteUseCase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditarPacienteController {

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

    private final EditarPacienteUseCase editarPacienteUseCase;
    private Paciente paciente;
    private GestaoPacientesController gestaoPacientesController;

    public EditarPacienteController() {
        this.editarPacienteUseCase = new EditarPacienteUseCase(new SqlitePacienteDAO());
    }

    public void setGestaoPacientesController(GestaoPacientesController gestaoPacientesController) {
        this.gestaoPacientesController = gestaoPacientesController;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        preencherFormulario();
    }

    private void preencherFormulario() {
        cpfTextField.setText(paciente.getCpf());
        nomeTextField.setText(paciente.getNome());
        dataNascimentoDatePicker.setValue(paciente.getDataNascimento());
        telefoneTextField.setText(paciente.getTelefone().toString());
        emailTextField.setText(paciente.getEmail());
        pesoTextField.setText(paciente.getPeso().toString());
        alturaTextField.setText(paciente.getAltura().toString());
        objetivoTextField.setText(paciente.getObjetivo());
        porcentagemGorduraTextField.setText(paciente.getPorcentagemGordura().toString());
        porcentagemMassaMagraTextField.setText(paciente.getPorcentagemMassaMagra().toString());
        porcentagemMassaGordaTextField.setText(paciente.getPorcentagemMassaGorda().toString());
        colesterolLDLTextField.setText(paciente.getColesterolLDL().toString());
        colesterolHDLTextField.setText(paciente.getColesterolHDL().toString());
        hipertensoCheckBox.setSelected(paciente.getHipertenso());
        diabeticoCheckBox.setSelected(paciente.getDiabetico());
        celiacoCheckBox.setSelected(paciente.getCeliaco());
    }

    @FXML
    private void handleSalvarPaciente() {
        try {
            paciente.setNome(nomeTextField.getText());
            paciente.setDataNascimento(dataNascimentoDatePicker.getValue());
            paciente.setTelefone(Long.parseLong(telefoneTextField.getText()));
            paciente.setEmail(emailTextField.getText());
            paciente.setPeso(Double.parseDouble(pesoTextField.getText()));
            paciente.setAltura(Double.parseDouble(alturaTextField.getText()));
            paciente.setObjetivo(objetivoTextField.getText());
            paciente.setPorcentagemGordura(Integer.parseInt(porcentagemGorduraTextField.getText()));
            paciente.setPorcentagemMassaMagra(Integer.parseInt(porcentagemMassaMagraTextField.getText()));
            paciente.setPorcentagemMassaGorda(Integer.parseInt(porcentagemMassaGordaTextField.getText()));
            paciente.setColesterolLDL(Integer.parseInt(colesterolLDLTextField.getText()));
            paciente.setColesterolHDL(Integer.parseInt(colesterolHDLTextField.getText()));
            paciente.setHipertenso(hipertensoCheckBox.isSelected());
            paciente.setDiabetico(diabeticoCheckBox.isSelected());
            paciente.setCeliaco(celiacoCheckBox.isSelected());

            editarPacienteUseCase.editar(paciente);
            gestaoPacientesController.refreshTable();
            showAlert("Paciente Atualizado!","Paciente atualizado com sucesso!");
            close();
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: Um dos campos numéricos possui valor inválido.");
        } catch (Exception e) {
            statusLabel.setText("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleCancelar() {
        close();
    }

    private void close(){
        Stage stage = (Stage) nomeTextField.getScene().getWindow();
        stage.close();
    }
}
