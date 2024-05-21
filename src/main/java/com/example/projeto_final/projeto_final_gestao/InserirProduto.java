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

public class InserirProduto implements Initializable {

    @FXML
    private Button btnAction;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lbl_Id;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtTamanho;

    @FXML
    private TextField txtQuantidade;

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
                lblTitle.setText("Inserção do Produto");
                btnAction.setText("Inserir");
                break;
            case Settings.ACTION_UPDATE:
                // Altera o texto do título e do botão Action
                lblTitle.setText("Alteração do Produto");
                btnAction.setText("Alterar");

                //Campo referente ao número de processo deve estar disable
                //Preencher os campos com os dados do objeto Aluno
                //que pretendemos editar/ atualizar
                lbl_Id.setText(String.valueOf(Settings.getProdutoEdit().getIdProduto()));
                txtNome.setText(Settings.getProdutoEdit().getNomeProduto());
                txtPreco.setText(String.valueOf(Settings.getProdutoEdit().getPreco()));
                txtTamanho.setText(Settings.getProdutoEdit().getTamanho());
                txtQuantidade.setText(Settings.getProdutoEdit().getQuantidade());
                break;
            case Settings.ACTION_DELETE:
                // Altera o texto do título e do botão Action
                lblTitle.setText("Eliminação do Produto");
                btnAction.setText("Eliminar");

                //Preencher os campos com os dados do objeto Aluno
                //que pretendemos eliminar
                lbl_Id.setText(String.valueOf(Settings.getProdutoEdit().getIdProduto()));
                txtNome.setText(String.valueOf(Settings.getProdutoEdit().getNomeProduto()));
                txtPreco.setText(String.valueOf(Settings.getProdutoEdit().getIdProduto()));
                txtTamanho.setText(String.valueOf(Settings.getProdutoEdit().getIdProduto()));
                txtQuantidade.setText(String.valueOf(Settings.getProdutoEdit().getIdProduto()));

                //Campos que devem estar disable
                txtNome.setDisable(true);
                txtPreco.setDisable(true);
                txtTamanho.setDisable(true);
                txtQuantidade.setDisable(true);
                break;
        }

    }

    public void ButtonAction(ActionEvent actionEvent) {
        switch (Settings.ACTION) {
            case Settings.ACTION_INSERT:
                // Recolha dos dados existentes nos Controlos (objetos gráficos da janela)
                // para um novo objeto.
                lbl_Id.setDisable(true);
                String nomeProduto = txtNome.getText();
                Double preco = Double.parseDouble(txtPreco.getText());
                String tamanho = txtTamanho.getText();
                String quantidade = txtQuantidade.getText();
                Produto newProduto = new Produto(nomeProduto, preco, tamanho, quantidade);

                int idProduto = ProdutoDAO.adicionarProduto(newProduto);
                newProduto.setIdProduto(idProduto);
                //Adiciona o novo Aluno à TableView
                Settings.getListProdutos().add(newProduto);
                break;
            case Settings.ACTION_UPDATE:
                //Recolha dos dados existentes nos Controlos (objetos gráficos da janela)
                // para o mesmo objeto.
                Settings.getProdutoEdit().setNomeProduto(txtNome.getText());
                Settings.getProdutoEdit().setPreco(Double.valueOf(txtPreco.getText()));
                Settings.getProdutoEdit().setQuantidade(txtQuantidade.getText());
                Settings.getProdutoEdit().setTamanho(txtTamanho.getText());


                ProdutoDAO.atualizarProduto(Settings.getProdutoEdit());
                int position = Settings.getListProdutos().indexOf(Settings.getProdutoEdit());
                System.out.println(position);
                Settings.getListProdutos().set(position, Settings.getProdutoEdit());
                break;
            case Settings.ACTION_DELETE:
                // Procura e elimina o objeto da Lista geral
                int id_produto = Integer.parseInt(lbl_Id.getText());
                ProdutoDAO.removerProduto(id_produto);
                Settings.getListProdutos().remove(Settings.getProdutoEdit());
                break;
        }
        Settings.ACTION = -1;
        Settings.setClienteEdit(null);
        thisStage = (Stage) btnAction.getScene().getWindow();
        thisStage.close();
    }

    public void buttonCancel(ActionEvent actionEvent) {
        // Reposição da Flag e Objeto Entidade em Settings e encerramento da Stage
        Settings.ACTION = -1;
        Settings.setProdutoEdit(null);
        thisStage = (Stage) btnAction.getScene().getWindow();
        thisStage.close();
    }
}
