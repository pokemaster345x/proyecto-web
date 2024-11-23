package com.example.crud1.controllers;

import com.example.crud1.models.ContratoCliente;
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
    private List<ContratoCliente> contratoClientes;
    private ContratoCliente contratoCliente;

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public ContratoBean() {
        this.contratoService = new ContratoService();
        listarContratosClientes();
    }

    public void listarContratosClientes() {
        this.contratoClientes = this.contratoService.getContratoClientes();
    }

    public List<ContratoCliente> getContratoClientes() {
        return contratoClientes;
    }

    public ContratoCliente getContratoCliente() {
        return contratoCliente;
    }

    public void setContratoCliente(ContratoCliente contratoCliente) {
        this.contratoCliente = contratoCliente;
    }

    public String createContratoCliente() {
        this.editando = false;
        this.contratoCliente = new ContratoCliente();
        return "/crud-contratos/agregar_editar-contratos?faces-redirect=true";
    }

    public String detail(int codigo) {
        this.editando = true;
        this.contratoCliente = this.contratoService.getByIdContratoCliente(codigo);
        return "/crud-contratos/agregar_editar-contratos?faces-redirect=true";
    }

    public String create() {
        boolean siexiste = contratoService.existeContrato(this.contratoCliente.getCodigo());
        if (!siexiste) {
            this.contratoService.createContratoCliente(this.contratoCliente);
            listarContratosClientes();
            return "/crud-contratos/listar-contratos?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Código Ya registrado", "ESTE CÓDIGO YA ESTÁ REGISTRADO"));
            return null;
        }
    }

    public String update() {
        this.contratoService.updateContrato(this.contratoCliente);
        listarContratosClientes();
        return "/crud-contratos/listar-contratos?faces-redirect=true";
    }

    public void delete(int codigo) {
        this.contratoService.deleteContrato(codigo);
        listarContratosClientes();
    }

    private int codigoBuscar;

    public int getCodigoBuscar() {
        return codigoBuscar;
    }

    public void setCodigoBuscar(int codigoBuscar) {
        this.codigoBuscar = codigoBuscar;
    }

    public void buscarContratoPorCodigo() {
        this.contratoCliente = this.contratoService.getByIdContratoCliente(this.codigoBuscar);
        if (this.contratoCliente == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No encontrado", "No se encontró un contrato con ese código."));
        }
    }
}