package com.example.crud1.models;

import java.util.Date;

public class InmuebleInmobiliaria extends Inmueble{

    private Date fechaAdquisicion;

    public InmuebleInmobiliaria() { }
    public InmuebleInmobiliaria(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }
}
