package com.example.crud1.controllers;

import com.example.crud1.models.Cliente;
import com.example.crud1.services.AgenteService;
import com.example.crud1.services.ClienteService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;

import java.io.Serializable;
import java.util.List;

@Named("clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {
    private Boolean editando=false;
    private ClienteService clienteService;
    private List<Cliente> clientes;
    private Cliente cliente;
    public boolean isEditando() {
        return editando;
    }
    public void setEditando(boolean editando) {
        this.editando = editando;
    }
    public ClienteBean(){
        this.clienteService = new ClienteService();
        listarClientes();
    }
    public void listarClientes(){
        this.clientes=this.clienteService.getClientes();
    }
    public List<Cliente> getClientes() {
        return clientes;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public String createCliente(){
        this.editando=false;
        this.cliente=new Cliente();
        return "/crud-clientes/agregar_editar-clientes?faces-redirect=true";
    }
    public String detail(int cedula){
        this.editando=true;
        this.cliente=this.clienteService.getbyID(cedula);
        return "/crud-clientes/agregar_editar-clientes?faces-redirect=true";
    }
    public String create(){
        boolean siexiste=clienteService.existeCliente(this.cliente);
        if(!siexiste){
            this.clienteService.createCliente(this.cliente);
            listarClientes();
            return "/crud-clientes/listar-clientes?faces-redirect=true";
        }else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Cedula Ya registrada","ESTE CEDULA YA ESTA REGISTRADA"));
            return null;
        }
    }
    public String update(){
        this.clienteService.update(cliente);
        listarClientes();
        return "/crud-clientes/listar-clientes?faces-redirect=true";
    }
    public void delete(int cedula){
        this.clienteService.delete(cedula);
        listarClientes();
    }
    private int cedulaBuscar;
    public int getCedulaBuscar(){
        return cedulaBuscar;
    }
    public void setCedulaBuscar(int cedula){
        this.cedulaBuscar=cedula;
    }
    public void buscarClientePorCedula() {
        this.cliente = this.clienteService.getbyID(this.cedulaBuscar);
        if (this.cliente == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "No encontrado", "No se encontró un cliente con esa cédula."));
        }
    }
}
