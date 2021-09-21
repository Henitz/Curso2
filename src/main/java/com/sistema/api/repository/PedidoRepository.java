package com.sistema.api.repository;

import com.sistema.api.model.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido,Integer> {
}
