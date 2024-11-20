package com.example.crud1.controllers;

import com.example.crud1.models.Agente;
import com.example.crud1.models.Contrato;
import com.example.crud1.services.ContratoService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
@Named("contratoBean")
@SessionScoped
public class ContratoBean implements Serializable {
    private boolean editando = false;
    private ContratoService contratoService;
    private Contrato contrato;
    private List<Contrato> contratos;
    public boolean isEditando() {
        return editando;
    }
    public void setEditando(boolean editando) {
        this.editando = editando;
    }
    public ContratoBean(){
        this.contratoService = new ContratoService();
        listarContrato();
    }
    public void listarContrato(){
        this.contratos =this.contratoService.getContrato();
    }
    public List<Contrato> getContratos() {
        return contratos;
    }
    public Contrato getContrato() {
        return contrato;
    }
    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    public String createContrato(){
        this.editando = false;
        this.contrato = new Contrato();
        return "/crud-contratos/agregar_editar-contrato?faces-redirect=true";
    }
    public String detail(int codigo){
        this.editando = true;
        this.contrato=this.contratoService.getById(codigo);
        return "/crud-contratos/agregar_editar-contrato?faces-redirect=true";
    }
    public String create(){
        boolean siexiste=contratoService.existeContrato(this.contrato);
        if(!siexiste){
            this.contratoService.createContrato(this.contrato);
            listarContrato();
            return "/crud-contratos/listar-contratos?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Codigo Ya registrado","ESTE CODIGO YA ESTA REGISTRADO"));
            return null;
        }
    }
    public String update(){
        this.contratoService.update(contrato);
        listarContrato();
        return "/crud-contratos/listar-contratos?faces-redirect=true";
    }
    public void delete(int codigo){
        this.contratoService.delete(codigo);
        listarContrato();
    }
    private int codigoBuscar;
    private int getCodigoBuscar() {
        return codigoBuscar;
    }
    public void setCodigoBuscar(int codigoBuscar) {
        this.codigoBuscar = codigoBuscar;
    }
    public void BuscarContratoPorCodigo(){
        this.contrato =this.contratoService.getById(codigoBuscar);
        if(this.contrato==null){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "No encontrado", "No se encontró un agente con esa cédula."));
        }
    }

}
