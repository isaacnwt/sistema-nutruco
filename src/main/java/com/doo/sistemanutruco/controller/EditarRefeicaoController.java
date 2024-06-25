package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.alimento.Alimento;
import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.repository.sqlite.SqliteAlimentoDAO;
import com.doo.sistemanutruco.repository.sqlite.SqliteRefeicaoDAO;
import com.doo.sistemanutruco.usecases.refeicao.EditarRefeicaoUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.util.List;

public class EditarRefeicaoController {

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
    private Refeicao refeicao;
    private final EditarRefeicaoUseCase editarRefeicaoUseCase;
    private GestaoRefeicoesController gestaoRefeicoesController;

    public EditarRefeicaoController() {
        this.editarRefeicaoUseCase = new EditarRefeicaoUseCase(new SqliteRefeicaoDAO());
    }

    public void setGestaoRefeicoesController(GestaoRefeicoesController gestaoRefeicoesController) {
        this.gestaoRefeicoesController = gestaoRefeicoesController;
    }

    @FXML
    public void initialize() {
        List<Alimento> alimentos = new SqliteAlimentoDAO().findAll();
        alimentosDisponiveis = FXCollections.observableArrayList(alimentos);
        alimentosSelecionados = FXCollections.observableArrayList();

        alimentosComboBox.setItems(alimentosDisponiveis);
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

        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        caloriasColumn.setCellValueFactory(new PropertyValueFactory<>("calorias"));
        carboidratosColumn.setCellValueFactory(new PropertyValueFactory<>("carboidratos"));
        proteinasColumn.setCellValueFactory(new PropertyValueFactory<>("proteinas"));
        sodioColumn.setCellValueFactory(new PropertyValueFactory<>("sodio"));
        gordurasColumn.setCellValueFactory(new PropertyValueFactory<>("gorduras"));
        glutenColumn.setCellValueFactory(new PropertyValueFactory<>("gluten"));
        lactoseColumn.setCellValueFactory(new PropertyValueFactory<>("lactose"));

        carregarDadosRefeicao();
    }

    private void carregarDadosRefeicao() {
        if (refeicao != null) {
            nomeTextField.setText(refeicao.getNome());
            descricaoTextField.setText(refeicao.getDescricao());
            objetivoTextField.setText(refeicao.getObjetivo());

            // Inicializa alimentosSelecionados se necessário
            if (alimentosSelecionados == null) {
                alimentosSelecionados = FXCollections.observableArrayList();
            }

            alimentosSelecionados.clear();

            if (refeicao.getAlimentos() != null) {
                alimentosSelecionados.addAll(refeicao.getAlimentos());
            }

            alimentosTableView.setItems(alimentosSelecionados);
        }
    }



    @FXML
    private void handleSalvarRefeicao() {
        try {
            refeicao.setNome(nomeTextField.getText());
            refeicao.setDescricao(descricaoTextField.getText());
            refeicao.setObjetivo(objetivoTextField.getText());
            refeicao.setAlimentos(alimentosSelecionados);

            editarRefeicaoUseCase.editar(refeicao);
            statusLabel.setText("Refeição atualizada com sucesso!");
            // gestaoRefeicoesController.refreshTable();

        } catch (Exception e) {
            statusLabel.setText(e.getMessage());
        }
    }

    @FXML
    private void handleAdicionarAlimento() {
        Alimento selectedAlimento = alimentosComboBox.getSelectionModel().getSelectedItem();
        if (selectedAlimento != null && !alimentosSelecionados.contains(selectedAlimento)) {
            alimentosSelecionados.add(selectedAlimento);
            alimentosTableView.setItems(alimentosSelecionados); // Atualiza a tabela ao adicionar um alimento
        }
    }

    @FXML
    private void handleRemoverAlimento() {
        Alimento selectedAlimento = alimentosTableView.getSelectionModel().getSelectedItem();
        if (selectedAlimento != null) {
            alimentosSelecionados.remove(selectedAlimento);
            alimentosTableView.setItems(alimentosSelecionados); // Atualiza a tabela ao remover um alimento
        }
    }

    public void setRefeicao(Refeicao refeicao) {
        this.refeicao = refeicao;
        carregarDadosRefeicao();
    }
}
