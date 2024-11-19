package com.example.crud1.models;

import java.util.Date;

public class InmuebleInmobiliaria {

    private int codigo;
    private Date fechaAdquisicion;

    public InmuebleInmobiliaria() { }
    public InmuebleInmobiliaria(int codigo, Date fechaAdquisicion) {
        this.codigo = codigo;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }
}
