package com.example.crud1.repositories;

import com.example.crud1.models.Agente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenteRepository {

    private Connection conn;

    public AgenteRepository(Connection conn) {
        this.conn = conn;
    }
    String querycedula = "SELECT * FROM agente WHERE cedula = ?";
    public List<Agente> list() {
        List<Agente> agentes = new ArrayList<>();
        String query = "SELECT * FROM agente";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Agente agente = new Agente();
                agente.setCedula(rs.getInt("cedula"));
                agente.setCelular(rs.getString("celular"));
                agente.setNombreCompleto(rs.getString("nombreCompleto"));
                agente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                agente.setFechaExpedicion(rs.getDate("fechaExpedicion"));
                agente.setLoguin(rs.getString("loguin"));
                agente.setContrasena(rs.getString("contrasena"));
                agente.setCorreoElectronico(rs.getString("correoElectronico"));
                agente.setDireccion(rs.getString("direccion"));
                agentes.add(agente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agentes;
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
    public boolean create(Agente agente) {
        String query = "INSERT INTO agente (cedula, celular, nombreCompleto, fechaNacimiento, fechaExpedicion, loguin, contrasena, correoElectronico, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, agente.getCedula());
            ps.setString(2, agente.getCelular());
            ps.setString(3, agente.getNombreCompleto());
            ps.setDate(4, new java.sql.Date(agente.getFechaNacimiento().getTime()));
            ps.setDate(5, new java.sql.Date(agente.getFechaExpedicion().getTime()));
            ps.setString(6, agente.getLoguin());
            ps.setString(7, agente.getContrasena());
            ps.setString(8, agente.getCorreoElectronico());
            ps.setString(9, agente.getDireccion());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Agente getById(int cedula) {
        String query = "SELECT * FROM agente WHERE cedula = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Agente agente = new Agente();
                agente.setCedula(rs.getInt("cedula"));
                agente.setCelular(rs.getString("celular"));
                agente.setNombreCompleto(rs.getString("nombreCompleto"));
                agente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                agente.setFechaExpedicion(rs.getDate("fechaExpedicion"));
                agente.setLoguin(rs.getString("loguin"));
                agente.setContrasena(rs.getString("contrasena"));
                agente.setCorreoElectronico(rs.getString("correoElectronico"));
                agente.setDireccion(rs.getString("direccion"));
                return agente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Agente agente) {
        String query = "UPDATE agente SET celular = ?, nombreCompleto = ?, fechaNacimiento = ?, fechaExpedicion = ?, loguin = ?, contrasena = ?, correoElectronico = ?, direccion = ? WHERE cedula = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setString(1, agente.getCelular());
            ps.setString(2, agente.getNombreCompleto());
            ps.setDate(3, new java.sql.Date(agente.getFechaNacimiento().getTime()));
            ps.setDate(4, new java.sql.Date(agente.getFechaExpedicion().getTime()));
            ps.setString(5, agente.getLoguin());
            ps.setString(6, agente.getContrasena());
            ps.setString(7, agente.getCorreoElectronico());
            ps.setString(8, agente.getDireccion());
            ps.setInt(9, agente.getCedula());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int cedula) {
        String query = "DELETE FROM agente WHERE cedula = ?";
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
