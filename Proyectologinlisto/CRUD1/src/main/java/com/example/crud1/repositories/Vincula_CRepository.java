package com.example.crud1.repositories;

import com.example.crud1.models.VinculaCliente;
import com.example.crud1.models.VinculaPropietario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vincula_CRepository {
    private Connection conn;

    public Vincula_CRepository(Connection conn) {
        this.conn = conn;
    }

    public List<VinculaCliente> list() {
        List<VinculaCliente> vinculaClientes = new ArrayList<>();
        String query = "SELECT * FROM vinculaCliente";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                VinculaCliente vc = new VinculaCliente();
                vc.setCodigoInmueble(rs.getInt("codigoInmueble"));
                vc.setCodigoContratoCliente(rs.getInt("codigoContratoCliente"));
                vc.setCedulaCliente(rs.getInt("cedulaCliente"));
                vinculaClientes.add(vc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vinculaClientes;
    }
}
