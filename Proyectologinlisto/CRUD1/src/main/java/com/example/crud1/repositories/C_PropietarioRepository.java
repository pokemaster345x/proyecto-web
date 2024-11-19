package com.example.crud1.repositories;

import com.example.crud1.models.ContratoCliente;
import com.example.crud1.models.ContratoPropietario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class C_PropietarioRepository {
    private Connection conn;

    public C_PropietarioRepository(Connection conn) {
        this.conn = conn;
    }

    public List<ContratoPropietario> list() {
        List<ContratoPropietario> c_propietarios = new ArrayList<>();
        String query = "SELECT * FROM contratoPropietario";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                ContratoPropietario c_propietario = new ContratoPropietario();
                c_propietario.setCodigo(rs.getInt("codigo"));
                c_propietario.setComision(rs.getFloat("comision"));
                c_propietarios.add(c_propietario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c_propietarios;
    }
}
