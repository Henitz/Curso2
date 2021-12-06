package com.sistema.api.repository;

import com.sistema.api.model.Cliente;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente,Integer> {

    @Query("select count(c) from Cliente c")
    public int total();

    List<Cliente> findAll(Sort id);

    List<Cliente> findByAccountAccountId(String accountId);

    Cliente findByIdAndAccountAccountId(Integer id, String accountId);

}
