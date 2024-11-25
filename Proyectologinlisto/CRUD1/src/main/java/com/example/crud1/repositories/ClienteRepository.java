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
    String querycedula = "SELECT * FROM cliente WHERE cedula = ?";

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
                cliente.setDireccion(rs.getString("direccion"));
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

    public Boolean existe(int cedula){
        try{
            PreparedStatement stmt = this.conn.prepareStatement(querycedula);
            stmt.setInt(1, cedula);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean create(Cliente cliente) {
        String query = "INSERT INTO cliente (cedula, telefono1, telefono2, nombreCompleto, direccion, fechaNacimiento, fechaExpedicion, correoElectronico) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, cliente.getCedula());
            ps.setString(2, cliente.getTelefono1());
            ps.setString(3, cliente.getTelefono2());
            ps.setString(4, cliente.getNombreCompleto());
            ps.setString(5, cliente.getDireccion());
            ps.setDate(6, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            ps.setDate(7, new java.sql.Date(cliente.getFechaExpedicion().getTime()));
            ps.setString(8, cliente.getCorreoElectronico());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cliente getById(int cedula) {
        String query = "SELECT * FROM cliente WHERE cedula = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCedula(rs.getInt("cedula"));
                cliente.setTelefono1(rs.getString("telefono1"));
                cliente.setTelefono2(rs.getString("telefono2"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setNombreCompleto(rs.getString("nombreCompleto"));
                cliente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                cliente.setFechaExpedicion(rs.getDate("fechaExpedicion"));
                cliente.setCorreoElectronico(rs.getString("correoElectronico"));
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Cliente cliente) {
        String query = "UPDATE cliente SET telefono1 = ?, telefono2 = ?, nombreCompleto = ?, direccion = ?, fechaNacimiento = ?, fechaExpedicion = ?, correoElectronico = ? WHERE cedula = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setString(1, cliente.getTelefono1());
            ps.setString(2, cliente.getTelefono2());
            ps.setString(3, cliente.getNombreCompleto());
            ps.setString(4, cliente.getDireccion());
            ps.setDate(5, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
            ps.setDate(6, new java.sql.Date(cliente.getFechaExpedicion().getTime()));
            ps.setString(7, cliente.getCorreoElectronico());
            ps.setInt(8, cliente.getCedula());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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