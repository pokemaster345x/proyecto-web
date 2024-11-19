package com.example.crud1.repositories;

import com.example.crud1.models.Ciudad;
import com.example.crud1.models.Departamento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DptoRepository {
    private Connection conn;

    public DptoRepository(Connection conn) {
        this.conn = conn;
    }
/*CREATE TABLE departamento (
    codigoDepartamento INT,
    nombreDepartamento CHAR(30),
    codigoCiudad INT,
    PRIMARY KEY (codigoDepartamento),
    FOREIGN KEY (codigoCiudad)
    REFERENCES ciudad (codigoCiudad)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);*/
    public List<Departamento> list() {
        List<Departamento> departamentos = new ArrayList<>();
        String query = "SELECT * FROM departamento";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Departamento dpto = new Departamento();
                dpto.setCodigoDepartamento(rs.getInt("codigoDepartamento"));
                dpto.setNombreDepartamento(rs.getString("nombreDepartamento"));
                dpto.setCodigoCiudad(rs.getInt("codigoCiudad"));
                departamentos.add(dpto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departamentos;
    }
}
