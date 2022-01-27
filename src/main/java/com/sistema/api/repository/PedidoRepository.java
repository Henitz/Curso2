package com.sistema.api.repository;

import com.sistema.api.model.Cliente;
import com.sistema.api.model.Pedido;
import com.sistema.api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    @Query("select count(p) from Pedido p")
    public int total();

    @Query("select count(ped) from Pedido ped where ped.cliente =:cliente")
    public int pedidosDoClienteEncontrados(@Param("cliente") Cliente cliente);

    @Query("select count(ped) from Pedido ped where ped.produto =:produto")
    public int pedidosDoProdutoEncontrados(@Param("produto") Produto produto);

    List<Pedido> findByAccountAccountId(String accountId);

    Pedido findByCodigoAndAccountAccountId(UUID codigo, String accountId);

    Pedido findByCodigo(UUID codigo);

    void deleteByCodigo(UUID codigo);

    boolean existsByCodigo(UUID codigo);
}
