package com.example.crud1.services;

import com.example.crud1.models.ContratoCliente;
import com.example.crud1.models.ContratoPropietario;
import com.example.crud1.repositories.ContratoClienteRepository;
import com.example.crud1.repositories.ContratoPropietarioRepository;
import com.example.crud1.utils.ConnectionDatabase;

import java.sql.Connection;
import java.util.List;

public class ContratoClienteService {

    private ContratoClienteRepository repo;
    private Connection conn;

    public ContratoClienteService() {
        this.conn = ConnectionDatabase.getConnection();
        this.repo = new ContratoClienteRepository(this.conn);
    }

    public List<ContratoCliente> getContratos() {
        return this.repo.list();
    }

    public Boolean createContratoPropietario(ContratoCliente contrato) {
        return this.repo.create(contrato);
    }

    public ContratoCliente getById(int codigo) {
        return repo.getById(codigo);
    }

    public void update(ContratoCliente contrato) {
        repo.update(contrato);
    }

    public void delete(int codigo) {
        repo.delete(codigo);
    }
}
