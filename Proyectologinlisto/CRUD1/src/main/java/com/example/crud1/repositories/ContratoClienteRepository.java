package com.example.crud1.repositories;

import com.example.crud1.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratoClienteRepository {

    private Connection conn;

    public ContratoClienteRepository(Connection connection) {
        this.conn = connection;
    }

        public List<ContratoCliente> list() {
        List<ContratoCliente> contratos = new ArrayList<>();
        String query = "SELECT vc.cedulaCliente, co.*, vc.codigoInmueble from contrato co \n" +
                "JOIN contratoCliente cc \n" +
                "ON co.codigo = cc.codigo\n" +
                "JOIN vinculacliente vc \n" +
                "ON cc.codigo = vc.codigoContratoCliente";

        try (Statement st = this.conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                ContratoCliente contratocliente = new ContratoCliente();
                contratocliente.setCodigo(rs.getInt("codigo"));
                contratocliente.setFechaCreacion(rs.getDate("fechacreacion"));
                contratocliente.setTipoContrato(rs.getString("tipocontrato"));
                contratocliente.setFechaExpiracion(rs.getDate("fechaexpiracion"));
                contratocliente.setValor(rs.getFloat("valor"));
                contratocliente.setDescripcion(rs.getString("descripcion"));

                Agente agente = new Agente();
                agente.setCedula(rs.getInt("cedula"));
                contratocliente.setAgente(agente);

                Cliente cliente = new Cliente();
                cliente.setCedula(rs.getInt("cedulaCliente"));
                contratocliente.setCliente(cliente);

                contratos.add(contratocliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return contratos;
    }

    public Boolean create(ContratoCliente contrato) {

        String query = "INSERT INTO contrato (codigo, valor, tipoContrato, descripcion, fechaCreacion, fechaExpiracion, cedula) VALUES  (?, ?, ?, ?, ?, ?, ?)";
        String query1 = "INSERT INTO contratoCliente (codigo) VALUES (?)";
        String query2 = "INSERT INTO vinculacliente (codigoInmueble, codigoContratoCliente, cedulaCliente) VALUES (?, ?, ?)";

        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, contrato.getCodigo());
            ps.setDouble(2, contrato.getValor());
            ps.setString(3, contrato.getTipoContrato());
            ps.setString(4, contrato.getDescripcion());
            ps.setDate(5, new java.sql.Date(contrato.getFechaCreacion().getTime()));
            ps.setDate(6, new java.sql.Date(contrato.getFechaExpiracion().getTime()));
            ps.setInt(7, contrato.getAgente().getCedula());

            PreparedStatement ps1 = this.conn.prepareStatement(query1);
            ps1.setInt(1, contrato.getCodigo());

            PreparedStatement ps2 = this.conn.prepareStatement(query2);
            ps2.setInt(1, contrato.getInmueble().getCodigo());
            ps2.setInt(2, contrato.getCodigo());;
            ps2.setInt(3, contrato.getCliente().getCedula());

            ps.executeUpdate();
            ps1.executeUpdate();
            ps2.executeUpdate();
            System.out.println("Datos insertados");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public ContratoCliente getById(int codigo) {
        ContratoCliente contrato = null;
        String query = "SELECT vinculacliente.*, \n" +
                "       contrato.* \n" +
                "FROM contrato\n" +
                "JOIN contratoCliente \n" +
                "  ON contrato.codigo = contratoCliente.codigo\n" +
                "JOIN vinculacliente \n" +
                "  ON contrato.codigo = vinculacliente.codigoContratoCliente\n" +
                "WHERE contrato.codigo = ?;\n";

        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    contrato = new ContratoCliente();
                    contrato.setCodigo(rs.getInt("codigo"));
                    contrato.setFechaCreacion(rs.getDate("fechacreacion"));
                    contrato.setFechaExpiracion(rs.getDate("fechaexpiracion"));
                    contrato.setTipoContrato(rs.getString("tipocontrato"));
                    contrato.setValor(rs.getFloat("valor"));
                    contrato.setDescripcion(rs.getString("descripcion"));

                    Agente agente = new Agente();
                    agente.setCedula(rs.getInt("cedula"));
                    contrato.setAgente(agente);

                    Cliente cliente = new Cliente();
                    cliente.setCedula(rs.getInt("cedulaCliente"));
                    contrato.setCliente(cliente);

                    Inmueble inmueble = new Inmueble();
                    inmueble.setCodigo(rs.getInt("codigoInmueble"));
                    contrato.setInmueble(inmueble);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrato;
    }

    public void update(ContratoCliente contrato) {
        String query = "UPDATE contrato SET fechacreacion = ?, fechaexpiracion = ?, tipocontrato = ?, valor = ?, descripcion = ?, cedula = ? WHERE codigo = ?";

        try (PreparedStatement ps = this.conn.prepareStatement(query)) {

            ps.setDate(1, new java.sql.Date(contrato.getFechaCreacion().getTime()));
            ps.setDate(2, new java.sql.Date(contrato.getFechaExpiracion().getTime()));
            ps.setString(3, contrato.getTipoContrato());
            ps.setFloat(4, contrato.getValor());
            ps.setString(5, contrato.getDescripcion());
            ps.setInt(6, contrato.getAgente().getCedula());
            ps.setInt(7, contrato.getCodigo());

            int rowsAffected = ps.executeUpdate();
           //System.out.println("Filas actualizadas en contrato: " + rowsAffected + " y contrato propietario: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int codigoContrato) {
        String query1 = "DELETE FROM contrato WHERE codigo = ?";
        try (PreparedStatement ps1 = this.conn.prepareStatement(query1)) {

            ps1.setInt(1, codigoContrato);
            ps1.executeUpdate();

            System.out.println("Contrato eliminado exitosamente");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
