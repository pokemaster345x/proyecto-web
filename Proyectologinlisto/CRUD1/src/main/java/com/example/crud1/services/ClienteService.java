package com.example.crud1.services;
import com.example.crud1.models.Cliente;
import com.example.crud1.repositories.AgenteRepository;
import com.example.crud1.repositories.ClienteRepository;
import com.example.crud1.utils.ConnectionDatabase;
import java.sql.Connection;
import java.util.List;

public class ClienteService {
    private Connection conn;
    private ClienteRepository clienteRepository;

    public ClienteService() {
        try{
            this.conn = ConnectionDatabase.getConnection();
            this.clienteRepository=new ClienteRepository(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<Cliente> getClientes(){
        return this.clienteRepository.list();
    }
    public Boolean existeCliente(Cliente cliente){
        return clienteRepository.existe(cliente.getCedula());
    }
    public boolean createCliente(Cliente cliente){
        return this.clienteRepository.create(cliente);
    }
    public Cliente getbyID(int cedula){
        return this.clienteRepository.getById(cedula);
    }
    public boolean update(Cliente cliente){
        return this.clienteRepository.update(cliente);
    }
    public boolean delete(int cedula){
        return this.clienteRepository.delete(cedula);
    }
}
