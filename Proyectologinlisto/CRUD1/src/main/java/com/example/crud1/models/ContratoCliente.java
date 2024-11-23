package com.example.crud1.models;

public class ContratoCliente extends Contrato{
    private Cliente cliente;
    public ContratoCliente() {
        cliente = new Cliente();
    }
    public ContratoCliente(Cliente cliente) {}
    public Cliente getCliente() {return this.cliente;
    }
    public void setCliente(Cliente cliente) {}
}
