package com.sistema.api.repository;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdutoRepository extends CrudRepository<Produto, Integer>{

    @Query("select count(p) from Produto p")
    public int total();

    List<Produto> findByAccountAccountId(String accountId);

    Produto findByCodigoAndAccountAccountId(Integer codigo, String accountId);
}
