package com.example.crud1.repositories;

import com.example.crud1.models.Agente;
import com.example.crud1.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private Connection conn;

    public ClienteRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Cliente> list() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCedula(rs.getInt("cedula"));
                cliente.setTelefono1(rs.getString("telefono1"));
                cliente.setTelefono2(rs.getString("telefono2"));
                cliente.setNombreCompleto(rs.getString("nombreCompleto"));
                cliente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                cliente.setFechaExpedicion(rs.getDate("fechaExpedicion"));
                cliente.setCorreoElectronico(rs.getString("correoElectronico"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public boolean delete(int cedula) {
        String query = "DELETE FROM cliente WHERE cedula = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, cedula);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}