package com.example.crud1.repositories;

import com.example.crud1.models.Contrato;
import com.example.crud1.models.ContratoCliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class C_ClienteRepository {
    private Connection conn;

    public C_ClienteRepository(Connection conn) {
        this.conn = conn;
    }

    public List<ContratoCliente> list() {
        List<ContratoCliente> c_clientes = new ArrayList<>();
        String query = "SELECT * FROM contratoCliente";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                ContratoCliente c_cliente = new ContratoCliente();
                c_cliente.setCodigo(rs.getInt("codigo"));
                c_clientes.add(c_cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c_clientes;
    }
}
