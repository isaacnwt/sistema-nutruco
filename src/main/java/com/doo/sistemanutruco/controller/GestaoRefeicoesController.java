package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.repository.sqlite.SqliteRefeicaoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GestaoRefeicoesController {

    @FXML
    private TableView<Refeicao> refeicoesTableView;

    @FXML
    private TableColumn<Refeicao, String> nomeColumn;

    @FXML
    private TableColumn<Refeicao, String> descricaoColumn;

    @FXML
    private TableColumn<Refeicao, String> objetivoColumn;

    private ObservableList<Refeicao> refeicoes;

    public GestaoRefeicoesController() {
    }

    @FXML
    public void initialize() {
        SqliteRefeicaoDAO refeicaoDAO = new SqliteRefeicaoDAO();
        List<Refeicao> refeicoesList = refeicaoDAO.findAll();

        refeicoes = FXCollections.observableArrayList(refeicoesList);
        refeicoesTableView.setItems(refeicoes);

        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        objetivoColumn.setCellValueFactory(new PropertyValueFactory<>("objetivo"));
    }

    @FXML
    private void handleCadastrarRefeicao() {
        loadWindow("/com/doo/sistemanutruco/view/CadastroRefeicaoView.fxml", "Cadastrar Refeição");
    }

    @FXML
    private void handleEditarRefeicao() {
        // Implementar a navegação para a tela de edição de refeição
    }

    @FXML
    private void handleExcluirRefeicao() {
        // Implementar a exclusão de uma refeição selecionada
    }

    private void loadWindow(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}