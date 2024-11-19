package com.example.crud1.models;

import java.util.Date;

public class InmueblePropietario {

    private int codigo;

    public InmueblePropietario(){}
    
    public InmueblePropietario(int codigo){
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
