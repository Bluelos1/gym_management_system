package pl.edu.pjatk.gym_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.gym_management_system.model.Client;
import pl.edu.pjatk.gym_management_system.model.Ticket;
import pl.edu.pjatk.gym_management_system.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client){
        return clientRepository.save(client);
    }

    public Client findByNameAndLastName(String firstName, String lastName){
        Optional<Client> client = clientRepository.findClientByFirstNameAndLastName(firstName, lastName);
        if(client.isPresent()){
            return client.get();
        }
        throw new IllegalArgumentException();
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(Long id){
        Optional<Client> a = clientRepository.findById(id);
        if (a.isPresent()){
            return a.get();
        }
        throw new IllegalArgumentException();
    }

    public void deleteClientById(Long id){
        clientRepository.deleteById(id);
    }

}
