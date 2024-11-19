package com.example.crud1.repositories;

import com.example.crud1.models.Cliente;
import com.example.crud1.models.Propietario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropietarioRepository {
    private Connection conn;

    public PropietarioRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Propietario> list() {
        List<Propietario> propietarios = new ArrayList<>();
        String query = "SELECT * FROM propietario";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Propietario propietario = new Propietario();
                propietario.setCedula(rs.getInt("cedula"));
                propietario.setTelefono1(rs.getString("telefono1"));
                propietario.setTelefono2(rs.getString("telefono2"));
                propietario.setNombreCompleto(rs.getString("nombreCompleto"));
                propietario.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                propietario.setFechaExpedicion(rs.getDate("fechaExpedicion"));
                propietario.setCorreoElectronico(rs.getString("correoElectronico"));
                propietarios.add(propietario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propietarios;
    }

    public boolean delete(int cedula) {
        String query = "DELETE FROM propietario WHERE cedula = ?";
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
