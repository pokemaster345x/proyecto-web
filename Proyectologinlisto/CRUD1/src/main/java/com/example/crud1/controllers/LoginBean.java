package com.example.crud1.controllers;

import com.example.crud1.utils.ConnectionDatabase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

    @Inject
    private SessionBean sessionBean;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /* loguin directamente con la bd del proyecto
    public String login() {
        Connection connection = null;
        try {
            // Cambiar siempre para que se conecte a la bd
            String url = "jdbc:sqlite:C:/Users/ekava/OneDrive/Documentos/GitHub/proyecto-web/Proyectologinlisto/CRUD1/alumnos.db";
            connection = DriverManager.getConnection(url);

            String sql = "SELECT * FROM agente WHERE loguin = ? AND contrasena = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                sessionBean.setUsername(username);
                String cedula = resultSet.getString("cedula"); // Recuperamos la cedula del ResultSet
                sessionBean.setCedula(cedula);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userInSession", cedula);
                return "/home?faces-redirect=true";
            } else {
                return "/login?faces-redirect=true&error=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "login.xhtml?faces-redirect=true&error=true";

        } finally {
            // Cerrar la conexión en el bloque finally para asegurar que se cierre siempre
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    */
    public String login() {
        Connection connection = null;
        try {
            // Usar la clase ConnectionDatabase para obtener la conexión
            connection = ConnectionDatabase.getConnection();

            if (connection == null) {
                throw new Exception("No se pudo establecer la conexión con la base de datos.");
            }

            String sql = "SELECT * FROM agente WHERE loguin = ? AND contrasena = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                sessionBean.setUsername(username);
                String cedula = resultSet.getString("cedula"); // Recuperamos la cedula del ResultSet
                sessionBean.setCedula(cedula);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userInSession", cedula);
                return "/home?faces-redirect=true";
            } else {
                return "/login?faces-redirect=true&error=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "login.xhtml?faces-redirect=true&error=true";

        } finally {
            // Cerrar la conexión en el bloque finally para asegurar que se cierre siempre
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

