package com.example.crud1.repositories;

import com.example.crud1.models.Ciudad;
import com.example.crud1.models.InmueblePropietario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CiudadRepository {
    private Connection conn;

    public CiudadRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Ciudad> list() {
        List<Ciudad> ciudades = new ArrayList<>();
        String query = "SELECT * FROM ciudad";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Ciudad ciudad = new Ciudad();
                ciudad.setCodigoCiudad(rs.getInt("codigoCiudad"));
                ciudad.setNombreCiudad(rs.getString("nombreCiudad"));
                ciudades.add(ciudad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ciudades;
    }
}
