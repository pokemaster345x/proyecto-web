package com.example.crud1.models;

public class VinculaPropietario {

    private int codigoInmueble;
    private int codigoContratoPropietario;
    private int cedulaProp;

    public VinculaPropietario() {}
    public VinculaPropietario(int codigoInmueble, int codigoContratoPropietario, int cedulaProp) {
        this.codigoInmueble = codigoInmueble;
        this.codigoContratoPropietario = codigoContratoPropietario;
        this.cedulaProp = cedulaProp;
    }

    public int getCodigoInmueble() {
        return codigoInmueble;
    }

    public void setCodigoInmueble(int codigoInmueble) {
        this.codigoInmueble = codigoInmueble;
    }

    public int getCodigoContratoPropietario() {
        return codigoContratoPropietario;
    }

    public void setCodigoContratoPropietario(int codigoContratoPropietario) {
        this.codigoContratoPropietario = codigoContratoPropietario;
    }

    public int getCedulaProp() {
        return cedulaProp;
    }

    public void setCedulaProp(int cedulaProp) {
        this.cedulaProp = cedulaProp;
    }
}
