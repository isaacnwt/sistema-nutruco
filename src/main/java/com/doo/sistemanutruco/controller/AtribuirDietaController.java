package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.dia.Dia;
import com.doo.sistemanutruco.entities.dieta.Dieta;
import com.doo.sistemanutruco.entities.paciente.Paciente;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.repository.sqlite.SqliteDiaDAO;
import com.doo.sistemanutruco.repository.sqlite.SqliteDietaDAO;
import com.doo.sistemanutruco.repository.sqlite.SqlitePacienteDAO;
import com.doo.sistemanutruco.repository.sqlite.SqliteRefeicaoDAO;
import com.doo.sistemanutruco.usecases.dia.CadastrarDiaUseCase;
import com.doo.sistemanutruco.usecases.dieta.AtivarDietaUseCase;
import com.doo.sistemanutruco.usecases.dieta.AtribuirDietaUseCase;
import com.doo.sistemanutruco.usecases.dieta.BuscarDietasDoPacienteUseCase;
import com.doo.sistemanutruco.usecases.dieta.RemoverDietaUseCase;
import com.doo.sistemanutruco.usecases.refeicao.AtribuirRefeicoesUseCase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.doo.sistemanutruco.controller.util.AlertUtil.showAlert;
import static java.util.Arrays.stream;

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
    private TableView<Dieta> dietasTableView;

    @FXML
    private TableColumn<Dieta, String> nomeDietaColumn;
    @FXML
    private TableColumn<Dieta, String> objetivoDietaColumn;

    @FXML
    private TableColumn<Dieta, LocalDate> dataInicioColumn;

    @FXML
    private TableColumn<Dieta, LocalDate> dataFimColumn;

    @FXML
    private TableColumn<Dieta, Boolean> ativaColumn;

    @FXML
    private ComboBox<Refeicao> refeicoesComboBox;

    @FXML
    private TableView<Refeicao> refeicoesTableView;

    @FXML
    private TableColumn<Refeicao, String> nomeColumn;

    public TableColumn<Refeicao, String>  descricaoColumn;
    public TableColumn<Refeicao, Double>  caloriasTotaisColumn;
    public TableColumn<Refeicao, Double>  carboidratosTotaisColumn;
    public TableColumn<Refeicao, Double>  proteinasTotaisColumn;
    public TableColumn<Refeicao, Double>  gordurasTotaisColumn;
    public TableColumn<Refeicao, Double> sodioTotalColumn;
    public TableColumn<Refeicao, Boolean>  contemLactoseColumn;
    public TableColumn<Refeicao, Boolean>  contemGlutenColumn;
    public TableColumn<Refeicao, String>  objetivoRefeicaoColumn;


    private final AtribuirDietaUseCase atribuirDietaUseCase;
    private final CadastrarDiaUseCase cadastrarDiaUseCase;
    private final AtribuirRefeicoesUseCase atribuirRefeicoesUseCase;
    private final BuscarDietasDoPacienteUseCase buscarDietasDoPacienteUseCase;
    private final AtivarDietaUseCase ativarDietaUseCase;
    private final RemoverDietaUseCase removerDietaUseCase;
    private GestaoDietaController gestaoDietaController;

    private List<Refeicao> refeicoesSelecionadas;

    public AtribuirDietaController() {
        this.atribuirDietaUseCase = new AtribuirDietaUseCase(new SqlitePacienteDAO(), new SqliteDietaDAO());
        this.cadastrarDiaUseCase = new CadastrarDiaUseCase(new SqliteDiaDAO(), new SqliteDietaDAO());
        this.atribuirRefeicoesUseCase = new AtribuirRefeicoesUseCase(new SqliteDiaDAO());
        this.buscarDietasDoPacienteUseCase = new BuscarDietasDoPacienteUseCase(new SqliteDietaDAO());
        this.ativarDietaUseCase = new AtivarDietaUseCase(new SqliteDietaDAO());
        this.removerDietaUseCase = new RemoverDietaUseCase(new SqliteDietaDAO());
    }

    public void setGestaoDietaController(GestaoDietaController gestaoDietaController) {
        this.gestaoDietaController = gestaoDietaController;
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

        List<Refeicao> refeicoes = new SqliteRefeicaoDAO().findAll();
        refeicoesComboBox.setItems(FXCollections.observableArrayList(refeicoes));
        refeicoesComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Refeicao refeicao) {
                return refeicao != null ? refeicao.getNome() : "";
            }

            @Override
            public Refeicao fromString(String string) {
                return refeicoes.stream()
                        .filter(refeicao -> refeicao.getNome().equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });

        nomeDietaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        objetivoDietaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObjetivo()));
        dataInicioColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDataInicio()));
        dataFimColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDataFim()));
        ativaColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isAtiva()));

        refeicoesSelecionadas = new ArrayList<>();
        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        objetivoRefeicaoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObjetivo()));
        descricaoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        caloriasTotaisColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCaloriasTotais()));
        carboidratosTotaisColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCarboidratosTotais()));
        proteinasTotaisColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProteinasTotais()));
        gordurasTotaisColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getGordurasTotais()));
        sodioTotalColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSodioTotal()));
        contemLactoseColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getContemLactose()));
        contemGlutenColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getContemGluten()));
        refeicoesTableView.setItems(FXCollections.observableArrayList(refeicoesSelecionadas));
    }


    @FXML
    private void handlePacienteSelecionado() {
        Paciente paciente = pacientesComboBox.getValue();
        if (paciente != null) {
            List<Dieta> dietas = buscarDietasDoPacienteUseCase.buscarDietas(paciente);
            dietasTableView.setItems(FXCollections.observableArrayList(dietas));
        }
    }

    @FXML
    private void handleAdicionarRefeicao() {
        Refeicao refeicao = refeicoesComboBox.getValue();
        if (refeicao != null) {
            if (refeicoesSelecionadas.contains(refeicao)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Refeição Já Adicionada!");
                alert.setHeaderText(null);
                alert.setContentText("Esta refeição já foi adicionada à lista.");
                alert.showAndWait();
            } else {
                refeicoesSelecionadas.add(refeicao);
                refeicoesTableView.setItems(FXCollections.observableArrayList(refeicoesSelecionadas));
            }
        }
    }

    @FXML
    private void handleRemoverRefeicao() {
        Refeicao refeicao = refeicoesTableView.getSelectionModel().getSelectedItem();
        if (refeicao != null) {
            refeicoesSelecionadas.remove(refeicao);
            refeicoesTableView.setItems(FXCollections.observableArrayList(refeicoesSelecionadas));
        }
    }
    @FXML
    private void handleAlterarStatusDieta() {
        Dieta dieta = dietasTableView.getSelectionModel().getSelectedItem();
        if (dieta != null) {
            try {
                if (dieta.isAtiva()) {
                    ativarDietaUseCase.inativar(dieta);
                    showAlert("Status da Dieta Alterado", "Dieta desativada com sucesso!", Alert.AlertType.INFORMATION);
                } else {
                    ativarDietaUseCase.ativar(dieta);
                    showAlert("Status da Dieta Alterado", "Dieta ativada com sucesso!", Alert.AlertType.INFORMATION);
                }
                dietasTableView.refresh();
            } catch (Exception e) {
                showAlert("Atenção!", e.getMessage(), Alert.AlertType.WARNING);
            }
        } else {
            showAlert("Atenção!", "Nenhuma dieta selecionada!", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void handleRemoverDieta() {
        Dieta dieta = dietasTableView.getSelectionModel().getSelectedItem();
        Paciente paciente = pacientesComboBox.getValue();
        if (dieta != null && paciente != null) {
            try {
                removerDietaUseCase.removerDietaDoPaciente(paciente.getCpf(), dieta.getId());
                showAlert("Dieta Removida", "Dieta removida do paciente com sucesso!", Alert.AlertType.INFORMATION);
                dietasTableView.getItems().remove(dieta);
            } catch (Exception e) {
                showAlert("Atenção!", e.getMessage(), Alert.AlertType.WARNING);
            }
        } else {
            showAlert("Atenção!", "Selecione um paciente e uma dieta!", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void handleAtribuirDieta() {
        try {
            Paciente paciente = pacientesComboBox.getValue();
            Dieta dieta = getDieta();

            Integer dietaId = atribuirDietaUseCase.atribuirDieta(paciente, dieta);
            List<Dia> diasDaDieta = cadastrarDiaUseCase.cadastrarDiasDaDieta(dietaId, refeicoesSelecionadas);
            atribuirRefeicoesUseCase.atribuirRefeicoesDosDias(diasDaDieta);

            List<Dieta> dietas = buscarDietasDoPacienteUseCase.buscarDietas(paciente);
            dietasTableView.setItems(FXCollections.observableArrayList(dietas));

            showAlert("Dieta cadastrada!", "Dieta cadastrada com sucesso!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Atenção!", e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private Dieta getDieta() {
        String nomeDieta = nomeDietaTextField.getText();
        String objetivoDieta = objetivoDietaTextField.getText();
        LocalDate dataInicio = dataInicioDatePicker.getValue();
        LocalDate dataFim = dataFimDatePicker.getValue();
        List<Dia> dias = stream(DayOfWeek.values()).map(diaDaSemana -> new Dia(diaDaSemana, refeicoesSelecionadas)).toList();

        return new Dieta(nomeDieta, objetivoDieta, dias, dataInicio, dataFim);
    }

    private void close(){
        Stage stage = (Stage) nomeDietaTextField.getScene().getWindow();
        stage.close();
    }
}
