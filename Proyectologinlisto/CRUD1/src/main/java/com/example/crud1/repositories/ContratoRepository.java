package com.example.crud1.repositories;

import com.example.crud1.models.Agente;
import com.example.crud1.models.Contrato;
import com.example.crud1.models.ContratoCliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContratoRepository {
    private Connection conn;

    public ContratoRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Contrato> list() {
        List<Contrato> contratos = new ArrayList<>();
        String query = "SELECT * FROM contrato";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Contrato contrato = new Contrato();
                contrato.setCodigo(rs.getInt("codigo"));
                contrato.setValor(rs.getFloat("valor"));
                contrato.setTipoContrato(rs.getString("tipoContrato"));
                contrato.setDescripcion(rs.getString("descripcion"));
                contrato.setFechaCreacion(rs.getDate("fechaCreacion"));
                contrato.setFechaExpiracion(rs.getDate("fechaExpiracion"));
                contrato.setCedula(rs.getInt("cedula"));
                contratos.add(contrato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratos;
    }
}
