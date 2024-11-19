package com.example.crud1.models;

public class ContratoPropietario {

    private int codigo;
    private float comision;

    public ContratoPropietario() {}
    public ContratoPropietario(int codigo, float comision) {}

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }
}
