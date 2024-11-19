package com.example.crud1.models;

import java.util.Date;

public class Agente {

    private int cedula;
    private String celular;
    private String nombreCompleto;
    private Date fechaNacimiento;
    private Date fechaExpedicion;
    private String loguin;
    private String contrasena;
    private String correoElectronico;
    private String direccion;

    // Constructor vacío
    public Agente() {}

    // Constructor con parámetros
    public Agente(int cedula, String celular, String nombreCompleto, Date fechaNacimiento,
                  Date fechaExpedicion, String loguin, String contrasena,
                  String correoElectronico, String direccion) {
        this.cedula = cedula;
        this.celular = celular;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaExpedicion = fechaExpedicion;
        this.loguin = loguin;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }

    // Getters y setters
    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getLoguin() {
        return loguin;
    }

    public void setLoguin(String loguin) {
        this.loguin = loguin;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
