package br.com.dio.designpatterns.repository;

import br.com.dio.designpatterns.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> { }

