package com.example.projeto_final.projeto_final_gestao;

import javafx.collections.ObservableList;

import java.sql.*;

public class ClienteDAO {
    public static int adicionarCliente(Cliente clientes) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;
        int IdCLiente= 0;

        String sql = "INSERT INTO cliente (nomeCliente, email, numTelefone) VALUES (? ,? , ?);";

        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, clientes.getNomeCliente());
            stmt.setString(2, clientes.getEmail());
            stmt.setString(3, clientes.getNumTelefone());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                IdCLiente = rs.getInt(1);
            }
            System.out.println("Cliente adicionado com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar novo cliente: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
        return IdCLiente;
    }

    public static ObservableList<Cliente> listarClientes() {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Cliente> clientes = Settings.getListClientes();

        String sql = "SELECT * FROM cliente;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("IdCliente");
                String nome = rs.getString("nomeCliente");
                String email = rs.getString("email");
                String numTelefone = rs.getString("numTelefone");
                Cliente c = new Cliente(idCliente, nome, email, numTelefone);
                clientes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os clientes: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt, rs);
        }
        return clientes;
    }



    public static void removerCliente(int IdCliente) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;

        String sql = "DELETE FROM cliente WHERE idCliente = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, IdCliente);
            stmt.executeUpdate();
            System.out.println("Cliente removido com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao remover cliente: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
    }

    public static void atualizarCliente(Cliente cliente) {
        Connection conn = ConexaoBD.abrirBD();
        PreparedStatement stmt = null;

        String sql = "UPDATE cliente SET nomeCliente = ?, email = ?, numTelefone = ? WHERE idCliente = ?;";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getNumTelefone());
            stmt.setInt(4, cliente.getIdCliente());
            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar cliente: " + ex);
        } finally {
            ConexaoBD.fecharBD(stmt);
        }
    }
}

