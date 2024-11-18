package com.example.crud1.controllers;


import com.example.crud1.models.Alumno;
import com.example.crud1.services.AlumnoService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jdk.jfr.Name;

import java.io.Serializable;
import java.util.List;

@Named("alumnoBean")
@SessionScoped
public class AlumnoBean implements Serializable {

    private AlumnoService alumnoService;
    private String helloWord = "Hola mundo";
    private List<Alumno> alumnos;
    private Alumno alumno;

    public AlumnoBean() {
        this.alumnoService = new AlumnoService();
        listarAlumnos();
    }

    public void listarAlumnos() {
        this.alumnos = this.alumnoService.getAlumnos();
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public String getHelloWord() {
        return helloWord;
    }

    public void setHelloWord(String helloWord) {
        this.helloWord = helloWord;
    }

    public String createAlumno(){
        this.alumno = new Alumno();

        return "/alumno/create_edit.xhtml?faces-redirect=true";
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String create(){
        this.alumnoService.createAlumno(this.alumno);
        listarAlumnos();
        return "/alumno/list.xhtml?faces-redirect=true";
    }

    public String detail(int numero_carnet){
        this.alumno = this.alumnoService.getById(numero_carnet);
        listarAlumnos();
        return "/alumno/create_edit.xhtml?faces-redirect=true";
    }

    public String update(){
        this.alumnoService.update(this.alumno);
        listarAlumnos();
        return "/alumno/list?faces-redirect=true";
    }

    public void delete(int numero_carnet){

        this.alumnoService.delete(numero_carnet);
        listarAlumnos();
    }

}
