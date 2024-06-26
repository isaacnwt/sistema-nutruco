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
import com.doo.sistemanutruco.usecases.dieta.AtribuirDietaUseCase;
import com.doo.sistemanutruco.usecases.refeicao.AtribuirRefeicoesUseCase;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import javafx.beans.property.SimpleStringProperty;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private ComboBox<Refeicao> refeicoesComboBox;

    @FXML
    private TableView<Refeicao> refeicoesTableView;

    @FXML
    private TableColumn<Refeicao, String> nomeColumn;

    @FXML
    private Label statusLabel;

    private final AtribuirDietaUseCase atribuirDietaUseCase;
    private final CadastrarDiaUseCase cadastrarDiaUseCase;
    private final AtribuirRefeicoesUseCase atribuirRefeicoesUseCase;

    private List<Refeicao> refeicoesSelecionadas;

    public AtribuirDietaController() {
        this.atribuirDietaUseCase = new AtribuirDietaUseCase(new SqlitePacienteDAO(), new SqliteDietaDAO());
        this.cadastrarDiaUseCase = new CadastrarDiaUseCase(new SqliteDiaDAO(), new SqliteDietaDAO());
        this.atribuirRefeicoesUseCase = new AtribuirRefeicoesUseCase(new SqliteDiaDAO());
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

        refeicoesSelecionadas = new ArrayList<>();
        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        refeicoesTableView.setItems(FXCollections.observableArrayList(refeicoesSelecionadas));
    }

    @FXML
    private void handleAdicionarRefeicao() {
        Refeicao refeicao = refeicoesComboBox.getValue();
        if (refeicao != null) {
            refeicoesSelecionadas.add(refeicao);
            refeicoesTableView.setItems(FXCollections.observableArrayList(refeicoesSelecionadas));
        }
    }

    @FXML
    private void handleAtribuirDieta() {
        try {
            Paciente paciente = pacientesComboBox.getValue();
            Dieta dieta = getDieta();

            Integer dietaId = atribuirDietaUseCase.atribuirDieta(paciente, dieta);
            List<Dia> diasDaDieta = cadastrarDiaUseCase.cadastrarDiasDaDieta(dietaId);
            atribuirRefeicoesUseCase.atribuirRefeicoesDosDias(diasDaDieta);

            statusLabel.setText("Dieta atribuída com sucesso!");
        } catch (Exception e) {
            statusLabel.setText(e.getMessage());
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
}
