package com.sistema.api.repository;

import com.sistema.api.model.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends CrudRepository<Produto, UUID> {

    @Query("select count(p) from Produto p")
    public int total();

    List<Produto> findByAccountAccountId(String accountId);




    Produto findByCodigo(UUID codigo);

    Produto findByCodigoAndAccountAccountId(UUID codigo, String accountId);
}
