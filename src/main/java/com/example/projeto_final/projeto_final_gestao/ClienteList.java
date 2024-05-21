package com.example.projeto_final.projeto_final_gestao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClienteList implements Initializable {
    @FXML
    private TableColumn tableColumnIdCliente;

    @FXML
    private TableColumn tableColumnNome;

    @FXML
    private TableColumn tableColumnEmail;

    @FXML
    private TableColumn tableColumnNumTelefone;

    @FXML
    private TableView tableviewClientes;



    public void ButtonInserirCliente(ActionEvent actionEvent) throws IOException {
        // Definição da Flag Action
        Settings.ACTION = Settings.ACTION_INSERT;
        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da cena pretendida (student.fxml)
        Parent scene = FXMLLoader.load(getClass().getResource("inserirCliente.fxml"));

        // Nova janela (Stage)
        Stage addCliente = new Stage();
        addCliente.setTitle("Aplicação de Demonstração - Inserir Cliente");

        // Associação da Scene à Stage
        addCliente.setScene(new Scene(scene));

        // Abertura da janela addStudent em modo MODAL, em relação à primaryStage
        addCliente.initOwner(Settings.getPrimaryStage());
        addCliente.initModality(Modality.WINDOW_MODAL);

        // Abertura da janela
        addCliente.show();
    }

    public void ButtonClose(ActionEvent actionEvent) throws IOException {
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("principal.fxml"));

        // Voltar à cena principal da Stage
        Settings.getPrimaryStage().setScene(new Scene(scene));
    }

    public void ButtonEditarCliente(ActionEvent actionEvent) throws IOException {
        // Caso não haja um item selecionado notifica o Utilizador e termina.
        if (tableviewClientes.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Item não selecionado");
            alert.setHeaderText("Selecione um item, por favor!");
            alert.show();
            return;
        }
        // Se chegou aqui é porque há um item selecionado => Extrai-o
        // O método devolve um Object porque nunca sabe o que lá vem. => Cast para Aluno.
        Cliente selectedItem = (Cliente) tableviewClientes.getSelectionModel().getSelectedItem();

        // Definição da Flag Ation e do objeto de Entidade de settings com Insert
        Settings.ACTION = Settings.ACTION_UPDATE;
        Settings.setClienteEdit(selectedItem);

        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("inserirCliente.fxml"));

        // Nova janela
        Stage clienteEdit = new Stage();
        clienteEdit.setTitle("Aplicação de Demonstração - Atualizar Aluno");

        // Associação da Scene à Stage
        clienteEdit.setScene(new Scene(scene));

        // Abertura da janela edit Student em modo MODAL, em relação à primaryStage
        clienteEdit.initOwner(Settings.getPrimaryStage());
        clienteEdit.initModality(Modality.WINDOW_MODAL);

        // Abertura da Window
        clienteEdit.show();
    }

    public void ButtonRemoverCliente(ActionEvent actionEvent) throws IOException {
        // Caso não haja um item selecionado notifica o Utilizador e termina.
        if (tableviewClientes.getSelectionModel().getSelectedItem() == null) {

            // Notifica o utilizdor e termina
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Item não selecionado");
            alert.setHeaderText("Selecione um item, por favor!");
            alert.show();
            return;
        }
        // Se chegou aqui é porque há um item selecionado => Extrai-o
        // O método devolve um Object porque nunca sabe o que lá vem. => Cast para Aluno.
        Cliente selectedItem = (Cliente) tableviewClientes.getSelectionModel().getSelectedItem();

        // Definição da Flag Ation e do objeto de Entidade de settings com Insert
        Settings.ACTION = Settings.ACTION_DELETE;
        Settings.setClienteEdit(selectedItem);

        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("inserirCliente.fxml"));

        // Nova janela
        Stage clienteDelete = new Stage();
        clienteDelete.setTitle("Aplicação de Demonstração - Eliminar Cliente");

        // Associação da Scene à Stage
        clienteDelete.setScene(new Scene(scene));

        // Abertura da janela edit Student em modo MODAL, em relação à primaryStage
        clienteDelete.initOwner(Settings.getPrimaryStage());
        clienteDelete.initModality(Modality.WINDOW_MODAL);

        // Abertura da Window
        clienteDelete.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Associação das colunas aos atributos da classe
        tableColumnIdCliente.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente")); // Corrigido para "idCliente"
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nomeCliente"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
        tableColumnNumTelefone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("numTelefone"));
        System.out.println();

        /**
         * Conversão dos dados da coluna Género. O método setCellFactory permite faze-lo.
         * Neste caso, queremos substituir o boolean do atributo genero por uma
         * string (true = Masculino; false = Femenino).
         * Passamos por parâmetro a classe do objeto da Lista (Aluno) e o tipo de dados (Boolean)
         * a tratar antes de chegar à coluna.
         * As instruções de substituição fazem-se no método updateItem.
         */
        // Associação da ObservableList à TableView. A partir daqui, tudo se faz na ObservableList.

        tableviewClientes.setItems(ClienteDAO.listarClientes());
        System.out.println(Settings.getListClientes().size());
    }

}

