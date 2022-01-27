package com.sistema.api.service;

import com.sistema.api.home.Main;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Mensagem;
import com.sistema.api.model.Pedido;
import com.sistema.api.model.Produto;
import com.sistema.api.repository.AccountRepository;
import com.sistema.api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PedidoService extends Main {

  @Autowired
  PedidoRepository pedidoRepository;

  @Autowired
  AccountRepository accountRepository;


  public int pedidosDoProdutoEncontrados(Produto produto) {
    return pedidoRepository.pedidosDoProdutoEncontrados(produto);
  };

  public int pedidosDoClienteEncontrados(Cliente cliente) {
    return pedidoRepository.pedidosDoClienteEncontrados(cliente);
  }

  public int total() {
    return pedidoRepository.total();
  }

  public List<Pedido> findByAccountId(String accountId) {
    return  pedidoRepository.findByAccountAccountId(accountId);
  }

  public Pedido findByCodigoAndAccountAccountId(UUID codigo, String accountId) {
    return pedidoRepository.findByCodigoAndAccountAccountId(codigo, accountId);
  }

  public Pedido save(Pedido pedido) {
    return pedidoRepository.save(pedido);
  }

  public boolean existsByCodigo(UUID codigo) {
    return pedidoRepository.existsByCodigo(codigo);
  }
  @Transactional
  public Mensagem deleteByCodigo(UUID codigo) {
    pedidoRepository.deleteByCodigo(codigo);
    return new Mensagem(DELETADO_COM_SUCESSO);
  }

  public Pedido findById(UUID codigo) {
    return pedidoRepository.findByCodigo(codigo);
  }
}
