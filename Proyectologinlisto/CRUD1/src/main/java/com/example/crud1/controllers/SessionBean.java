package com.example.crud1.controllers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ExternalContext;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionBean implements Serializable {

    private String username;
    private String cedula;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /*public void checkSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        if (username == null) {
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("no papito");
            }
        }
    }*/

    // Método para cerrar sesión
    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();

         /*try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/index.faces");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return "/login?faces-redirect=true";
    }

    /* Método para verificar si el usuario está autenticado
    public boolean isLoggedIn() {
        return username != null;
    }*/

}
