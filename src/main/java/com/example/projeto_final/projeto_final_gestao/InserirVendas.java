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

public class InserirVendas implements Initializable {

    @FXML
    private Button btnAction;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lbl_Id;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtTotal;

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
                lblTitle.setText("Inserção das Vendas");
                btnAction.setText("Inserir");
                break;
            case Settings.ACTION_UPDATE:
                // Altera o texto do título e do botão Action
                lblTitle.setText("Alteração das Vendas");
                btnAction.setText("Alterar");

                //Campo referente ao número de processo deve estar disable
                //Preencher os campos com os dados do objeto Aluno
                //que pretendemos editar/ atualizar
                lbl_Id.setText(String.valueOf(Settings.getVendasEdit().getIdVendas()));
                txtQuantidade.setText(Settings.getVendasEdit().getQuantidade());
                txtTotal.setText(String.valueOf(Settings.getVendasEdit().getTotal()));
                break;
            case Settings.ACTION_DELETE:
                // Altera o texto do título e do botão Action
                lblTitle.setText("Eliminação das Vendas");
                btnAction.setText("Eliminar");

                //Preencher os campos com os dados do objeto Aluno
                //que pretendemos eliminar
                lbl_Id.setText(String.valueOf(Settings.getVendasEdit().getIdVendas()));
                txtQuantidade.setText(Settings.getVendasEdit().getQuantidade());
                txtTotal.setText(String.valueOf(Settings.getVendasEdit().getTotal()));
                //Campos que devem estar disable
                txtQuantidade.setDisable(true);
                txtTotal.setDisable(true);
                break;
        }

    }




    public void ButtonAction(ActionEvent actionEvent) {
        switch (Settings.ACTION) {
            case Settings.ACTION_INSERT:
                // Recolha dos dados existentes nos Controlos (objetos gráficos da janela)
                // para um novo objeto.
                lbl_Id.setDisable(true);
                String Quantidade = txtQuantidade.getText();
                Double Total = Double.valueOf(txtTotal.getText());
                Vendas newVendas = new Vendas(Quantidade, Total);

                int idVendas = VendasDAO.adicionarVendas(newVendas);
                newVendas.setIdVendas(idVendas);
                //Adiciona o novo Aluno à TableView
                Settings.getListVendas().add(newVendas);
                break;
            case Settings.ACTION_UPDATE:
                //Recolha dos dados existentes nos Controlos (objetos gráficos da janela)
                // para o mesmo objeto.
                Settings.getVendasEdit().setQuantidade(txtQuantidade.getText());
                Settings.getVendasEdit().setTotal(Double.valueOf(txtTotal.getText()));

                VendasDAO.atualizarVendas(Settings.getVendasEdit());
                int position = Settings.getListVendas().indexOf(Settings.getVendasEdit());
                System.out.println(position);
                Settings.getListVendas().set(position, Settings.getVendasEdit());
                break;
            case Settings.ACTION_DELETE:
                // Procura e elimina o objeto da Lista geral
                int IdVendas = Integer.parseInt(lbl_Id.getText());
                VendasDAO.removerVendas(IdVendas);
                Settings.getListVendas().remove(Settings.getVendasEdit());
                break;
        }
        Settings.ACTION = -1;
        Settings.setVendasEdit(null);
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
