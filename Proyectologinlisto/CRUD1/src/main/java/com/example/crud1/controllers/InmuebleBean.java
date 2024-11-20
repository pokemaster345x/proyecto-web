package com.example.crud1.controllers;
import com.example.crud1.models.*;
import com.example.crud1.repositories.InmuebleRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.example.crud1.services.InmuebleService;
import jdk.jfr.Name;

@SessionScoped
@Named("inmuebleBean")

public class InmuebleBean implements Serializable {
    private boolean editando = false;
    private Inmueble inmueble;
    private InmuebleService inmuebleService;
    private List <Inmueble> inmuebles;
    private boolean banderita=false;//para saber si es de la inmobiliaria o de cliente
    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }
    public InmuebleBean(){
        this.inmuebleService = new InmuebleService();
        ListarInmueble();
    }
    public void ListarInmueble(){
        this.inmuebles=this.inmuebleService.getInmuebles();
    }
    public List<Inmueble> getInmuebles() {
        return inmuebles;
    }
    public Inmueble getInmueble() {
        return inmueble;
    }
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
    public String createInmueble() {
        boolean existeInmueble = inmuebleService.existeInmueble(this.inmueble); // Método para verificar existencia

        if (!existeInmueble) {
            // Lógica para decidir si el inmueble es de un propietario o de la inmobiliaria
            if (banderita) {
                // Crear inmueble de propietario
                InmueblePropietario inmueblePropietario = new InmueblePropietario(this.inmueble);
                inmuebleService.createPropietario(inmueblePropietario);
            } else {
                // Crear inmueble de la inmobiliaria
                InmuebleInmobiliaria inmuebleInmobiliaria = new InmuebleInmobiliaria(this.inmueble);
                inmuebleService.createInmobiliaria(inmuebleInmobiliaria);
            }

            ListarInmueble(); // Actualiza la lista de inmuebles
            return "/crud-inmuebles/listar-inmuebles?faces-redirect=true"; // Redirige a la lista
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Código ya registrado", "Este código de inmueble ya está registrado")
            );
            return null;
        }
    }

}
