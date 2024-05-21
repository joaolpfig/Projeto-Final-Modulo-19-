package com.example.projeto_final.projeto_final_gestao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Principal {
        @FXML
        private BorderPane borderPane;

        public void menuExitApplication(ActionEvent actionEvent) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sair da aplicação");
            alert.setHeaderText("Deseja mesmo sair da apliação?");
            // Adiciona botões personalizados em português
            ButtonType botaoSim = new ButtonType("Sim");
            ButtonType botaoNao = new ButtonType("Não");
            alert.getButtonTypes().setAll(botaoSim, botaoNao);

            alert.showAndWait().ifPresent(response -> {
                if (response == botaoSim) {
                    Settings.getPrimaryStage().close();
                }
            });
        }

        public void menuAbout(ActionEvent actionEvent) throws IOException {
            // Aquisição do controlo da cena (Scene) about FXML
            Parent scene = FXMLLoader.load(getClass().getResource("about.fxml"));
            //Nova janela (Stage)
            Stage about = new Stage();
            //Definições da Stage
            about.setTitle("Acerca de");
            about.setResizable(false);

            // Associação da Scene à Stage
            about.setScene(new Scene(scene));

            // Abertura da janela About em modo MODAL, em relação à primaryStage
            about.initOwner(Settings.getPrimaryStage());
            about.initModality(Modality.WINDOW_MODAL);

            //Abertura da janela About
            about.show();
        }

        public void menuClienteList(ActionEvent actionEvent) throws Exception {
            Parent scene = FXMLLoader.load(getClass().getResource("clienteList.fxml"));

            // Atribuição da Scene à zona central da cena Principal, que é um BorderPane
            borderPane.setCenter(scene);
        }


        public void menuProdutoList(ActionEvent actionEvent) throws IOException {
            Parent scene = FXMLLoader.load(getClass().getResource("produtosList.fxml"));

            // Atribuição da Scene à zona central da cena Principal, que é um BorderPane
            borderPane.setCenter(scene);
        }

        public void menuVendasList(ActionEvent actionEvent) throws IOException {
            Parent scene = FXMLLoader.load(getClass().getResource("vendasList.fxml"));

            // Atribuição da Scene à zona central da cena Principal, que é um BorderPane
            borderPane.setCenter(scene);
        }

    public void menuCategoriaList(ActionEvent actionEvent) throws IOException {
        Parent scene = FXMLLoader.load(getClass().getResource("categoriaList.fxml"));

        // Atribuição da Scene à zona central da cena Principal, que é um BorderPane
        borderPane.setCenter(scene);
    }
    }

