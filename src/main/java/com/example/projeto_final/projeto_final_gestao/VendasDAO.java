package com.example.projeto_final.projeto_final_gestao;

import javafx.collections.ObservableList;

import java.sql.*;

public class VendasDAO {
    public static int adicionarVendas(Vendas vendas) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;
        int IdVendas= 0;

        String sql = "INSERT INTO vendas (quantidade, total) VALUES (? ,?);";

        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, vendas.getQuantidade());
            stmt.setDouble(2, vendas.getTotal());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                IdVendas = rs.getInt(1);
            }
            System.out.println("Vendas adicionado com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar novas vendas: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
        return IdVendas;
    }

    public static ObservableList<Vendas> listarVendas() {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Vendas> vendas = Settings.getListVendas();

        String sql = "SELECT * FROM vendas;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idVendas = rs.getInt("IdVendas");
                String quantidade = rs.getString("quantidade");
                Double total = rs.getDouble("total");
                Vendas v = new Vendas(idVendas, quantidade, total);
                vendas.add(v);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar as vendas: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt, rs);
        }
        return vendas;
    }



    public static void removerVendas(int IdVendas) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;

        String sql = "DELETE FROM vendas WHERE idVendas = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, IdVendas);
            stmt.executeUpdate();
            System.out.println("Vendas removido com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao remover vendas: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
    }

    public static void atualizarVendas(Vendas vendas) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;

        String sql = "UPDATE vendas SET quantidade = ?, total = ? WHERE idVendas = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vendas.getQuantidade());
            stmt.setDouble(2, vendas.getTotal());
            stmt.setInt(3, vendas.getIdVendas());
            stmt.executeUpdate();
            System.out.println("Vendas atualizado com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar as Vendas: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
    }
}
