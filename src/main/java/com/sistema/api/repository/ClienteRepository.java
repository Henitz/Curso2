package com.sistema.api.repository;

import com.sistema.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    @Query("select count(c) from Cliente c")
    public int total();



//    List<Cliente> findAll(Sort id, Account account);

    List<Cliente> findByAccountAccountId(String accountId);

    Cliente findByIdAndAccountAccountId(UUID id, String accountId);
//
//
//
    Cliente getAccountByAccountId(String accountId);




    Cliente findById(UUID id);

    void deleteById(UUID id);


}
