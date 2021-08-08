package com.sistema.api.repository;

import com.sistema.api.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente,Integer> {
}
