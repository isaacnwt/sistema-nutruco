package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.entities.alimento.Alimento;
import com.doo.sistemanutruco.repository.sqlite.SqliteAlimentoDAO;
import com.doo.sistemanutruco.repository.sqlite.SqliteRefeicaoDAO;
import com.doo.sistemanutruco.usecases.refeicao.CadastrarRefeicaoUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.util.List;

public class CadastroRefeicaoController {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField descricaoTextField;

    @FXML
    private TextField objetivoTextField;

    @FXML
    private ComboBox<Alimento> alimentosComboBox;

    @FXML
    private TableView<Alimento> alimentosTableView;

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

    @FXML
    private Label statusLabel;

    private ObservableList<Alimento> alimentosDisponiveis;
    private ObservableList<Alimento> alimentosSelecionados;

    private final CadastrarRefeicaoUseCase cadastrarRefeicaoUseCase;

    public CadastroRefeicaoController() {
        this.cadastrarRefeicaoUseCase = new CadastrarRefeicaoUseCase(new SqliteRefeicaoDAO());
    }

    @FXML
    public void initialize() {
        SqliteAlimentoDAO alimentoDAO = new SqliteAlimentoDAO();
        List<Alimento> alimentos = alimentoDAO.findAll();

        alimentosDisponiveis = FXCollections.observableArrayList(alimentos);
        alimentosSelecionados = FXCollections.observableArrayList();

        alimentosComboBox.setItems(alimentosDisponiveis);
        alimentosTableView.setItems(alimentosSelecionados);

        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        caloriasColumn.setCellValueFactory(new PropertyValueFactory<>("calorias"));
        carboidratosColumn.setCellValueFactory(new PropertyValueFactory<>("carboidratos"));
        proteinasColumn.setCellValueFactory(new PropertyValueFactory<>("proteinas"));
        sodioColumn.setCellValueFactory(new PropertyValueFactory<>("sodio"));
        gordurasColumn.setCellValueFactory(new PropertyValueFactory<>("gorduras"));
        glutenColumn.setCellValueFactory(new PropertyValueFactory<>("gluten"));
        lactoseColumn.setCellValueFactory(new PropertyValueFactory<>("lactose"));

        alimentosComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Alimento alimento) {
                return alimento != null ? alimento.getNome() : "";
            }

            @Override
            public Alimento fromString(String string) {
                return alimentosDisponiveis.stream()
                        .filter(alimento -> alimento.getNome().equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });
    }

    @FXML
    private void handleAdicionarAlimento() {
        Alimento selectedAlimento = alimentosComboBox.getSelectionModel().getSelectedItem();
        if (selectedAlimento != null && !alimentosSelecionados.contains(selectedAlimento)) {
            alimentosSelecionados.add(selectedAlimento);
        }
    }

    @FXML
    private void handleSalvarRefeicao() {
        String nome = nomeTextField.getText();
        String descricao = descricaoTextField.getText();
        String objetivo = objetivoTextField.getText();

        Refeicao refeicao = new Refeicao(nome, descricao, objetivo, alimentosSelecionados);

        try {
            cadastrarRefeicaoUseCase.cadastrar(refeicao);
            statusLabel.setText("Refeição cadastrada com sucesso!");
        } catch (Exception e) {
            statusLabel.setText(e.getMessage());
        }
    }
}