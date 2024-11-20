package com.example.crud1.repositories;

import com.example.crud1.models.Agente;
import com.example.crud1.models.Contrato;
import com.example.crud1.models.ContratoCliente;
import com.example.crud1.models.ContratoPropietario;

import java.sql.*;
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
    public List<ContratoCliente>listContratoCliente() {
        List<ContratoCliente> contratoClientes = new ArrayList<>();
        String query ="SELECT * FROM contrato co INNER JOIN contratoCliente cc ON co.codigo = cc.codigo";
        try(Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(query)){
            while(rs.next()){
                ContratoCliente contratoCliente = new ContratoCliente();
                contratoCliente.setCodigo(rs.getInt("codigo"));
                contratoCliente.setTipoContrato(rs.getString("tipoContrato"));
                contratoCliente.setDescripcion(rs.getString("descripcion"));
                contratoCliente.setFechaCreacion(rs.getDate("fechaCreacion"));
                contratoCliente.setFechaExpiracion(rs.getDate("fechaExpiracion"));
                contratoCliente.setCedula(rs.getInt("cedula"));
                contratoCliente.setValor(rs.getFloat("valor"));
                contratoClientes.add(contratoCliente);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return contratoClientes;
    }
    public List<ContratoPropietario>listContratoPropietario() {
        List<ContratoPropietario> contratoPropietarios = new ArrayList<>();
        String query ="SELECT * FROM contrato co INNER JOIN contratoPropietario cc ON co.codigo = cc.codigo";
        try(Statement st = this.conn.createStatement();
            ResultSet rs = st.executeQuery(query)){
            while(rs.next()){
                ContratoPropietario contratoPropietario = new ContratoPropietario();
                contratoPropietario.setCodigo(rs.getInt("codigo"));
                contratoPropietario.setTipoContrato(rs.getString("tipoContrato"));
                contratoPropietario.setDescripcion(rs.getString("descripcion"));
                contratoPropietario.setFechaCreacion(rs.getDate("fechaCreacion"));
                contratoPropietario.setFechaExpiracion(rs.getDate("fechaExpiracion"));
                contratoPropietario.setCedula(rs.getInt("cedula"));
                contratoPropietario.setValor(rs.getFloat("valor"));
                contratoPropietario.setComision(rs.getFloat("comision"));
                contratoPropietarios.add(contratoPropietario);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return contratoPropietarios;
    }

    public int create(Contrato contrato){
        int check = -1;
        String query = "INSERT INTO contrato (codigo, valor, tipoContrato, descripcion, fechaCreacion, fechaExpiracion, cedula) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, contrato.getCodigo());
            ps.setFloat(2, contrato.getValor());
            ps.setString(3, contrato.getTipoContrato());
            ps.setString(4, contrato.getDescripcion());
            ps.setDate(5, new java.sql.Date(contrato.getFechaCreacion().getTime()));
            ps.setDate(6, new java.sql.Date(contrato.getFechaExpiracion().getTime()));
            ps.setInt(7, contrato.getCedula());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                check = rs.getInt(1); //código del contrato
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check; //retorna el código
    }

    public boolean createContratoCl(ContratoCliente contratoCliente){
        try {
            int codigo = create(contratoCliente);
            String query = "INSERT INTO contratoCliente (codigo) VALUES (?)";
            if (codigo != -1) {
                PreparedStatement ps = this.conn.prepareStatement(query);
                ps.setInt(1, codigo); //guarda el código en inmueble propietario
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createContratoP(ContratoPropietario contratoPropietario){
        try {
            int codigo = create(contratoPropietario);
            String query = "INSERT INTO contratoPropietario (codigo, comision) VALUES (?,?)";
            if (codigo != -1) {
                PreparedStatement ps = this.conn.prepareStatement(query);
                ps.setInt(1, codigo); //guarda el código en inmueble propietario
                ps.setFloat(2, contratoPropietario.getComision());
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ContratoCliente getByIdCC(int codigo){
        String query= "SELECT * FROM contrato co INNER JOIN contratoCliente cc ON co.codigo WHERE cc.codigo = ?";
        try(PreparedStatement ps=this.conn.prepareStatement(query)){
            ps.setInt(1,codigo);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                ContratoCliente contratoCliente = new ContratoCliente();
                contratoCliente.setCodigo(rs.getInt("codigo"));
                contratoCliente.setTipoContrato(rs.getString("tipoContrato"));
                contratoCliente.setDescripcion(rs.getString("descripcion"));
                contratoCliente.setFechaCreacion(rs.getDate("fechaCreacion"));
                contratoCliente.setFechaExpiracion(rs.getDate("fechaExpiracion"));
                contratoCliente.setCedula(rs.getInt("cedula"));
                contratoCliente.setValor(rs.getFloat("valor"));
                return contratoCliente;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ContratoPropietario getByIdCP(int codigo){
        String query= "SELECT * FROM contrato co INNER JOIN contratoPropietario cc ON co.codigo WHERE cc.codigo = ?";
        try(PreparedStatement ps=this.conn.prepareStatement(query)){
            ps.setInt(1,codigo);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                ContratoPropietario contrato = new ContratoPropietario();
                contrato.setCodigo(rs.getInt("codigo"));
                contrato.setTipoContrato(rs.getString("tipoContrato"));
                contrato.setDescripcion(rs.getString("descripcion"));
                contrato.setFechaCreacion(rs.getDate("fechaCreacion"));
                contrato.setFechaExpiracion(rs.getDate("fechaExpiracion"));
                contrato.setCedula(rs.getInt("cedula"));
                contrato.setValor(rs.getFloat("valor"));
                contrato.setComision(rs.getFloat("comision"));
                return contrato;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Contrato getById(int codigo){
        String query = "SELECT * FROM contrato WHERE codigo = ?";
        try(PreparedStatement ps=this.conn.prepareStatement(query)){
            ps.setInt(1,codigo);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Contrato contrato = new Contrato();
                contrato.setCodigo(rs.getInt("codigo"));
                contrato.setValor(rs.getFloat("valor"));
                contrato.setTipoContrato(rs.getString("tipoContrato"));
                contrato.setDescripcion(rs.getString("descripcion"));
                contrato.setFechaCreacion(rs.getDate("fechaCreacion"));
                contrato.setFechaExpiracion(rs.getDate("fechaExpiracion"));
                contrato.setCedula(rs.getInt("cedula"));
                return contrato;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean ModificarContrato (Contrato contrato){
        String query = "UPDATE contrato SET valor = ?, tipoContrato = ?, descripcion = ?, fechaExpiracion = ? WHERE codigo = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setFloat(1, contrato.getValor());
            ps.setString(2, contrato.getTipoContrato());
            ps.setString(3, contrato.getDescripcion());
            ps.setDate(4, new java.sql.Date(contrato.getFechaCreacion().getTime()));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean ModificarContratoProp(ContratoPropietario contratoPropietario){
        try {
            ModificarContrato(contratoPropietario);
            PreparedStatement ps = conn.prepareStatement("UPDATE contratoPropietario " +"SET comision = ? " +"WHERE codigo = ?" );
            ps.setFloat(1, contratoPropietario.getComision());
            ps.setInt(2, contratoPropietario.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(int codigo){
        String query ="DELETE FROM contrato WHERE codigo = ?";
        try(PreparedStatement ps=this.conn.prepareStatement(query)){
            ps.setInt(1,codigo);
            int rows=ps.executeUpdate();
            return rows > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean existe(int codigo){
        try{
            String query ="DELETE FROM contrato WHERE codigo = ?";
            PreparedStatement stmt=this.conn.prepareStatement(query);
            stmt.setInt(1,codigo);
            ResultSet rs=stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
