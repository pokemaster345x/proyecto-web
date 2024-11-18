package com.example.crud1.services;

import com.example.crud1.models.Alumno;
import com.example.crud1.repositories.AlumnoRepository;
import com.example.crud1.utils.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AlumnoService {

    private AlumnoRepository alumnoRepository;
    private Connection conn;

    public AlumnoService(){
        this.conn = ConnectionDatabase.getConnection();
        this.alumnoRepository = new AlumnoRepository(this.conn);
    }

    public List<Alumno> getAlumnos(){
        //Enviar correos, crear un archivo
     return this.alumnoRepository.list();
    }

    public Boolean createAlumno(Alumno alumno){

        return this.alumnoRepository.create(alumno);
    }

    public Alumno getById(int numero_carnet){

        return this.alumnoRepository.getById(numero_carnet);
    }

    public Boolean update(Alumno alumno){
        return this.alumnoRepository.update(alumno);
    }
    public Boolean delete(int numero_carnet){
        return this.alumnoRepository.delete(numero_carnet);

    }

}
