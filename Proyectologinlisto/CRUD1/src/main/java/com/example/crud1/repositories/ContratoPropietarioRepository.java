package com.example.crud1.repositories;

import com.example.crud1.models.Agente;
import com.example.crud1.models.ContratoPropietario;
import com.example.crud1.models.Inmueble;
import com.example.crud1.models.Propietario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratoPropietarioRepository {


    private Connection conn;

    public ContratoPropietarioRepository(Connection connection) {
        this.conn = connection;
    }


        public List<ContratoPropietario> list() {
        List<ContratoPropietario> contratos = new ArrayList<>();
        String query = "select vinculapropietario.cedulaprop, contrato.*, contratopropietario.comision from contrato \n" +
                "join contratopropietario \n" +
                "on contrato.codigo = contratopropietario.codigo\n" +
                "join vinculapropietario \n" +
                "on vinculapropietario.codigocontratopropietario = contratopropietario.codigo";

        try (Statement st = this.conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                //cambiar lo que esta entre comillas
                ContratoPropietario contratoPropietario = new ContratoPropietario();
                contratoPropietario.setCodigo(rs.getInt("codigo"));
                contratoPropietario.setFechaCreacion(rs.getDate("fechacreacion"));
                contratoPropietario.setTipoContrato(rs.getString("tipocontrato"));
                contratoPropietario.setFechaExpiracion(rs.getDate("fechaexpiracion"));
                contratoPropietario.setValor(rs.getFloat("valor"));
                contratoPropietario.setDescripcion(rs.getString("descripcion"));
                contratoPropietario.setComision(rs.getInt("comision"));

                Agente agente = new Agente();
                agente.setCedula(rs.getInt("cedula"));
                contratoPropietario.setAgente(agente);

                Propietario propietario = new Propietario();
                propietario.setCedula(rs.getInt("cedulaprop"));
                contratoPropietario.setPropietario(propietario);

                contratos.add(contratoPropietario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return contratos;
    }

    public Boolean create(ContratoPropietario contrato) {

        String query = "INSERT INTO contrato (codigo, fechacreacion, fechaexpiracion, tipocontrato, valor, descripcion, cedula) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String query1 = "INSERT INTO contratopropietario (codigo, comision) VALUES (?, ?)";
        String query2 = "INSERT INTO vinculapropietario (codigocontratopropietario, codigoinmueble, cedulaprop) VALUES (?, ?, ?)";

        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, contrato.getCodigo());
            ps.setDate(2, new java.sql.Date(contrato.getFechaCreacion().getTime()));
            ps.setDate(3, new java.sql.Date(contrato.getFechaExpiracion().getTime()));
            ps.setString(4, contrato.getTipoContrato());
            ps.setDouble(5, contrato.getValor());
            ps.setString(6, contrato.getDescripcion());
            ps.setInt(7, contrato.getAgente().getCedula());

            PreparedStatement ps1 = this.conn.prepareStatement(query1);
            ps1.setInt(1, contrato.getCodigo());
            ps1.setFloat(2, contrato.getComision());

            PreparedStatement ps2 = this.conn.prepareStatement(query2);
            ps2.setInt(1, contrato.getCodigo());
            ps2.setInt(2, contrato.getInmueble().getCodigo());
            ps2.setInt(3, contrato.getPropietario().getCedula());

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

    public ContratoPropietario getById(int codigo) {
        ContratoPropietario contrato = null;
        String query = "SELECT vinculapropietario.*, \n" +
                "       contrato.*, \n" +
                "       contratopropietario.comision\n" +
                "FROM contrato\n" +
                "JOIN contratopropietario \n" +
                "  ON contrato.codigo = contratopropietario.codigo\n" +
                "JOIN vinculapropietario \n" +
                "  ON contrato.codigo = vinculapropietario.codigocontratopropietario\n" +
                "WHERE contrato.codigo = ?;\n";

        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    contrato = new ContratoPropietario();
                    contrato.setCodigo(rs.getInt("codigo"));
                    contrato.setFechaCreacion(rs.getDate("fechacreacion"));
                    contrato.setFechaExpiracion(rs.getDate("fechaexpiracion"));
                    contrato.setTipoContrato(rs.getString("tipocontrato"));
                    contrato.setValor(rs.getFloat("valor"));
                    contrato.setDescripcion(rs.getString("descripcion"));
                    contrato.setComision(rs.getFloat("comision"));
                    Agente agente = new Agente();
                    agente.setCedula(rs.getInt("cedula"));
                    contrato.setAgente(agente);

                    Propietario propietario = new Propietario();
                    propietario.setCedula(rs.getInt("cedulaprop"));
                    contrato.setPropietario(propietario);

                    Inmueble inmueble = new Inmueble();
                    inmueble.setCodigo(rs.getInt("codigoinmueble"));
                    contrato.setInmueble(inmueble);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrato;
    }

    public void update(ContratoPropietario contrato) {
        String query = "UPDATE contrato SET fechacreacion = ?, fechaexpiracion = ?, tipocontrato = ?, valor = ?, descripcion = ?, cedula = ? WHERE codigo = ?";
        String query1 = "UPDATE contratopropietario SET comision = ? WHERE codigo = ?";

        try (PreparedStatement ps = this.conn.prepareStatement(query);
             PreparedStatement ps1 = this.conn.prepareStatement(query1)) {

            ps.setDate(1, new java.sql.Date(contrato.getFechaCreacion().getTime()));
            ps.setDate(2, new java.sql.Date(contrato.getFechaExpiracion().getTime()));
            ps.setString(3, contrato.getTipoContrato());
            ps.setFloat(4, contrato.getValor());
            ps.setString(5, contrato.getDescripcion());
            ps.setInt(6, contrato.getAgente().getCedula());
            ps.setInt(7, contrato.getCodigo());

            ps1.setFloat(1, contrato.getComision());
            ps1.setInt(2, contrato.getCodigo());


            int rowsUpdated = ps1.executeUpdate();
            int rowsAffected = ps.executeUpdate();
            System.out.println("Filas actualizadas en contrato: " + rowsAffected + " y contrato propietario: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int codigoContrato) {
        String query1 = "DELETE FROM contratopropietario WHERE codigo = ?";


        try (PreparedStatement ps1 = this.conn.prepareStatement(query1)) {

            ps1.setInt(1, codigoContrato);
            ps1.executeUpdate();

            System.out.println("Contrato eliminado exitosamente");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


