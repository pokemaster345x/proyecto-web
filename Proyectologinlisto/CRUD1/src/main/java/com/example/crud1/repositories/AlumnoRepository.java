package com.example.crud1.repositories;

import com.example.crud1.models.Alumno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoRepository {

    private Connection conn;
    private GeneroRepository generoRepository;

    public AlumnoRepository(Connection conn) {
        this.conn = conn;
        this.generoRepository = new GeneroRepository(this.conn);
    }

    public List<Alumno> list(){
        List<Alumno> alumnos = new ArrayList<>();
        try {
            Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Alumno");
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setNumero_carnet(rs.getInt("Numero_carnet"));
                alumno.setNombre(rs.getString("Nombre"));
                alumno.setApellido(rs.getString("Apellido"));
                alumno.setCorreo(rs.getString("Correo"));
                alumno.setTelefono(rs.getString("Telefono"));
                alumno.setDireccion(rs.getString("Direccion"));
    //                alumno.setGenero(this.generoRepository.getGeneroById(rs.getInt("idgenero")));
                alumnos.add(alumno);

            }
            st.close();
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    public Boolean create(Alumno alumno) {

        try {
            PreparedStatement ps = this.conn.prepareStatement(
            "INSERT INTO alumno(nombre,apellido,telefono,correo,direccion)  VALUES (?,?,?,?,?)");
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getTelefono());
            ps.setString(4, alumno.getCorreo());
            ps.setString(5, alumno.getDireccion());
//            ps.setInt(6,1);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Alumno getById(int numero_carnet){
        Alumno alumno = null;
        try {
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM alumno WHERE numero_carnet = ?");
            ps.setInt(1, numero_carnet);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setNumero_carnet(rs.getInt("Numero_carnet"));
                alumno.setNombre(rs.getString("Nombre"));
                alumno.setApellido(rs.getString("Apellido"));
                alumno.setCorreo(rs.getString("Correo"));
                alumno.setTelefono(rs.getString("Telefono"));
                alumno.setDireccion(rs.getString("Direccion"));
                ps.close();
            }
        }catch (SQLException e){

        }
        return alumno;
    }
    public Boolean update(Alumno alumno) {
        try{
            PreparedStatement ps = this.conn.prepareStatement("UPDATE alumno SET nombre = ?, apellido = ?, telefono = ?, correo = ?, direccion = ? WHERE numero_carnet = ?");
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getTelefono());
            ps.setString(4, alumno.getCorreo());
            ps.setString(5, alumno.getDireccion());
            ps.setInt(6, alumno.getNumero_carnet());
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean delete(int numero_carnet) {
        try{
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM alumno WHERE numero_carnet = ?");
            ps.setInt(1, numero_carnet);
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
