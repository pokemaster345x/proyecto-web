package com.example.crud1.repositories;

import com.example.crud1.models.VinculaCliente;
import com.example.crud1.models.VinculaPropietario;

import java.sql.*;
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

    public List<VinculaPropietario> listVinculaPropietario() {
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

    public boolean createVCC(int codigoInmueble, int codigoContratoCliente, int cedulaCliente){
        String query = "INSERT INTO vinculaCliente (codigoInmueble, codigoContratoCliente, cedulaCliente) VALUES (?,?,?)";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, codigoInmueble);
            ps.setInt(2, codigoContratoCliente);
            ps.setInt(3, cedulaCliente);
            ps.executeUpdate();
            ps.close();
            } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createVCP(int codigoInmueble, int codigoContratoPropietario, int cedulaProp){
        String query = "INSERT INTO vinculaPropietario (codigoInmueble, codigoContratoPropietario, cedulaProp) VALUES VALUES (?,?,?)";
        try {
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, codigoInmueble);
            ps.setInt(2, codigoContratoPropietario);
            ps.setInt(3, cedulaProp);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public
}
