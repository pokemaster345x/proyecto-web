package com.example.crud1.controllers;


import com.example.crud1.models.Contrato;
import com.example.crud1.models.ContratoPropietario;
import com.example.crud1.repositories.ContratoPropietarioRepository;
import com.example.crud1.services.ContratoPropietarioService;
import com.example.crud1.utils.ConnectionDatabase;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.util.List;
import java.io.Serializable;
import java.sql.Connection;

@Named("contratopropietariobean")
@SessionScoped

public class ContratoPropietarioBean implements Serializable {

    private ContratoPropietarioService contratoService;
    private List<ContratoPropietario> Contratos;
    private ContratoPropietario contratoPropietario;

    public ContratoPropietarioBean() {
        this.contratoService = new ContratoPropietarioService();
        listarContratos();
    }

    public void listarContratos() {
        System.out.println("Listando clientes");
        this.Contratos = this.contratoService.getContratos();
    }

    public List<ContratoPropietario> getContratos() {
        return Contratos;
    }

    public void setContratos(List<ContratoPropietario> Contratos) {
        this.Contratos = Contratos;
    }

    public ContratoPropietario getContratoPropietario() {
        return contratoPropietario;
    }

    public void setContratoPropietario(ContratoPropietario contratoPropietario) {
        this.contratoPropietario = contratoPropietario;
    }

    public String createContrato() {
        System.out.println("Criando cliente");
        this.contratoPropietario = new ContratoPropietario();
        return "/crud-contratos/create-edit-prop.xhtml?faces-redirect=true";
    }

    public String create() {
        this.contratoService.createContratoPropietario(this.contratoPropietario);
        listarContratos();
        return "/crud-contratos/listar-contrato-propietario.xhtml?faces-redirect=true";
    }

    public String edit(int codigo) {
        this.contratoPropietario = contratoService.getById(codigo);
        return "/crud-contratos/create-edit-prop.xhtml?faces-redirect=true";
    }

    public String delete(int cedula) {
        contratoService.delete(cedula);
        listarContratos();
        return "/crud-contratos/listar-contrato-propietario.xhtml?faces-redirect=true";
    }

    public String update() {
        contratoService.update(this.contratoPropietario);
        listarContratos();
        return "/crud-contratos/listar-contrato-propietario.xhtml?faces-redirect=true";
    }

}