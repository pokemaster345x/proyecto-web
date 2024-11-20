package com.example.crud1.repositories;

import com.example.crud1.models.Agente;
import com.example.crud1.models.Inmueble;
import com.example.crud1.models.InmuebleInmobiliaria;
import com.example.crud1.models.InmueblePropietario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class I_PropietarioRepository {

    private Connection conn;

    public I_PropietarioRepository(Connection conn) {
        this.conn = conn;
    }

    public List<InmueblePropietario> list() {
        List<InmueblePropietario> inmueblePropietarios = new ArrayList<>();
        String query = "SELECT * FROM inmueble i INNER JOIN inmueblepropietario ip ON i.codigo = ip.codigo";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                InmueblePropietario inmuebleP = new InmueblePropietario();
                inmuebleP.setCodigo(rs.getInt("codigo"));
                inmuebleP.setTipoInmueble(rs.getString("tipoInmueble"));
                inmuebleP.setDescripcionInmueble(rs.getString("DescripcionInmueble"));
                inmuebleP.setModalidadComercio(rs.getString("ModalidadComercio"));
                inmuebleP.getCiudad().setCodigoCiudad(rs.getInt("codigoCiudad"));
                inmuebleP.setDireccion(rs.getString("direccion"));
                inmuebleP.setTamanoMetro(rs.getFloat("tamanoMetro"));
                inmuebleP.setCantBanos(rs.getInt("CantBanos"));
                inmuebleP.setFotoInmueble(rs.getString("FotoInmueble"));
                inmuebleP.setEstado(rs.getBoolean("estado"));
                inmuebleP.setPrecioFinal(rs.getFloat("precioFinal"));
                inmueblePropietarios.add(inmuebleP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inmueblePropietarios;
    }

    public boolean create(InmueblePropietario inmueble) {
        String query = "INSERT INTO inmueble (codigo, tipoInmueble, DescripcionInmueble, ModalidadComercio, codigoCiudad, direccion, tamanoMetro, CantBanos, FotoInmueble, estado, precioFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        String query_p = "INSERT INTO inmueblePropietario (codigo) VALUES (?)";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, inmueble.getCodigo());
            ps.setString(2, inmueble.getTipoInmueble());
            ps.setString(3, inmueble.getDescripcionInmueble());
            ps.setString(4, inmueble.getModalidadComercio());
            ps.setInt(5, inmueble.getCiudad().getCodigoCiudad());
            ps.setString(6, inmueble.getDireccion());
            ps.setFloat(7, inmueble.getTamanoMetro());
            ps.setInt(8, inmueble.getCantBanos());
            ps.setFloat(9, inmueble.getPrecioFinal());
            ps.setBoolean(10,inmueble.isEstado());
            ps.setFloat(11,inmueble.getPrecioFinal());

            PreparedStatement ps_p = this.conn.prepareStatement(query_p);
            ps_p.setInt(1,inmueble.getCodigo());
            ps.executeUpdate();
            ps_p.executeUpdate();
            ps_p.close();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public InmueblePropietario ConsutarInmueble(int codigo) {
        String query = "SELECT * FROM InmueblePropietario WHERE codigo = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                InmueblePropietario inmueblePropietario = new InmueblePropietario();
                inmueblePropietario.setCodigo(rs.getInt("codigo"));
                return inmueblePropietario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
