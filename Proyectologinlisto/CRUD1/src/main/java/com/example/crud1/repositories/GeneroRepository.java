package com.example.crud1.repositories;

import com.example.crud1.models.Genero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroRepository {

    private Connection con;

    public GeneroRepository(Connection con) {this.con = con;}

    public Genero getGeneroById(int idgenero) {

        Genero genero = null;
        try {
            String query = "SELECT * FROM genero WHERE idgenero = ?";
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1, idgenero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                genero = new Genero(rs.getInt("idgenero"), rs.getString("descripcion"));
            }
            ps.close();
            rs.close();
        }catch (SQLException e) {}

        return genero;
    }

}
