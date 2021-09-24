package com.sistema.api.repository;
import com.sistema.api.model.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Integer>{

    @Query("select count(p) from Produto p")
    public int total();
}
