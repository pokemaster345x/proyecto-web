package com.example.crud1.repositories;

import com.example.crud1.models.Contrato;
import com.example.crud1.models.VinculaPropietario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vincula_PRepository {
    private Connection conn;

    public Vincula_PRepository(Connection conn) {
        this.conn = conn;
    }

    public List<VinculaPropietario> list() {
        List<VinculaPropietario> vinculaPropietarios = new ArrayList<>();
        String query = "SELECT * FROM vinculaPropietario";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                VinculaPropietario vp = new VinculaPropietario();
                vp.setCodigoInmueble(rs.getInt("codigoInmueble"));
                vp.setCodigoContratoPropietario(rs.getInt("codigoContratoPropietario"));
                vp.setCedulaProp(rs.getInt("cedulaProp"));
                vinculaPropietarios.add(vp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vinculaPropietarios;
    }
}
