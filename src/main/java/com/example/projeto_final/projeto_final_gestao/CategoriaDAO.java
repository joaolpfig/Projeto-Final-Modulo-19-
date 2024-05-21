package com.example.projeto_final.projeto_final_gestao;

import javafx.collections.ObservableList;

import java.sql.*;

public class CategoriaDAO {
    public static int adicionarCategoria(Categoria categoria) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;
        int IdCategoria= 0;

        String sql = "INSERT INTO categoria (categoria, cor) VALUES (? , ?);";

        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, categoria.getCategoria());
            stmt.setString(2, categoria.getCor());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                IdCategoria = rs.getInt(1);
            }
            System.out.println("Categoria adicionado com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar novo categoria: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
        return IdCategoria;
    }

    public static ObservableList<Categoria> listarCategoria() {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Categoria> categorias = Settings.getListCategoria();

        String sql = "SELECT * FROM categoria;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCategoria = rs.getInt("IdCategoria");
                String categoria = rs.getString("categoria");
                String cor = rs.getString("cor");
                Categoria c = new Categoria(idCategoria, categoria, cor);
                categorias.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar as categorias: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt, rs);
        }
        return categorias;
    }



    public static void removerCategoria(int IdCategoria) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;

        String sql = "DELETE FROM categoria WHERE idCategoria = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, IdCategoria);
            stmt.executeUpdate();
            System.out.println("Categoria removido com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao remover categoria: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
    }

    public static void atualizarCategoria(Categoria categoria) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;

        String sql = "UPDATE categoria SET categoria = ?, cor = ? WHERE idCategoria = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria.getCategoria());
            stmt.setString(2, categoria.getCor());
            stmt.setInt(3, categoria.getIdCategoria());
            stmt.executeUpdate();
            System.out.println("Categoria atualizado com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar categoria: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
    }
}
