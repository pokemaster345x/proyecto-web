package com.example.crud1.models;

public class VinculaCliente {

    private int codigoInmueble;
    private int codigoContratoCliente;
    private int cedulaCliente;

    public VinculaCliente() { }
    public VinculaCliente(int codigoInmueble, int codigoContratoCliente, int cedulaCliente) {
        this.codigoInmueble = codigoInmueble;
        this.codigoContratoCliente = codigoContratoCliente;
        this.cedulaCliente = cedulaCliente;
    }

    public int getCodigoInmueble() {
        return codigoInmueble;
    }

    public void setCodigoInmueble(int codigoInmueble) {
        this.codigoInmueble = codigoInmueble;
    }

    public int getCodigoContratoCliente() {
        return codigoContratoCliente;
    }

    public void setCodigoContratoCliente(int codigoContratoCliente) {
        this.codigoContratoCliente = codigoContratoCliente;
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }
}
