package com.example.crud1.services;
import com.example.crud1.models.Ciudad;
import com.example.crud1.repositories.InmuebleRepository;
import com.example.crud1.utils.ConnectionDatabase;
import com.example.crud1.models.Inmueble;
import com.example.crud1.models.InmuebleInmobiliaria;
import com.example.crud1.models.InmueblePropietario;
import java.sql.Connection;
import java.util.List;

public class InmuebleService {
    private InmuebleRepository inmuebleRepository;
    private Connection conn;
    public InmuebleService(){
        conn=ConnectionDatabase.getConnection();
        inmuebleRepository=new InmuebleRepository(conn);
    }
    public boolean existeInmueble(Inmueble inmueble){
        return inmuebleRepository.existe(inmueble.getCodigo());
    }
    public List<Inmueble> getInmuebles(){
        return inmuebleRepository.list();
    }
    public List<InmuebleInmobiliaria> ListInmobiliarias(){
        return inmuebleRepository.listInmobiliaria();
    }
    public List<InmueblePropietario> ListPropietarios(){
        return inmuebleRepository.listIPropietario();
    }
    public Boolean createPropietario(InmueblePropietario propietario){
        return inmuebleRepository.createInmueblePro(propietario);
    }
    public Boolean createInmobiliaria(InmuebleInmobiliaria inmobiliaria){
        return inmuebleRepository.createInmuebleI(inmobiliaria);
    }
    public Inmueble getbyId(int codigo){
        return this.inmuebleRepository.ConsultarInmueble_ID(codigo);
    }
    public InmueblePropietario getbyIdP(int codigo){
        return this.inmuebleRepository.ConsultarInmueblePro_ID(codigo);
    }
    public InmuebleInmobiliaria getbyIdI(int codigo){
        return this.inmuebleRepository.ConsultarInmuebleIn_ID(codigo);
    }

}
