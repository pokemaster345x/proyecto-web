package com.example.crud1.controllers;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.flow.builder.ReturnBuilder;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Objects;

@Named("navigationcontrollers")
@RequestScoped
public class NavigationControllers implements Serializable {

    public String goToPage(String page,String TipodeCrud){
        if (Objects.equals(page, "home")) {
            return "/home?faces-redirect=true";
        }else {
            String pepe="/crud-"+TipodeCrud+"/" + page + "?faces-redirect=true";
            System.out.println(pepe);
            return pepe;
        }
    }
}
