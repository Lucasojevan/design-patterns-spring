package br.com.dio.designpatterns.controller;

import br.com.dio.designpatterns.model.Client;
import br.com.dio.designpatterns.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService service;
    public ClientController(ClientService service) { this.service = service; }
    @GetMapping public Iterable<Client> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public Client findById(@PathVariable Long id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Client create(@RequestBody Client client) { return service.create(client); }
    @PutMapping("/{id}") public Client update(@PathVariable Long id, @RequestBody Client client) { return service.update(id, client); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}

