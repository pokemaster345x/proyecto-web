package com.example.crud1.repositories;

import com.example.crud1.models.InmuebleInmobiliaria;
import com.example.crud1.models.InmueblePropietario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class I_PropietarioRepository {

    private Connection conn;

    public I_PropietarioRepository(Connection conn) {
        this.conn = conn;
    }

    public List<InmueblePropietario> list() {
        List<InmueblePropietario> inmueblePropietarios = new ArrayList<>();
        String query = "SELECT * FROM inmueblePropietario";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                InmueblePropietario inmuebleP = new InmueblePropietario();
                inmuebleP.setCodigo(rs.getInt("codigo"));
                inmueblePropietarios.add(inmuebleP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inmueblePropietarios;
    }
}
