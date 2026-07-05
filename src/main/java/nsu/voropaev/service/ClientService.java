package nsu.voropaev.service;

import nsu.voropaev.model.Client;
import nsu.voropaev.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepo;

    public ClientService(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Page<Client> findAll(Pageable pageable) {
        return clientRepo.findAll(pageable);
    }

    public Optional<Client> findById(Long id) {
        return clientRepo.findById(id);
    }

    public Client save(Client client) {
        return clientRepo.save(client);
    }

    public void deleteById(Long id) {
        clientRepo.deleteById(id);
    }
}