package com.example.crud1.repositories;

import com.example.crud1.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InmuebleRepository {

    private Connection conn;
    public InmuebleRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Inmueble> list() {
        List<Inmueble> inmuebles = new ArrayList<>();
        String query = "SELECT * FROM inmueble i INNER JOIN ciudad c ON i.id = ciudad.id";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                Inmueble inmueble = new Inmueble();
                inmueble.setCodigo(rs.getInt("codigo"));
                inmueble.setTipoInmueble(rs.getString("tipoInmueble"));
                inmueble.setDescripcionInmueble(rs.getString("DescripcionInmueble"));
                inmueble.setModalidadComercio(rs.getString("ModalidadComercio"));

                Ciudad ciudad = new Ciudad();
                ciudad.setCodigoCiudad(rs.getInt("codigoCiudad"));
                ciudad.setNombreCiudad(rs.getString("nombreCiudad"));

                inmueble.setCiudad(ciudad);
                inmueble.setDireccion(rs.getString("direccion"));
                inmueble.setTamanoMetro(rs.getFloat("tamanoMetro"));
                inmueble.setCantBanos(rs.getInt("CantBanos"));
                inmueble.setFotoInmueble(rs.getString("FotoInmueble"));
                inmueble.setEstado(rs.getBoolean("estado"));
                inmueble.setPrecioFinal(rs.getFloat("precioFinal"));
                inmuebles.add(inmueble);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inmuebles;
    }

    public List<InmueblePropietario> listIPropietario() {
        List<InmueblePropietario> inmueblePropietarios = new ArrayList<>();
        String query = "SELECT * FROM inmueble i INNER JOIN inmueblePropietario ip ON i.codigo = ip.codigo " +
                " JOIN ciudad c ON i.codigoCiudad = c.codigoCiudad";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                InmueblePropietario inmuebleP = new InmueblePropietario();
                inmuebleP.setCodigo(rs.getInt("codigo"));
                inmuebleP.setTipoInmueble(rs.getString("tipoInmueble"));
                inmuebleP.setDescripcionInmueble(rs.getString("DescripcionInmueble"));
                inmuebleP.setModalidadComercio(rs.getString("ModalidadComercio"));

                Ciudad ciudad = new Ciudad();
                ciudad.setCodigoCiudad(rs.getInt("codigoCiudad"));
                ciudad.setNombreCiudad(rs.getString("nombreCiudad"));

                inmuebleP.setCiudad(ciudad);
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

    public List<InmuebleInmobiliaria> listInmobiliaria() {
        List<InmuebleInmobiliaria> InmuebleI = new ArrayList<>();
        String query = "SELECT * FROM inmueble i INNER JOIN inmuebleInmobiliaria ii ON i.codigo = ii.codigo " +
                " JOIN ciudad c ON i.codigoCiudad = c.codigoCiudad";
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                InmuebleInmobiliaria inmuebleI = new InmuebleInmobiliaria();
                inmuebleI.setCodigo(rs.getInt("codigo"));
                inmuebleI.setTipoInmueble(rs.getString("tipoInmueble"));
                inmuebleI.setDescripcionInmueble(rs.getString("DescripcionInmueble"));
                inmuebleI.setModalidadComercio(rs.getString("ModalidadComercio"));
                inmuebleI.setFechaAdquisicion(rs.getDate("fechaAdquisicion"));

                Ciudad ciudad = new Ciudad();
                ciudad.setCodigoCiudad(rs.getInt("codigoCiudad"));
                ciudad.setNombreCiudad(rs.getString("nombreCiudad"));

                inmuebleI.setCiudad(ciudad);
                inmuebleI.setDireccion(rs.getString("direccion"));
                inmuebleI.setTamanoMetro(rs.getFloat("tamanoMetro"));
                inmuebleI.setCantBanos(rs.getInt("CantBanos"));
                inmuebleI.setFotoInmueble(rs.getString("FotoInmueble"));
                inmuebleI.setEstado(rs.getBoolean("estado"));
                inmuebleI.setPrecioFinal(rs.getFloat("precioFinal"));
                InmuebleI.add(inmuebleI);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return InmuebleI;
    }
    public Boolean existe(int codigo){
        String query ="SELECT * FROM inmueble WHERE codigo = ?";
        try{
            PreparedStatement stmt =this.conn.prepareStatement((query));
            stmt.setInt(1,codigo);
            ResultSet rs=stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int create(Inmueble inmueble) {
        int check = -1;
        String query = "INSERT INTO inmueble (codigo, tipoInmueble, DescripcionInmueble, ModalidadComercio, codigoCiudad, direccion, tamanoMetro, CantBanos, FotoInmueble, estado, precioFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
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
            ps.setBoolean(10, inmueble.isEstado());
            ps.setFloat(11, inmueble.getPrecioFinal());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                check = rs.getInt(1);
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check; //retorna el código
    }

    public boolean createInmueblePro(InmueblePropietario inmueblePropietario) {
        int check = create(inmueblePropietario);
        String query = "INSERT INTO inmueblePropietario (codigo) VALUES (?)";
        try {
            if (check != -1) {
                PreparedStatement ps = this.conn.prepareStatement(query);
                ps.setInt(1, check); //guarda el código en inmueble propietario
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createInmuebleI(InmuebleInmobiliaria inmuebleInmobiliaria) {
        int check = create(inmuebleInmobiliaria);
        String query = "INSERT INTO inmuebleInmobiliaria (codigo, fechaAdquisicion) VALUES (?,?)";
        try {
            if (check != -1) {
                PreparedStatement ps = this.conn.prepareStatement(query);
                ps.setInt(1, check); //guarda el código en inmueble inmo
                ps.setDate(2, new java.sql.Date(inmuebleInmobiliaria.getFechaAdquisicion().getTime()));
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean ModificarInmueble(Inmueble inmueble) {
        String query = "UPDATE inmueble SET tipoInmueble = ?, DescripcionInmueble = ?, ModalidadComercio = ?, codigoCiudad = ?, direccion = ?, tamanoMetro = ?, CantBanos = ?, FotoInmueble = ?, estado = ?, precioFinal = ? WHERE codigo = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setString(1, inmueble.getTipoInmueble());
            ps.setString(2, inmueble.getDescripcionInmueble());
            ps.setString(3, inmueble.getModalidadComercio());
            ps.setInt(4, inmueble.getCiudad().getCodigoCiudad());
            ps.setString(5, inmueble.getDireccion());
            ps.setFloat(6, inmueble.getTamanoMetro());
            ps.setInt(7, inmueble.getCantBanos());
            ps.setString(8, inmueble.getFotoInmueble());
            ps.setBoolean(9, inmueble.isEstado());
            ps.setFloat(10, inmueble.getPrecioFinal());
            ps.setInt(11, inmueble.getCodigo());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ModificarInmuebleI(InmuebleInmobiliaria inmuebleI) {
        boolean inmueble = ModificarInmueble(inmuebleI);
        String query = "UPDATE inmuebleInmobiliaria SET fechaAdquisicion = ? WHERE codigo = ?";
        try {
            if (inmueble) {
                PreparedStatement ps = this.conn.prepareStatement(query);
                ps.setDate(1, new java.sql.Date(inmuebleI.getFechaAdquisicion().getTime())); //guarda el código en inmueble inmo
                ps.setInt(2, inmuebleI.getCodigo());

                int n = ps.executeUpdate();
                ps.close();
                return n > 0;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public InmuebleInmobiliaria ConsultarInmuebleIn_ID(int codigo) {
        String query = "SELECT *" +
                "FROM inmuebleInmobiliaria ii " +
                "INNER JOIN inmueble i ON ii.codigo = i.codigo " +
                "INNER JOIN ciudad c ON i.codigoCiudad = c.codigoCiudad " +
                "WHERE ii.codigo = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                InmuebleInmobiliaria inmuebleInmobiliaria = new InmuebleInmobiliaria();
                inmuebleInmobiliaria.setCodigo(rs.getInt("codigo"));
                inmuebleInmobiliaria.setTipoInmueble(rs.getString("tipoInmueble"));
                inmuebleInmobiliaria.setDescripcionInmueble(rs.getString("DescripcionInmueble"));
                inmuebleInmobiliaria.setModalidadComercio(rs.getString("ModalidadComercio"));

                Ciudad ciudad = new Ciudad();
                ciudad.setCodigoCiudad(rs.getInt("codigoCiudad"));
                ciudad.setNombreCiudad(rs.getString("nombreCiudad"));
                inmuebleInmobiliaria.setCiudad(ciudad);

                inmuebleInmobiliaria.setDireccion(rs.getString("direccion"));
                inmuebleInmobiliaria.setTamanoMetro(rs.getFloat("tamanoMetro"));
                inmuebleInmobiliaria.setCantBanos(rs.getInt("CantBanos"));
                inmuebleInmobiliaria.setFotoInmueble(rs.getString("FotoInmueble"));
                inmuebleInmobiliaria.setEstado(rs.getBoolean("estado"));
                inmuebleInmobiliaria.setPrecioFinal(rs.getFloat("precioFinal"));
                inmuebleInmobiliaria.setFechaAdquisicion(rs.getDate("fechaAdquisicion"));
                return inmuebleInmobiliaria;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InmueblePropietario ConsultarInmueblePro_ID(int codigo) {
        String query = "SELECT *" +
                "FROM inmueblepropietario ip " +
                "INNER JOIN inmueble i ON ip.codigo = i.codigo " +
                "INNER JOIN ciudad c ON i.codigoCiudad = c.codigoCiudad " +
                "WHERE ip.codigo = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                InmueblePropietario inmueblePropietario = new InmueblePropietario();
                inmueblePropietario.setCodigo(rs.getInt("codigo"));
                inmueblePropietario.setTipoInmueble(rs.getString("tipoInmueble"));
                inmueblePropietario.setDescripcionInmueble(rs.getString("DescripcionInmueble"));
                inmueblePropietario.setModalidadComercio(rs.getString("ModalidadComercio"));

                Ciudad ciudad = new Ciudad();
                ciudad.setCodigoCiudad(rs.getInt("codigoCiudad"));
                ciudad.setNombreCiudad(rs.getString("nombreCiudad"));
                inmueblePropietario.setCiudad(ciudad);

                inmueblePropietario.setDireccion(rs.getString("direccion"));
                inmueblePropietario.setTamanoMetro(rs.getFloat("tamanoMetro"));
                inmueblePropietario.setCantBanos(rs.getInt("CantBanos"));
                inmueblePropietario.setFotoInmueble(rs.getString("FotoInmueble"));
                inmueblePropietario.setEstado(rs.getBoolean("estado"));
                inmueblePropietario.setPrecioFinal(rs.getFloat("precioFinal"));
                return inmueblePropietario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Inmueble ConsultarInmueble_ID(int codigo) {
        String query = "SELECT * FROM inmueble WHERE codigo = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Inmueble inmueble = new Inmueble();
                inmueble.setCodigo(rs.getInt("codigo"));
                inmueble.setTipoInmueble(rs.getString("tipoInmueble"));
                inmueble.setDescripcionInmueble(rs.getString("DescripcionInmueble"));
                inmueble.setModalidadComercio(rs.getString("ModalidadComercio"));
                inmueble.getCiudad().setCodigoCiudad(rs.getInt("codigoCiudad"));
                inmueble.setDireccion(rs.getString("direccion"));
                inmueble.setTamanoMetro(rs.getFloat("tamanoMetro"));
                inmueble.setCantBanos(rs.getInt("CantBanos"));
                inmueble.setFotoInmueble(rs.getString("FotoInmueble"));
                inmueble.setEstado(rs.getBoolean("estado"));
                inmueble.setPrecioFinal(rs.getFloat("precioFinal"));
                return inmueble;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int codigo) {
        String query = "DELETE FROM inmueble WHERE codigo = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(query)) {
            ps.setInt(1, codigo);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
