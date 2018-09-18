package by.bsu.mmf.webservice.touragency.repository;

import by.bsu.mmf.webservice.touragency.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> findAll();
    Optional<Client> findOne(Long id);
    Optional<Client> save(Client client);
    Optional<Client> update(Client client);
    Optional<Client> delete(Long id);
}
