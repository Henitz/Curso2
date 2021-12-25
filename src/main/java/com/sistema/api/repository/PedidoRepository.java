package com.sistema.api.repository;

import com.sistema.api.model.Cliente;
import com.sistema.api.model.Pedido;
import com.sistema.api.model.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends CrudRepository<Pedido,Integer> {

    @Query("select count(p) from Pedido p")
    public int total();

    @Query("select count(ped) from Pedido ped where ped.cliente =:cliente")
    public int pedidosDoClienteEncontrados(@Param("cliente") Cliente cliente);

    @Query("select count(ped) from Pedido ped where ped.produto =:produto")
    public int pedidosDoProdutoEncontrados(@Param("produto") Produto produto);

    List<Pedido> findByAccountAccountId(String accountId);

    Pedido findByCodigoAndAccountAccountId(Integer codigo, String accountId);
}
