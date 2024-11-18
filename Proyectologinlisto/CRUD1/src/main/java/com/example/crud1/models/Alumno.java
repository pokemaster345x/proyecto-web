package com.example.crud1.models;

public class Alumno {

    private int numero_carnet;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String direccion;
    private Genero genero;

    public Alumno() {}

    public Alumno(int numero_carnet, String nombre, String apellido, String correo, String telefono, String direccion, Genero genero) {
        this.numero_carnet = numero_carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.genero = genero;
    }

    public int getNumero_carnet() {
        return numero_carnet;
    }

    public void setNumero_carnet(int numero_carnet) {
        this.numero_carnet = numero_carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
