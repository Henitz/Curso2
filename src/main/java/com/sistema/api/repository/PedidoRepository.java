package com.sistema.api.repository;

import com.sistema.api.model.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido,Integer> {

    @Query("select count(p) from Pedido p")
    public int total();

    @Query("select count(ped) from Pedido ped where ped.cliente =:id")
    public int pedidosDoClienteEncontrados(int id);
}
