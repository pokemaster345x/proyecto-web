package com.example.crud1.controllers;

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

    public String login() {
        try {
            // cambiar siempre para que se conecte a la bd
            String url = "jjdbc:sqlite:C:/Users/ekava/OneDrive/Documentos/GitHub/proyecto-web/Proyectologinlisto/CRUD1/alumnos.db";
            Connection connection = DriverManager.getConnection(url);

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
        }
    }
}
