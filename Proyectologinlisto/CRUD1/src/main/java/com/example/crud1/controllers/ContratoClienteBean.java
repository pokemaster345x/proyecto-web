package com.example.crud1.controllers;

import com.example.crud1.models.ContratoCliente;
import com.example.crud1.models.ContratoPropietario;
import com.example.crud1.services.ContratoClienteService;
import com.example.crud1.services.ContratoPropietarioService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import javax.naming.ldap.Control;
import java.io.Serializable;
import java.util.List;

@Named("contratoclientebean")
@SessionScoped

public class ContratoClienteBean implements Serializable {

    private ContratoClienteService contratoService;
    private List<ContratoCliente> Contratos;
    private ContratoCliente contratoCliente;

    public ContratoClienteBean() {
        this.contratoService = new ContratoClienteService();
        listarContratos();
    }

    public void listarContratos() {
        System.out.println("Listando contratos");
        this.Contratos = this.contratoService.getContratos();
    }

    public List<ContratoCliente> getContratos() {
        return Contratos;
    }

    public void setContratos(List<ContratoCliente> Contratos) {
        this.Contratos = Contratos;
    }

    public ContratoCliente getContratoCliente() {
        return contratoCliente;
    }

    public void contratoCliente(ContratoCliente ContratoCliente) {
        this.contratoCliente = ContratoCliente;
    }

    public String createContrato() {
        this.contratoCliente = new ContratoCliente();
        return "/crud-contratos/create-edit-cliente.xhtml?faces-redirect=true";
    }

    public String create() {
        this.contratoService.createContratoPropietario(this.contratoCliente);
        listarContratos();
        return "/crud-contratos/listar-contrato-cliente.xhtml?faces-redirect=true";
    }

    public String edit(int codigo) {
        this.contratoCliente = contratoService.getById(codigo);
        return "/crud-contratos/create-edit-cliente.xhtml?faces-redirect=true";
    }

    public String delete(int cedula) {
        contratoService.delete(cedula);
        listarContratos();
        return "/crud-contratos/listar-contrato-cliente.xhtml?faces-redirect=true";
    }

    public String update() {
        contratoService.update(this.contratoCliente);
        listarContratos();
        return "/crud-contratos/listar-contrato-cliente.xhtml?faces-redirect=true";
    }
    private int codigobuscar;
    public int getCodigobuscar() {
        return codigobuscar;
    }
    public void setCodigobuscar(int codigobuscar) {
        this.codigobuscar = codigobuscar;
    }
    public void buscarContratoPorCodigo() {
        this.contratoCliente = contratoService.getById(codigobuscar);
    }
}
