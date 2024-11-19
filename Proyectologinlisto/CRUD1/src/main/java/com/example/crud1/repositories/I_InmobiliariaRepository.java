package com.example.crud1.repositories;

import com.example.crud1.models.Inmueble;
import com.example.crud1.models.InmuebleInmobiliaria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class I_InmobiliariaRepository {
    private Connection conn;

    public I_InmobiliariaRepository(Connection conn) {
        this.conn = conn;
    }

    public List<InmuebleInmobiliaria> list() {
        List<InmuebleInmobiliaria> inmuebleInmobiliaria = new ArrayList<>();
        String query = "SELECT * FROM inmuebleInmobiliaria";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                InmuebleInmobiliaria inmuebleI = new InmuebleInmobiliaria();
                inmuebleI.setCodigo(rs.getInt("codigo"));
                inmuebleI.setFechaAdquisicion(rs.getDate("fechaAdquisicion"));
                inmuebleInmobiliaria.add(inmuebleI);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inmuebleInmobiliaria;
    }
}
