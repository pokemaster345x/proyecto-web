package com.example.crud1.models;

public class VinculaCliente {

    private Inmueble inmueble;
    private Cliente cliente;
    private ContratoCliente contratoCliente;

    public VinculaCliente() { }

    public VinculaCliente(Inmueble inmueble, ContratoCliente contratoCliente, Cliente cliente) {
        this.inmueble = inmueble;
        this.contratoCliente = contratoCliente;
        this.cliente = cliente;
    }


    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ContratoCliente getContratoCliente() {
        return contratoCliente;
    }

    public void setContratoCliente(ContratoCliente contratoCliente) {
        this.contratoCliente = contratoCliente;
    }
}
