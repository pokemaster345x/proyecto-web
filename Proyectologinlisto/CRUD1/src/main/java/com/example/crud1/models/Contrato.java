package com.example.crud1.models;

import java.util.Date;

public class Contrato {

    private int codigo;
    private float valor;
    private String tipoContrato;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaExpiracion;
    private int cedula;
    private Inmueble inmueble;
    private  Agente agente;


    public Contrato() {
        inmueble = new Inmueble();
        agente = new Agente();
    }
    public Contrato(int codigo, float valor, String tipoContrato, String descripcion, Date fechaCreacion, Date fechaExpiracion, int cedula, Inmueble inmueble, Agente agente) {
        this.codigo = codigo;
        this.valor = valor;
        this.tipoContrato = tipoContrato;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.inmueble = inmueble;
        this.agente = agente;
        this.cedula = cedula;
    }

    public Agente getAgente(){
        return agente;
    }
    public void setAgente(Agente agente){
        this.agente = agente;
    }
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
    public Inmueble getInmueble() {return inmueble;}

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
}
