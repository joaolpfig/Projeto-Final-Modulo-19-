package com.example.projeto_final.projeto_final_gestao;

import javafx.collections.ObservableList;

import java.sql.*;

public class ProdutoDAO {
    public static int adicionarProduto(Produto Produtos) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;
        int IdProduto= 0;

        String sql = "INSERT INTO produto (nomeProduto, preco, tamanho, quantidade) VALUES (? ,? , ?, ?);";

        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Produtos.getNomeProduto());
            stmt.setDouble(2, Produtos.getPreco());
            stmt.setString(3, Produtos.getTamanho());
            stmt.setString(4, Produtos.getQuantidade());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                IdProduto = rs.getInt(1);
            }
            System.out.println("Produto adicionado com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar novo produto: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
        return IdProduto;
    }

    public static ObservableList<Produto> listarProdutos() {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Produto> produtos = Settings.getListProdutos();

        String sql = "SELECT * FROM produto;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idProduto = rs.getInt("IdProduto");
                String nomeProduto = rs.getString("nomeProduto");
                Double preco = Double.valueOf(rs.getString("preco"));
                String tamanho = rs.getString("tamanho");
                String quantidade = rs.getString("quantidade");
                Produto p = new Produto(idProduto, nomeProduto, preco, tamanho, quantidade);
                produtos.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os produtos: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt, rs);
        }
        return produtos;
    }



    public static void removerProduto(int IdProduto) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;

        String sql = "DELETE FROM produto WHERE idProduto = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, IdProduto);
            stmt.executeUpdate();
            System.out.println("Produto removido com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao remover produto: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
    }

    public static void atualizarProduto(Produto produto) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;

        String sql = "UPDATE produto SET nomeProduto = ?, preco = ?, tamanho = ?, quantidade = ? WHERE idProduto = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNomeProduto());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getTamanho());
            stmt.setString(4, produto.getQuantidade());
            stmt.setInt(5, produto.getIdProduto());
            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar produto: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
    }
}
