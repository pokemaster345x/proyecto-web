package com.example.crud1.controllers;

import com.example.crud1.models.Agente;
import com.example.crud1.services.AgenteService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("agenteController")
@SessionScoped
public class AgenteController implements Serializable {

    private AgenteService agenteService;
    private List<Agente> agentes;
    private Agente agente;

    public AgenteController() {
        this.agenteService = new AgenteService();
        listarAgentes();
    }

    // Método para listar todos los agentes
    public void listarAgentes() {
        this.agentes = this.agenteService.getAgentes();
    }

    // Getters y setters para la lista de agentes
    public List<Agente> getAgentes() {
        return agentes;
    }

    public void setAgentes(List<Agente> agentes) {
        this.agentes = agentes;
    }

    // Crear un nuevo agente
    public String createAgente() {
        this.agente = new Agente();
        return "/agente/create_edit.xhtml?faces-redirect=true";
    }

    // Getters y setters para un agente individual
    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    // Guardar un nuevo agente en la base de datos
    public String create() {
        this.agenteService.createAgente(this.agente);
        listarAgentes();
        return "/agente/list.xhtml?faces-redirect=true";
    }

    // Ver los detalles de un agente por su cédula
    public String detail(int cedula) {
        this.agente = this.agenteService.getById(cedula);
        return "/agente/create_edit.xhtml?faces-redirect=true";
    }

    // Actualizar la información de un agente
    public String update() {
        this.agenteService.updateAgente(this.agente);
        listarAgentes();
        return "/agente/list.xhtml?faces-redirect=true";
    }

    // Eliminar un agente por su cédula
    public void delete(int cedula) {
        this.agenteService.deleteAgente(cedula);
        listarAgentes();
    }
}
