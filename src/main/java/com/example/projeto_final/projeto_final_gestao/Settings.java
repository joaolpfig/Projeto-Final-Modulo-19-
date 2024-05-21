package com.example.projeto_final.projeto_final_gestao;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
public class Settings {
    private static Stage primaryStage;


    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Settings.primaryStage = primaryStage;
    }

    public static int ACTION = -1;

    // Constantes para definir as operações da FLAG ACTION
    public static final int ACTION_INSERT = 1;
    public static final int ACTION_UPDATE = 2;
    public static final int ACTION_DELETE = 3;
    //endregion

    //Cliente
    private static Cliente clienteEdit;

    public static Cliente getClienteEdit() {
        return clienteEdit;
    }

    public static void setClienteEdit(Cliente clienteEdit) {
        Settings.clienteEdit = clienteEdit;
    }
//endregion

    //region Lista de Clientes

    /**
     * ObservableList de Clientes
     */
    private static ObservableList<Cliente> listClientes = FXCollections.observableArrayList();

    public static ObservableList<Cliente> getListClientes() {
        return listClientes;
    }

    public static void setListClientes(ObservableList<Cliente> listClientes) {
        Settings.listClientes = listClientes;
    }


    //Produtos
    private static Produto produtoEdit;

    public static Produto getProdutoEdit() {
        return produtoEdit;
    }

    public static void setProdutoEdit(Produto produtoEdit) {
        Settings.produtoEdit = produtoEdit;
    }
//endregion

    //region Lista de Produtos

    /**
     * ObservableList de Produtos
     */
    private static ObservableList<Produto> listProdutos = FXCollections.observableArrayList();

    public static ObservableList<Produto> getListProdutos() {
        return listProdutos;
    }

    public static void setListProdutos(ObservableList<Produto> listProdutos) {
        Settings.listProdutos = listProdutos;
    }




    //Categoria
    private static Categoria categoriaEdit;

    public static Categoria getCategoriaEdit() {
        return categoriaEdit;
    }

    public static void setCategoriaEdit(Categoria categoriaEdit) {
        Settings.categoriaEdit = categoriaEdit;
    }
//endregion

    //region Lista de Categoria

    /**
     * ObservableList de Categoria
     */
    private static ObservableList<Categoria> listCategoria = FXCollections.observableArrayList();

    public static ObservableList<Categoria> getListCategoria() {
        return listCategoria;
    }

    public static void setListCategoria(ObservableList<Categoria> listCategoria) {
        Settings.listCategoria = listCategoria;
    }





    //Vendas
    private static Vendas vendasEdit;

    public static Vendas getVendasEdit() {
        return vendasEdit;
    }

    public static void setVendasEdit(Vendas vendasEdit) {
        Settings.vendasEdit = vendasEdit;
    }
//endregion

    //region Lista das Vendas

    /**
     * ObservableList das Vendas
     */
    private static ObservableList<Vendas> listVendas = FXCollections.observableArrayList();

    public static ObservableList<Vendas> getListVendas() {
        return listVendas;
    }

    public static void setListVendas(ObservableList<Vendas> listVendas) {
        Settings.listVendas = listVendas;
    }



}