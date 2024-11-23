package com.example.crud1.models;

public class ContratoPropietario extends Contrato{

    private float comision;
    private Propietario propietario;


    public ContratoPropietario() {
        propietario = new Propietario();

    }
    public Propietario getPropietario() {
        return propietario;
    }
    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
 
    public ContratoPropietario(float comision) {this.comision = comision;}

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }
}
