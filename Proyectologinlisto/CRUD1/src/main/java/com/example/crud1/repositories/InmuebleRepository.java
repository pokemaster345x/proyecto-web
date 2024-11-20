package com.example.crud1.repositories;

import com.example.crud1.models.Inmueble;
import com.example.crud1.models.Propietario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InmuebleRepository {

    private Connection conn;

    public InmuebleRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Inmueble> list() {
        List<Inmueble> inmuebles = new ArrayList<>();
        String query = "SELECT * FROM inmueble";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Inmueble inmueble = new Inmueble();
                inmueble.setCodigo(rs.getInt("codigo"));
                inmueble.setTipoInmueble(rs.getString("tipoInmueble"));
                inmueble.setDescripcionInmueble(rs.getString("DescripcionInmueble"));
                inmueble.setModalidadComercio(rs.getString("ModalidadComercio"));
                inmueble.getCiudad().setCodigoCiudad(rs.getInt("codigoCiudad"));
                inmueble.setDireccion(rs.getString("direccion"));
                inmueble.setTamanoMetro(rs.getFloat("tamanoMetro"));
                inmueble.setCantBanos(rs.getInt("CantBanos"));
                inmueble.setFotoInmueble(rs.getString("FotoInmueble"));
                inmueble.setEstado(rs.getBoolean("estado"));
                inmueble.setPrecioFinal(rs.getFloat("precioFinal"));
                inmuebles.add(inmueble);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inmuebles;
    }
}
