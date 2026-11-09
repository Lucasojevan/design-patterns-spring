package br.com.dio.designpatterns.repository;

import br.com.dio.designpatterns.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> { }

