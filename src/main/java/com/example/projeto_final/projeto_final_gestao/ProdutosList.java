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

public class ProdutosList implements Initializable {
    @FXML
    private TableColumn tableColumnIdProduto;

    @FXML
    private TableColumn tableColumnNome;

    @FXML
    private TableColumn tableColumnPreco;

    @FXML
    private TableColumn tableColumnTamanho;

    @FXML
    private TableColumn tableColumnQuantidade;

    @FXML
    private TableView tableviewProdutos;




    public void ButtonInserirProdutos(ActionEvent actionEvent) throws IOException {
        // Definição da Flag Action
        Settings.ACTION = Settings.ACTION_INSERT;
        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da cena pretendida (student.fxml)
        Parent scene = FXMLLoader.load(getClass().getResource("inserirProdutos.fxml"));

        // Nova janela (Stage)
        Stage addProduto = new Stage();
        addProduto.setTitle("Aplicação de Demonstração - Inserir Produto");

        // Associação da Scene à Stage
        addProduto.setScene(new Scene(scene));

        // Abertura da janela addStudent em modo MODAL, em relação à primaryStage
        addProduto.initOwner(Settings.getPrimaryStage());
        addProduto.initModality(Modality.WINDOW_MODAL);

        // Abertura da janela
        addProduto.show();
    }

    public void ButtonEditarProdutos(ActionEvent actionEvent) throws IOException {
        // Caso não haja um item selecionado notifica o Utilizador e termina.
        if (tableviewProdutos.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Item não selecionado");
            alert.setHeaderText("Selecione um item, por favor!");
            alert.show();
            return;
        }
        // Se chegou aqui é porque há um item selecionado => Extrai-o
        // O método devolve um Object porque nunca sabe o que lá vem. => Cast para Aluno.
        Produto selectedItem = (Produto) tableviewProdutos.getSelectionModel().getSelectedItem();

        // Definição da Flag Ation e do objeto de Entidade de settings com Insert
        Settings.ACTION = Settings.ACTION_UPDATE;
        Settings.setProdutoEdit(selectedItem);

        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("inserirProdutos.fxml"));

        // Nova janela
        Stage produtoEdit = new Stage();
        produtoEdit.setTitle("Aplicação de Demonstração - Atualizar Aluno");

        // Associação da Scene à Stage
        produtoEdit.setScene(new Scene(scene));

        // Abertura da janela edit Student em modo MODAL, em relação à primaryStage
        produtoEdit.initOwner(Settings.getPrimaryStage());
        produtoEdit.initModality(Modality.WINDOW_MODAL);

        // Abertura da Window
        produtoEdit.show();
    }

    public void ButtonRemoverProdutos(ActionEvent actionEvent) throws IOException {
        // Caso não haja um item selecionado notifica o Utilizador e termina.
        if (tableviewProdutos.getSelectionModel().getSelectedItem() == null) {

            // Notifica o utilizdor e termina
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Item não selecionado");
            alert.setHeaderText("Selecione um item, por favor!");
            alert.show();
            return;
        }
        // Se chegou aqui é porque há um item selecionado => Extrai-o
        // O método devolve um Object porque nunca sabe o que lá vem. => Cast para Aluno.
        Produto selectedItem = (Produto) tableviewProdutos.getSelectionModel().getSelectedItem();

        // Definição da Flag Ation e do objeto de Entidade de settings com Insert
        Settings.ACTION = Settings.ACTION_DELETE;
        Settings.setProdutoEdit(selectedItem);

        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("inserirProdutos.fxml"));

        // Nova janela
        Stage produtoDelete = new Stage();
        produtoDelete.setTitle("Aplicação de Demonstração - Eliminar Produto");

        // Associação da Scene à Stage
        produtoDelete.setScene(new Scene(scene));

        // Abertura da janela edit Student em modo MODAL, em relação à primaryStage
        produtoDelete.initOwner(Settings.getPrimaryStage());
        produtoDelete.initModality(Modality.WINDOW_MODAL);

        // Abertura da Window
        produtoDelete.show();
    }

    public void ButtonClose(ActionEvent actionEvent) throws IOException {
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("principal.fxml"));

        // Voltar à cena principal da Stage
        Settings.getPrimaryStage().setScene(new Scene(scene));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Associação das colunas aos atributos da classe
        tableColumnIdProduto.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("idProduto")); // Corrigido para "idProduto"
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nomeProduto"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Produto, Double>("preco"));
        tableColumnTamanho.setCellValueFactory(new PropertyValueFactory<Produto, String>("tamanho"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<Produto, String>("quantidade"));
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
        Settings.getListProdutos().clear();
        tableviewProdutos.setItems(ProdutoDAO.listarProdutos());
        System.out.println(Settings.getListProdutos().size());
    }
}
