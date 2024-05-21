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

public class VendasList implements Initializable {
    @FXML
    private TableColumn tableColumnIdVendas;

    @FXML
    private TableColumn tableColumnQuantidade;

    @FXML
    private TableColumn tableColumnTotal;

    @FXML
    private TableView tableviewVendas;
    public void ButtonInserirVendas(ActionEvent actionEvent) throws IOException {
        // Definição da Flag Action
        Settings.ACTION = Settings.ACTION_INSERT;
        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da cena pretendida (student.fxml)
        Parent scene = FXMLLoader.load(getClass().getResource("inserirVendas.fxml"));

        // Nova janela (Stage)
        Stage addVendas = new Stage();
        addVendas.setTitle("Aplicação de Demonstração - Inserir Vendas");

        // Associação da Scene à Stage
        addVendas.setScene(new Scene(scene));

        // Abertura da janela addStudent em modo MODAL, em relação à primaryStage
        addVendas.initOwner(Settings.getPrimaryStage());
        addVendas.initModality(Modality.WINDOW_MODAL);

        // Abertura da janela
        addVendas.show();
    }

    public void ButtonEditarVendas(ActionEvent actionEvent) throws IOException {
        // Caso não haja um item selecionado notifica o Utilizador e termina.
        if (tableviewVendas.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Item não selecionado");
            alert.setHeaderText("Selecione um item, por favor!");
            alert.show();
            return;
        }
        // Se chegou aqui é porque há um item selecionado => Extrai-o
        // O método devolve um Object porque nunca sabe o que lá vem. => Cast para Aluno.
        Vendas selectedItem = (Vendas) tableviewVendas.getSelectionModel().getSelectedItem();

        // Definição da Flag Ation e do objeto de Entidade de settings com Insert
        Settings.ACTION = Settings.ACTION_UPDATE;
        Settings.setVendasEdit(selectedItem);

        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("inserirVendas.fxml"));

        // Nova janela
        Stage vendasEdit = new Stage();
        vendasEdit.setTitle("Aplicação de Demonstração - Atualizar Vendas");

        // Associação da Scene à Stage
        vendasEdit.setScene(new Scene(scene));

        // Abertura da janela edit Student em modo MODAL, em relação à primaryStage
        vendasEdit.initOwner(Settings.getPrimaryStage());
        vendasEdit.initModality(Modality.WINDOW_MODAL);

        // Abertura da Window
        vendasEdit.show();
    }

    public void ButtonRemoverVendas(ActionEvent actionEvent) throws IOException {
        // Caso não haja um item selecionado notifica o Utilizador e termina.
        if (tableviewVendas.getSelectionModel().getSelectedItem() == null) {

            // Notifica o utilizdor e termina
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Item não selecionado");
            alert.setHeaderText("Selecione um item, por favor!");
            alert.show();
            return;
        }
        // Se chegou aqui é porque há um item selecionado => Extrai-o
        // O método devolve um Object porque nunca sabe o que lá vem. => Cast para Aluno.
        Vendas selectedItem = (Vendas) tableviewVendas.getSelectionModel().getSelectedItem();

        // Definição da Flag Ation e do objeto de Entidade de settings com Insert
        Settings.ACTION = Settings.ACTION_DELETE;
        Settings.setVendasEdit(selectedItem);

        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("inserirVendas.fxml"));

        // Nova janela
        Stage vendasDelete = new Stage();
        vendasDelete.setTitle("Aplicação de Demonstração - Eliminar Venda");

        // Associação da Scene à Stage
        vendasDelete.setScene(new Scene(scene));

        // Abertura da janela edit Student em modo MODAL, em relação à primaryStage
        vendasDelete.initOwner(Settings.getPrimaryStage());
        vendasDelete.initModality(Modality.WINDOW_MODAL);

        // Abertura da Window
        vendasDelete.show();
    }

    public void ButtonClose(ActionEvent actionEvent) throws  IOException{
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("principal.fxml"));

        // Voltar à cena principal da Stage
        Settings.getPrimaryStage().setScene(new Scene(scene));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Associação das colunas aos atributos da classe
        tableColumnIdVendas.setCellValueFactory(new PropertyValueFactory<Vendas, Integer>("idVendas")); // Corrigido para "idCliente"
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<Vendas, String>("quantidade"));
        tableColumnTotal.setCellValueFactory(new PropertyValueFactory<Vendas, String>("total"));

        /**
         * Conversão dos dados da coluna Género. O método setCellFactory permite faze-lo.
         * Neste caso, queremos substituir o boolean do atributo genero por uma
         * string (true = Masculino; false = Femenino).
         * Passamos por parâmetro a classe do objeto da Lista (Aluno) e o tipo de dados (Boolean)
         * a tratar antes de chegar à coluna.
         * As instruções de substituição fazem-se no método updateItem.
         */
        // Associação da ObservableList à TableView. A partir daqui, tudo se faz na ObservableList.
        Settings.getListVendas().clear();
        tableviewVendas.setItems(VendasDAO.listarVendas());
        System.out.println(Settings.getListVendas().size());
    }


}

