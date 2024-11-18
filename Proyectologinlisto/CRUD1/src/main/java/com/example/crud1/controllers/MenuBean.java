package com.example.crud1.controllers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("menuBean")
@SessionScoped
public class MenuBean implements Serializable {
    @Inject
    private SessionBean sessionBean;

    private String rol;

    public boolean getIsAdmin() {
        return "AdminMaster".equals(sessionBean.getUsername());
    }

    public String getRol() {
        // Calcula el rol basado en el usuario
        if ("AdminMaster".equals(sessionBean.getUsername())) {
            return "Admin";
        } else {
            return "Agente";
        }
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
