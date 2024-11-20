package com.example.crud1.models;

public class ContratoPropietario extends Contrato{

    private float comision;

    public ContratoPropietario() {}
    public ContratoPropietario(float comision) {}

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }
}
