package com.example.crud1.repositories;

import com.example.crud1.models.Agente;
import com.example.crud1.utils.ConnectionDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgenteRepository {

    private Connection conn;

    // Constructor que obtiene la conexión desde la clase ConnectionDatabase
    public AgenteRepository() {
        this.conn = ConnectionDatabase.getConnection();
    }

    public List<Agente> obtenerTodosLosAgentes() {
        List<Agente> agentes = new ArrayList<>();
        String query = "SELECT * FROM agente";

        try  {
            Statement st = this.conn.createStatement();
            ResultSet resultSet = st.executeQuery(query);

            while (resultSet.next()) {
                Agente agente = new Agente();

                agente.setCedula(resultSet.getInt("cedula"));
                agente.setCelular(resultSet.getString("celular"));
                agente.setNombreCompleto(resultSet.getString("nombreCompleto"));
                agente.setLogin(resultSet.getString("loguin"));
                agente.setContrasena(resultSet.getString("contrasena"));
                agente.setCorreoElectronico(resultSet.getString("correoElectronico"));
                agente.setDireccion(resultSet.getString("direccion"));

                Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                if (fechaNacimiento != null) {
                    agente.setFechaNacimiento(new java.util.Date(fechaNacimiento.getTime()));
                }

                Date fechaExpedicion = resultSet.getDate("fechaExpedicion");
                if (fechaExpedicion != null) {
                    agente.setFechaExpedicion(new java.util.Date(fechaExpedicion.getTime()));
                }

                // Añadir agente a la lista
                agentes.add(agente);
            }
            st.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agentes;
    }
    /*
    // Crear un nuevo agente
    public boolean crearAgente(Agente agente) {
        String query = "INSERT INTO agente (cedula, celular, nombreCompleto, loguin, contrasena, correoelectronico, direccion, fechanacimiento, fechaexpedicion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(query)) {
            preparedStatement.setInt(1, agente.getCedula());
            preparedStatement.setString(2, agente.getCelular());
            preparedStatement.setString(3, agente.getNombreCompleto());
            preparedStatement.setString(4, agente.getLogin());
            preparedStatement.setString(5, agente.getContrasena());
            preparedStatement.setString(6, agente.getCorreoElectronico());
            preparedStatement.setString(7, agente.getDireccion());
            preparedStatement.setDate(8, new java.sql.Date(agente.getFechaNacimiento().getTime()));
            preparedStatement.setDate(9, new java.sql.Date(agente.getFechaExpedicion().getTime()));

            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Actualizar un agente
    public boolean actualizarAgente(Agente agente) {
        String query = "UPDATE agente SET celular = ?, nombreCompleto = ?, loguin = ?, contrasena = ?, correoelectronico = ?, direccion = ?, fechanacimiento = ?, fechaexpedicion = ? WHERE cedula = ?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(query)) {
            preparedStatement.setString(1, agente.getCelular());
            preparedStatement.setString(2, agente.getNombreCompleto());
            preparedStatement.setString(3, agente.getLogin());
            preparedStatement.setString(4, agente.getContrasena());
            preparedStatement.setString(5, agente.getCorreoElectronico());
            preparedStatement.setString(6, agente.getDireccion());
            preparedStatement.setDate(7, new java.sql.Date(agente.getFechaNacimiento().getTime()));
            preparedStatement.setDate(8, new java.sql.Date(agente.getFechaExpedicion().getTime()));
            preparedStatement.setInt(9, agente.getCedula());

            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Eliminar un agente
    public boolean eliminarAgente(int cedula) {
        String query = "DELETE FROM agente WHERE cedula = ?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(query)) {
            preparedStatement.setInt(1, cedula);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     */
}
