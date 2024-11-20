package com.example.crud1.services;

import com.example.crud1.models.Contrato;
import com.example.crud1.repositories.ContratoRepository;
import com.example.crud1.utils.ConnectionDatabase;
import java.sql.Connection;
import java.util.List;

public class ContratoService {

    private Connection conn;
    private ContratoRepository contratoRepository;

    public ContratoService(){
        try{
            this.conn=ConnectionDatabase.getConnection();
            this.contratoRepository=new ContratoRepository(this.conn);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<Contrato> getContrato(){
        return this.contratoRepository.list();
    }
    public Boolean existeContrato(Contrato contrato){
        return contratoRepository.existe(contrato.getCodigo());
    }
    public boolean createContrato(Contrato contrato){
        return this.contratoRepository.create(contrato);
    }
    public Contrato getById(int Codigo){
        return this.contratoRepository.getById(Codigo);
    }
    public boolean update(Contrato contrato){
        return this.contratoRepository.update(contrato);
    }
    public boolean delete(int codigo){
        return this.contratoRepository.delete(codigo);
    }
}
