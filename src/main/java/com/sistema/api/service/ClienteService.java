package com.sistema.api.service;

import com.sistema.api.controller.ClienteController;
import com.sistema.api.home.Main;
import com.sistema.api.model.Account;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Mensagem;
import com.sistema.api.repository.AccountRepository;
import com.sistema.api.repository.ClienteRepository;
import com.sistema.api.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;


@Service
public class ClienteService extends Main {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    private static Logger logger = LoggerFactory.getLogger(ClienteController.class);

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente findByIdAndAccountAccountId(UUID id, String accountId){
        return clienteRepository.findByIdAndAccountAccountId(id, accountId);
    }
    public List<Cliente> findByAccountId(String accountId) {

        return  clienteRepository.findByAccountAccountId(accountId);
    }

    public Cliente findById(UUID id) {
        return clienteRepository.findById(id);
    }

    public Mensagem delete(UUID id, String accountId) {
        Cliente cliente = new Cliente();
        cliente.setId(id);

        Account account = accountRepository.getAccountByAccountId(accountId);
        cliente.setAccount(account);

        int quantidadeDePedidos = pedidoRepository.pedidosDoClienteEncontrados(cliente);

        logger.info("Id: " + id);
        logger.info("Quantidade de pedidos: " + quantidadeDePedidos);
        Boolean block_delecao = false;

        //TODO verificar se precisa de mais uma condição aqui
        // &&
        //clienteRepository.findById(id).getAccount()
        //       .notequals(accountService.getAccountByAccountId(accountId))
        if (quantidadeDePedidos > 0
        ) {
            block_delecao = true;
            return new Mensagem("Preciso bloquear a deleção", block_delecao);
        } else {
            // return new Mensagem("Da pra deletar por que não tem amarrações em uso");
            clienteRepository.deleteById(id);
            return new Mensagem(DELETADO_COM_SUCESSO, block_delecao);

        }


    }


    public Mensagem delete2(UUID id,  String accountId) {
        Cliente cliente = new Cliente();
        cliente.setId(id);

        Account account = accountRepository.getAccountByAccountId(accountId);
        cliente.setAccount(account);

        int quantidadeDePedidos = pedidoRepository.pedidosDoClienteEncontrados(cliente);

        logger.info("Id: " + id);
        logger.info("Quantidade de pedidos: " + quantidadeDePedidos);
        Boolean block_delecao = false;

        //TODO verificar se precisa de mais uma condição aqui
        // &&
        //clienteRepository.findById(id).getAccount()
        //       .notequals(accountService.getAccountByAccountId(accountId))
        if (quantidadeDePedidos > 0
        ) {
            block_delecao = true;
            return new Mensagem("Preciso bloquear a deleção", block_delecao);
        } else {
            // return new Mensagem("Da pra deletar por que não tem amarrações em uso");
            clienteRepository.deleteById(id);
            return new Mensagem(DELETADO_COM_SUCESSO, block_delecao);

        }
    }






    public Cliente mudarStatus( UUID id,  String accountId) {


        Cliente cliente = clienteRepository.findByIdAndAccountAccountId(id, accountId);


        if (cliente.getAtivo()) {
            cliente.setAtivo(false);
        } else {
            cliente.setAtivo(true);
        }
        return clienteRepository.save(cliente);

    }




    public int total() {
        return clienteRepository.total();
    }
}
