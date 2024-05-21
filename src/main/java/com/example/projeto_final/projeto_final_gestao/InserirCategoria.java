package com.example.projeto_final.projeto_final_gestao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InserirCategoria implements Initializable {

    @FXML
    private Button btnAction;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lbl_Id;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtCor;


    @FXML
    private Stage thisStage;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Se flag Action não está definida => notifica e termina
        if(Settings.ACTION == -1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sair da aplicação");
            alert.setHeaderText("A flag Action não está definida");
            alert.show();
        }
        //Preparação da cena
        switch (Settings.ACTION){
            case Settings.ACTION_INSERT:
                // Altera o texto do título e do botão Action
                // Os campos são apresentados abertos e vazios
                lblTitle.setText("Inserção da Categoria");
                btnAction.setText("Inserir");
                break;
            case Settings.ACTION_UPDATE:
                // Altera o texto do título e do botão Action
                lblTitle.setText("Alteração da Categoria");
                btnAction.setText("Alterar");

                //Campo referente ao número de processo deve estar disable
                //Preencher os campos com os dados do objeto Aluno
                //que pretendemos editar/ atualizar
                lbl_Id.setText(String.valueOf(Settings.getCategoriaEdit().getIdCategoria()));
                txtCategoria.setText(Settings.getCategoriaEdit().getCategoria());
                txtCor.setText(Settings.getCategoriaEdit().getCor());
                break;
            case Settings.ACTION_DELETE:
                // Altera o texto do título e do botão Action
                lblTitle.setText("Eliminação da Categoria");
                btnAction.setText("Eliminar");

                //Preencher os campos com os dados do objeto Aluno
                //que pretendemos eliminar
                lbl_Id.setText(String.valueOf(Settings.getCategoriaEdit().getIdCategoria()));
                txtCategoria.setText(Settings.getCategoriaEdit().getCategoria());
                txtCor.setText(Settings.getCategoriaEdit().getCor());
                //Campos que devem estar disable
                txtCategoria.setDisable(true);
                txtCor.setDisable(true);
                break;
        }

    }


    public void ButtonAction(ActionEvent actionEvent) {
        switch (Settings.ACTION) {
            case Settings.ACTION_INSERT:
                // Recolha dos dados existentes nos Controlos (objetos gráficos da janela)
                // para um novo objeto.
                lbl_Id.setDisable(true);
                String Categoria = txtCategoria.getText();
                String Cor = txtCor.getText();
                Categoria newCategoria = new Categoria(Categoria, Cor);

                int idCategoira = CategoriaDAO.adicionarCategoria(newCategoria);
                newCategoria.setIdCategoria(idCategoira);
                //Adiciona o novo Aluno à TableView
                Settings.getListCategoria().add(newCategoria);
                break;
            case Settings.ACTION_UPDATE:
                //Recolha dos dados existentes nos Controlos (objetos gráficos da janela)
                // para o mesmo objeto.
                Settings.getCategoriaEdit().setCategoria(txtCategoria.getText());
                Settings.getCategoriaEdit().setCor(txtCor.getText());

                CategoriaDAO.atualizarCategoria(Settings.getCategoriaEdit());
                int position = Settings.getListCategoria().indexOf(Settings.getCategoriaEdit());
                System.out.println(position);
                Settings.getListCategoria().set(position, Settings.getCategoriaEdit());
                break;
            case Settings.ACTION_DELETE:
                // Procura e elimina o objeto da Lista geral
                int idCategoria = Integer.parseInt(lbl_Id.getText());
                CategoriaDAO.removerCategoria(idCategoria);
                Settings.getListCategoria().remove(Settings.getCategoriaEdit());
                break;
        }
        Settings.ACTION = -1;
        Settings.setCategoriaEdit(null);
        thisStage = (Stage) btnAction.getScene().getWindow();
        thisStage.close();
    }

    public void buttonCancel(ActionEvent actionEvent) {
        // Reposição da Flag e Objeto Entidade em Settings e encerramento da Stage
        Settings.ACTION = -1;
        Settings.setCategoriaEdit(null);
        thisStage = (Stage) btnAction.getScene().getWindow();
        thisStage.close();
    }
}
