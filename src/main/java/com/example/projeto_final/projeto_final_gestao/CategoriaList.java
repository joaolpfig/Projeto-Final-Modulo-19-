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

public class CategoriaList implements Initializable {
    @FXML
    private TableColumn tableColumnIdCategoria;

    @FXML
    private TableColumn tableColumnCategoria;

    @FXML
    private TableColumn tableColumnCor;


    @FXML
    private TableView tableviewCategoria;


    public void ButtonInserirCategorias(ActionEvent actionEvent) throws IOException {
        // Definição da Flag Action
        Settings.ACTION = Settings.ACTION_INSERT;
        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da cena pretendida (student.fxml)
        Parent scene = FXMLLoader.load(getClass().getResource("inserirCategoria.fxml"));

        // Nova janela (Stage)
        Stage addCategoria = new Stage();
        addCategoria.setTitle("Aplicação de Demonstração - Inserir Categoria");

        // Associação da Scene à Stage
        addCategoria.setScene(new Scene(scene));

        // Abertura da janela addStudent em modo MODAL, em relação à primaryStage
        addCategoria.initOwner(Settings.getPrimaryStage());
        addCategoria.initModality(Modality.WINDOW_MODAL);

        // Abertura da janela
        addCategoria.show();
    }

    public void ButtonEditarCategorias(ActionEvent actionEvent) throws IOException{
        // Caso não haja um item selecionado notifica o Utilizador e termina.
        if (tableviewCategoria.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Item não selecionado");
            alert.setHeaderText("Selecione um item, por favor!");
            alert.show();
            return;
        }
        // Se chegou aqui é porque há um item selecionado => Extrai-o
        // O método devolve um Object porque nunca sabe o que lá vem. => Cast para Aluno.
        Categoria selectedItem = (Categoria) tableviewCategoria.getSelectionModel().getSelectedItem();

        // Definição da Flag Ation e do objeto de Entidade de settings com Insert
        Settings.ACTION = Settings.ACTION_UPDATE;
        Settings.setCategoriaEdit(selectedItem);

        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("inserirCategoria.fxml"));

        // Nova janela
        Stage categoriaEdit = new Stage();
        categoriaEdit.setTitle("Aplicação de Demonstração - Atualizar Categoria");

        // Associação da Scene à Stage
        categoriaEdit.setScene(new Scene(scene));

        // Abertura da janela edit Student em modo MODAL, em relação à primaryStage
        categoriaEdit.initOwner(Settings.getPrimaryStage());
        categoriaEdit.initModality(Modality.WINDOW_MODAL);

        // Abertura da Window
        categoriaEdit.show();
    }

    public void ButtonRemoverCategorias(ActionEvent actionEvent) throws IOException {
        // Caso não haja um item selecionado notifica o Utilizador e termina.
        if (tableviewCategoria.getSelectionModel().getSelectedItem() == null) {

            // Notifica o utilizdor e termina
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Item não selecionado");
            alert.setHeaderText("Selecione um item, por favor!");
            alert.show();
            return;
        }
        // Se chegou aqui é porque há um item selecionado => Extrai-o
        // O método devolve um Object porque nunca sabe o que lá vem. => Cast para Aluno.
        Categoria selectedItem = (Categoria) tableviewCategoria.getSelectionModel().getSelectedItem();

        // Definição da Flag Ation e do objeto de Entidade de settings com Insert
        Settings.ACTION = Settings.ACTION_DELETE;
        Settings.setCategoriaEdit(selectedItem);

        // Abre a Scene numa nova Stage em modo Modal
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("inserirCategoria.fxml"));

        // Nova janela
        Stage categoriaDelete = new Stage();
        categoriaDelete.setTitle("Aplicação de Demonstração - Eliminar Categoria");

        // Associação da Scene à Stage
        categoriaDelete.setScene(new Scene(scene));

        // Abertura da janela edit Student em modo MODAL, em relação à primaryStage
        categoriaDelete.initOwner(Settings.getPrimaryStage());
        categoriaDelete.initModality(Modality.WINDOW_MODAL);

        // Abertura da Window
        categoriaDelete.show();
    }

    public void ButtonClose(ActionEvent actionEvent) throws IOException {
        // Aquisição do controlo da Scene pretendida
        Parent scene = FXMLLoader.load(getClass().getResource("principal.fxml"));

        // Voltar à cena principal da Stage
        Settings.getPrimaryStage().setScene(new Scene(scene));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Associação das colunas aos atributos da classe
        tableColumnIdCategoria.setCellValueFactory(new PropertyValueFactory<Categoria, Integer>("idCategoria")); // Corrigido para "idCliente"
        tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<Categoria, String>("categoria"));
        tableColumnCor.setCellValueFactory(new PropertyValueFactory<Categoria, String>("cor"));

        /**
         * Conversão dos dados da coluna Género. O método setCellFactory permite faze-lo.
         * Neste caso, queremos substituir o boolean do atributo genero por uma
         * string (true = Masculino; false = Femenino).
         * Passamos por parâmetro a classe do objeto da Lista (Aluno) e o tipo de dados (Boolean)
         * a tratar antes de chegar à coluna.
         * As instruções de substituição fazem-se no método updateItem.
         */
        // Associação da ObservableList à TableView. A partir daqui, tudo se faz na ObservableList.
        Settings.getListCategoria().clear();
        tableviewCategoria.setItems(CategoriaDAO.listarCategoria());
        System.out.println(Settings.getListCategoria().size());
    }
}
