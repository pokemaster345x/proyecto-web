package com.example.crud1.controllers;

import com.example.crud1.models.Agente;
import com.example.crud1.services.AgenteService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
@Named("agenteBean")
@SessionScoped
public class AgenteBean implements Serializable {
    private  boolean editando =false;
    private AgenteService agenteService;
    private List<Agente> agentes;
    private Agente agente;
    public boolean  isEditando(){
        return editando;
    }
    public void setEditando(boolean editando) {
        this.editando = editando;
    }
    public AgenteBean() {
        this.agenteService = new AgenteService();
        listarAgentes();
    }

    public void listarAgentes() {
        this.agentes = this.agenteService.getAgentes();
    }

    public List<Agente> getAgentes() {
        return agentes;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }
    public String createAgente(){
        this.editando =false;
        this.agente = new Agente();
        return "/crud-agentes/agregar_editar-agentes?faces-redirect=true";
    }
    public String detail(int cedula){
        this.editando =true;
        this.agente =this.agenteService.getById(cedula);
        return "/crud-agentes/agregar_editar-agentes?faces-redirect=true";
    }

    public String create(){
        boolean siexiste=agenteService.existeAgente(this.agente);
        if(!siexiste){
            this.agenteService.createAgente(this.agente);
            listarAgentes();
            return "/crud-agentes/listar-agentes?faces-redirect=true";
        }else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Cedula Ya registrada","ESTE CEDULA YA ESTA REGISTRADA"));
        return null;
        }
    }
    public String update(){
        this.agenteService.update(agente);
        listarAgentes();
        return "/crud-agentes/listar-agentes?faces-redirect=true";
    }
    public void delete(int cedula){
        this.agenteService.delete(cedula);
        listarAgentes();
    }
}
