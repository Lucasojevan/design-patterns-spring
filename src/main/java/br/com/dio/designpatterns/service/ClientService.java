package br.com.dio.designpatterns.service;

import br.com.dio.designpatterns.model.Client;
import java.util.Optional;

public interface ClientService {
    Iterable<Client> findAll();
    Client findById(Long id);
    Client create(Client client);
    Client update(Long id, Client client);
    void delete(Long id);
}

