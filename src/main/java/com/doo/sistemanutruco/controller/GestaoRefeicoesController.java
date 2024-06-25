package com.doo.sistemanutruco.controller;

import com.doo.sistemanutruco.entities.refeicao.Refeicao;
import com.doo.sistemanutruco.repository.sqlite.SqliteRefeicaoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GestaoRefeicoesController {

    @FXML
    private TableView<Refeicao> refeicoesTableView;

    @FXML
    private TableColumn<Refeicao, String> nomeColumn;

    @FXML
    private TableColumn<Refeicao, String> descricaoColumn;

    @FXML
    private TableColumn<Refeicao, String> objetivoColumn;

    private ObservableList<Refeicao> refeicoesList;
    private final SqliteRefeicaoDAO refeicaoDAO = new SqliteRefeicaoDAO();

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        objetivoColumn.setCellValueFactory(new PropertyValueFactory<>("objetivo"));

        loadRefeicoes();
    }

    private void loadRefeicoes() {
        List<Refeicao> refeicoes = refeicaoDAO.findAll();
        refeicoesList = FXCollections.observableArrayList(refeicoes);
        refeicoesTableView.setItems(refeicoesList);
    }

    @FXML
    private void handleCadastrarRefeicao() {
        loadWindow("/com/doo/sistemanutruco/view/CadastroRefeicaoView.fxml", "Cadastrar Refeição");
    }

    @FXML
    private void handleEditarRefeicao() {
        Refeicao selectedRefeicao = refeicoesTableView.getSelectionModel().getSelectedItem();
        if (selectedRefeicao != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/doo/sistemanutruco/view/EditarRefeicaoView.fxml"));
                Parent parent = loader.load();

                EditarRefeicaoController controller = loader.getController();
                controller.setRefeicao(selectedRefeicao);
                controller.setGestaoRefeicoesController(this);
                controller.initialize();

                Stage stage = new Stage();
                stage.setTitle("Editar Refeição");
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Selecione uma refeição", "Por favor, selecione uma refeição para editar.");
        }
    }

    @FXML
    private void handleExcluirRefeicao() {
        Refeicao selectedRefeicao = refeicoesTableView.getSelectionModel().getSelectedItem();
        if (selectedRefeicao != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Excluir Refeição");
            alert.setContentText("Tem certeza que deseja excluir a refeição selecionada?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean deleted = refeicaoDAO.delete(selectedRefeicao);
                if (deleted) {
                    refeicoesList.remove(selectedRefeicao);
                    showAlert("Refeição excluída", "Refeição excluída com sucesso.");
                } else {
                    showAlert("Erro ao excluir", "Não foi possível excluir a refeição selecionada.");
                }
            }
        } else {
            showAlert("Nenhuma refeição selecionada", "Por favor, selecione uma refeição para excluir.");
        }
    }

    public void refreshTable() {
        refeicoesList.clear();
        loadRefeicoes();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadWindow(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent parent = loader.load();

            CadastroRefeicaoController controller = loader.getController();
            controller.setGestaoRefeicoesController(this);

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
