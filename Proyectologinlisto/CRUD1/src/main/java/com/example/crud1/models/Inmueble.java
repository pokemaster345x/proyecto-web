package com.example.crud1.models;

import java.util.Date;

public class Inmueble {

    private int codigo;
    private String tipoInmueble;
    private String DescripcionInmueble;
    private String ModalidadComercio;
    private Ciudad ciudad;
    private String direccion;
    private float tamanoMetro;
    private int CantBanos;
    private String FotoInmueble;
    private boolean estado;
    private float precioFinal;

    public Inmueble() {
    }

    public Inmueble(int codigo, String tipoInmueble, String DescripcionInmueble, String ModalidadComercio, int codigoCiudad, String direccion, float tamanoMetro, int CantBanos, String FotoInmueble, boolean estado, float precioFinal) {
        this.codigo = codigo;
        this.tipoInmueble = tipoInmueble;
        this.DescripcionInmueble = DescripcionInmueble;
        this.ModalidadComercio = ModalidadComercio;
        this.ciudad = new Ciudad();
        this.direccion = direccion;
        this.tamanoMetro = tamanoMetro;
        this.CantBanos = CantBanos;
        this.FotoInmueble = FotoInmueble;
        this.estado = estado;
        this.precioFinal = precioFinal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public String getDescripcionInmueble() {
        return DescripcionInmueble;
    }

    public void setDescripcionInmueble(String descripcionInmueble) {
        DescripcionInmueble = descripcionInmueble;
    }

    public String getModalidadComercio() {
        return ModalidadComercio;
    }

    public void setModalidadComercio(String modalidadComercio) {
        ModalidadComercio = modalidadComercio;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad codigoCiudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getTamanoMetro() {
        return tamanoMetro;
    }

    public void setTamanoMetro(float tamanoMetro) {
        this.tamanoMetro = tamanoMetro;
    }

    public int getCantBanos() {
        return CantBanos;
    }

    public void setCantBanos(int cantBanos) {
        CantBanos = cantBanos;
    }

    public String getFotoInmueble() {
        return FotoInmueble;
    }

    public void setFotoInmueble(String fotoInmueble) {
        FotoInmueble = fotoInmueble;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(float precioFinal) {
        this.precioFinal = precioFinal;
    }
}
