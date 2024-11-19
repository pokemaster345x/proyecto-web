package com.example.crud1.services;

import com.example.crud1.models.Agente;
import com.example.crud1.repositories.AgenteRepository;
import com.example.crud1.utils.ConnectionDatabase;
import java.sql.Connection;
import java.util.List;

public class AgenteService {

    private Connection conn;
    private AgenteRepository agenteRepository;

    public AgenteService() {
        try {
            this.conn = ConnectionDatabase.getConnection();
            this.agenteRepository = new AgenteRepository(this.conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Agente> getAgentes() {
        return this.agenteRepository.list();
    }
    public Boolean existeAgente(Agente agente) {
        return agenteRepository.existe(agente.getCedula());
    }
    public boolean createAgente(Agente agente) {
        return this.agenteRepository.create(agente);
    }

    public Agente getById(int cedula) {
        return this.agenteRepository.getById(cedula);
    }

    public boolean update(Agente agente) {
        return this.agenteRepository.update(agente);
    }

    public boolean delete(int cedula) {
        return this.agenteRepository.delete(cedula);
    }
}
