package com.example.crud1.controllers;
import com.example.crud1.models.*;
import com.example.crud1.repositories.InmuebleRepository;
import jakarta.enterprise.context.SessionScoped;

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
    private InmuebleService inmuebleService;
    private List<InmuebleInmobiliaria> inmobiliarias;
    private List<InmueblePropietario> propietarios;
    private InmuebleInmobiliaria inmobiliaria;
    private InmueblePropietario propietario;

    public InmuebleBean() {
        this.inmuebleService = new InmuebleService();
        listarInmueblesInmobiliarias();
        listarInmueblesPropietarios();
    }
    public void listarInmueblesInmobiliarias() {
        this.inmobiliarias=this.inmuebleService.ListInmobiliarias();
    }
    public void listarInmueblesPropietarios(){
        this.propietarios=this.inmuebleService.ListPropietarios();
    }

    public List<InmuebleInmobiliaria> getInmobiliarias() {
        return inmobiliarias;
    }

    public void setInmobiliarias(List<InmuebleInmobiliaria> inmobiliarias) {
        this.inmobiliarias = inmobiliarias;
    }

    public List<InmueblePropietario> getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(List<InmueblePropietario> propietarios) {
        this.propietarios = propietarios;
    }

    public InmuebleInmobiliaria getInmobiliaria() {
        return inmobiliaria;
    }

    public void setInmobiliaria(InmuebleInmobiliaria inmobiliaria) {
        this.inmobiliaria = inmobiliaria;
    }

    public InmueblePropietario getPropietario() {
        return propietario;
    }

    public void setPropietario(InmueblePropietario propietario) {
        this.propietario = propietario;
    }
    public String createI_Inmobiliaria() {
        this.inmobiliaria=new InmuebleInmobiliaria();
        return "/crud-inmuebles/agregar_editar-inmobiliaria?faces-redirect=true";
    }
    public String createI_Propietario() {
        this.propietario=new InmueblePropietario();
        return "/crud-inmuebles/agregar_editar-propietario?faces-redirect=true";
    }
    public String createInmobiliaria() {
        this.inmuebleService.createInmobiliaria(this.inmobiliaria);
        listarInmueblesInmobiliarias();
        return "/crud-inmuebles/list-inmobiliaria.xhtml?faces-redirect=true";
    }
    public String createPropietario() {
        this.inmuebleService.createPropietario(this.propietario);
        listarInmueblesPropietarios();
        return "/curd-inmuebles/list-propietario.xhtml?faces-redirect=true";
    }
    public String editInmobliaria(int codigo){
        this.inmobiliaria=inmuebleService.getbyIdI(codigo);
        return "/crud-inmuebles/agregar_editar-inmobiliaria?faces-redirect=true";
    }
    public String editPropietario(int codigo){
        this.propietario=inmuebleService.getbyIdP(codigo);
        return "/crud-inmuebles/agregar_editar-propietario?faces-redirect=true";
    }
    public String deleteInmobiliaria(int codigo){
        inmuebleService.delete(codigo);
        listarInmueblesInmobiliarias();
        return "/crud-inmuebles/list-inmobiliaria.xhtml?faces-redirect=true";
    }
    public String deletePropietario(int codigo){
        inmuebleService.delete(codigo);
        listarInmueblesPropietarios();
        return "/crud-inmuebles/list-propietario.xhtml?faces-redirect=true";
    }
    public String updateInmobiliaria(int codigo){
        inmuebleService.editInmobiliaria(this.inmobiliaria);
        listarInmueblesInmobiliarias();
        return  "/crud-inmuebles/list-inmobiliaria.xhtml?faces-redirect=true";
    }
    public String updatePropietario(int codigo){
        inmuebleService.editPropietario(this.propietario);
        listarInmueblesPropietarios();
        return  "/crud-inmuebles/list-propietario.xhtml?faces-redirect=true";
    }
}
