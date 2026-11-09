package br.com.dio.designpatterns.service;

import br.com.dio.designpatterns.integration.ViaCepClient;
import br.com.dio.designpatterns.model.Address;
import br.com.dio.designpatterns.model.Client;
import br.com.dio.designpatterns.repository.AddressRepository;
import br.com.dio.designpatterns.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clients;
    private final AddressRepository addresses;
    private final ViaCepClient viaCep;

    public ClientServiceImpl(ClientRepository clients, AddressRepository addresses, ViaCepClient viaCep) {
        this.clients = clients; this.addresses = addresses; this.viaCep = viaCep;
    }
    public Iterable<Client> findAll() { return clients.findAll(); }
    public Client findById(Long id) { return clients.findById(id).orElseThrow(() -> new ClientNotFoundException(id)); }
    public Client create(Client client) { return clients.save(attachAddress(client)); }
    public Client update(Long id, Client client) { client.setId(findById(id).getId()); return clients.save(attachAddress(client)); }
    public void delete(Long id) { clients.delete(findById(id)); }
    private Client attachAddress(Client client) {
        if (client.getAddress() == null || client.getAddress().getZipCode() == null) throw new IllegalArgumentException("CEP é obrigatório.");
        String zip = client.getAddress().getZipCode().replaceAll("\\D", "");
        Address address = addresses.findById(zip).orElseGet(() -> addresses.save(viaCep.findAddress(zip)));
        client.setAddress(address);
        return client;
    }
    public static class ClientNotFoundException extends RuntimeException { public ClientNotFoundException(Long id) { super("Cliente " + id + " não encontrado."); } }
}

