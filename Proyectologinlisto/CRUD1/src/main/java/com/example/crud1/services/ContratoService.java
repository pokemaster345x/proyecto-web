package com.example.crud1.services;

import com.example.crud1.models.Contrato;
import com.example.crud1.models.ContratoCliente;
import com.example.crud1.repositories.ContratoRepository;
import com.example.crud1.utils.ConnectionDatabase;

import java.sql.Connection;
import java.util.List;

public class ContratoService {

    private Connection conn;
    private ContratoRepository contratoRepository;

    public ContratoService() {
        try {
            this.conn = ConnectionDatabase.getConnection();
            this.contratoRepository = new ContratoRepository(this.conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Contrato> getContratos() {
        return this.contratoRepository.list();
    }

    public List<ContratoCliente> getContratoClientes() {
        return this.contratoRepository.listContratoCliente();
    }

    public boolean createContratoCliente(ContratoCliente contratoCliente) {
        return this.contratoRepository.createContratoCl(contratoCliente);
    }

    public ContratoCliente getByIdContratoCliente(int codigo) {
        return this.contratoRepository.getByIdCC(codigo);
    }

    public boolean updateContrato(Contrato contrato) {
        return this.contratoRepository.ModificarContrato(contrato);
    }

    public boolean deleteContrato(int codigo) {
        return this.contratoRepository.delete(codigo);
    }

    public boolean existeContrato(int codigo) {
        return this.contratoRepository.existe(codigo);
    }
}