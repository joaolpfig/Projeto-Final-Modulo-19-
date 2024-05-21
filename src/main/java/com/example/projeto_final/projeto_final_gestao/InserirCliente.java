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

public class InserirCliente implements Initializable {

    @FXML
    private Button btnAction;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lbl_Id;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

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
                lblTitle.setText("Inserção do Cliente");
                btnAction.setText("Inserir");
                break;
            case Settings.ACTION_UPDATE:
                // Altera o texto do título e do botão Action
                lblTitle.setText("Alteração do Cliente");
                btnAction.setText("Alterar");

                //Campo referente ao número de processo deve estar disable
                //Preencher os campos com os dados do objeto Aluno
                //que pretendemos editar/ atualizar
                lbl_Id.setText(String.valueOf(Settings.getClienteEdit().getIdCliente()));
                txtNome.setText(Settings.getClienteEdit().getNomeCliente());
                txtEmail.setText(Settings.getClienteEdit().getEmail());
                txtTelefone.setText(Settings.getClienteEdit().getNumTelefone());
                break;
            case Settings.ACTION_DELETE:
                // Altera o texto do título e do botão Action
                lblTitle.setText("Eliminação do Cliente");
                btnAction.setText("Eliminar");

                //Preencher os campos com os dados do objeto Aluno
                //que pretendemos eliminar
                lbl_Id.setText(String.valueOf(Settings.getClienteEdit().getIdCliente()));
                txtNome.setText(Settings.getClienteEdit().getNomeCliente());
                txtEmail.setText(Settings.getClienteEdit().getEmail());
                txtTelefone.setText(Settings.getClienteEdit().getNumTelefone());

                //Campos que devem estar disable
                txtNome.setDisable(true);
                txtEmail.setDisable(true);
                txtTelefone.setDisable(true);
                break;
        }

    }







    public void buttonCancel(ActionEvent actionEvent) {
        // Reposição da Flag e Objeto Entidade em Settings e encerramento da Stage
        Settings.ACTION = -1;
        Settings.setClienteEdit(null);
        thisStage = (Stage) btnAction.getScene().getWindow();
        thisStage.close();
    }

    public void ButtonAction(ActionEvent actionEvent) {
        switch (Settings.ACTION) {
            case Settings.ACTION_INSERT:
                // Recolha dos dados existentes nos Controlos (objetos gráficos da janela)
                // para um novo objeto.
                lbl_Id.setDisable(true);
                String nome = txtNome.getText();
                String Email = txtEmail.getText();
                String Telefone = txtTelefone.getText();
                Cliente newCliente = new Cliente(nome, Email, Telefone);

                int idCliente = ClienteDAO.adicionarCliente(newCliente);
                newCliente.setIdCliente(idCliente);
                //Adiciona o novo Aluno à TableView
                Settings.getListClientes().add(newCliente);
                break;
            case Settings.ACTION_UPDATE:
                //Recolha dos dados existentes nos Controlos (objetos gráficos da janela)
                // para o mesmo objeto.
                Settings.getClienteEdit().setNomeCliente(txtNome.getText());
                Settings.getClienteEdit().setEmail(txtEmail.getText());
                Settings.getClienteEdit().setNumTelefone(txtTelefone.getText());

                ClienteDAO.atualizarCliente(Settings.getClienteEdit());
                int position = Settings.getListClientes().indexOf(Settings.getClienteEdit());
                System.out.println(position);
                Settings.getListClientes().set(position, Settings.getClienteEdit());
                break;
            case Settings.ACTION_DELETE:
                // Procura e elimina o objeto da Lista geral
                int id_cliente = Integer.parseInt(lbl_Id.getText());
                ClienteDAO.removerCliente(id_cliente);
                Settings.getListClientes().remove(Settings.getClienteEdit());
                break;
        }
        Settings.ACTION = -1;
        Settings.setClienteEdit(null);
        thisStage = (Stage) btnAction.getScene().getWindow();
        thisStage.close();
    }
}
