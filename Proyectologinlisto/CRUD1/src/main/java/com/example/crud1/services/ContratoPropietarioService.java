package com.example.crud1.services;



import com.example.crud1.models.ContratoPropietario;
import com.example.crud1.repositories.ContratoPropietarioRepository;
import com.example.crud1.utils.ConnectionDatabase;

import java.sql.Connection;
import java.util.List;

public class ContratoPropietarioService {

    private ContratoPropietarioRepository repo;
    private Connection conn;

    public ContratoPropietarioService() {
        this.conn = ConnectionDatabase.getConnection();
        this.repo = new ContratoPropietarioRepository(this.conn);

    }

    public List<ContratoPropietario> getContratos() {
        return this.repo.list();
    }

    public Boolean createContratoPropietario(ContratoPropietario contrato) {
        return this.repo.create(contrato);
    }

    public ContratoPropietario getById(int codigo) {
        return repo.getById(codigo);
    }

    public void update(ContratoPropietario contrato) {
        repo.update(contrato);
    }

    public void delete(int codigo) {
        repo.delete(codigo);
    }

}
