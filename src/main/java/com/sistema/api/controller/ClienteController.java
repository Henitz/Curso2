package com.sistema.api.controller;

import com.sistema.api.home.MainController;
import com.sistema.api.model.Account;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Mensagem;
import com.sistema.api.repository.AccountRepository;
import com.sistema.api.repository.ClienteRepository;
import com.sistema.api.repository.PedidoRepository;
import com.sistema.api.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController extends MainController {

    private static Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private ClienteRepository clienteRepository;
    private PedidoRepository pedidoRepository;
    private AccountService accountService;

    public ClienteController(ClienteRepository clienteRepository,
                             PedidoRepository pedidoRepository,
                             AccountService accountService
                             ) {

        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.accountService = accountService;

    }

    @GetMapping("/{accountId}")
    public List<Cliente> todos(@PathVariable String accountId) {
        return (List<Cliente>) clienteRepository.findByAccountAccountId(accountId);

                // Sort.Direction.ASC, "id"
    }

    @PostMapping("/{accountId}")
    public Cliente save(@PathVariable String accountId, @RequestBody Cliente cliente) {
        cliente.setAtivo(true);
        Account account = accountService.getAccountByAccountId(accountId);
        cliente.setAccount(account);
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}/{accountId}")
    public Cliente um(@PathVariable Integer id, @PathVariable String accountId) {

        return (Cliente) clienteRepository.findByIdAndAccountAccountId(id, accountId);
    }

    @DeleteMapping("/{id}/{accountId}")
    public Mensagem delete(@PathVariable Integer id, @PathVariable String accountId) {

        Cliente cliente= new Cliente();
        cliente.setId(id);

        Account account = accountService.getAccountByAccountId(accountId);
        cliente.setAccount(account);

        int quantidadeDePedidos = pedidoRepository.pedidosDoClienteEncontrados(cliente);

        logger.info("Id: " + id);
        logger.info("Quantidade de pedidos: " + quantidadeDePedidos);
        Boolean block_delecao = false;

        //TODO verificar se precisa de mais uma condição aqui
        // &&
        //clienteRepository.findById(id).getAccount()
         //       .notequals(accountService.getAccountByAccountId(accountId))
        if(quantidadeDePedidos > 0
        ){
            block_delecao = true;
            return new Mensagem("Preciso bloquear a deleção", block_delecao );
        } else {
           // return new Mensagem("Da pra deletar por que não tem amarrações em uso");
            clienteRepository.deleteById(id);
            return new Mensagem(DELETADO_COM_SUCESSO, block_delecao);

        }
    }

    @PatchMapping("{id}/ativo/{accountId}")
    public Cliente mudarStatus(@PathVariable Integer id, @PathVariable String accountId) {


        Cliente cliente = clienteRepository.findById(id).get();

        Account account = accountService.getAccountByAccountId(accountId);
        cliente.setAccount(account);


        if (cliente.getAtivo()) {
            cliente.setAtivo(false);
        } else {
            cliente.setAtivo(true);
        }
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}/{accountId}")
    public Cliente alterar(@RequestBody Cliente cliente, @PathVariable int id, @PathVariable String accountId) {
        Account account = accountService.getAccountByAccountId(accountId);
        System.out.println(account);
        cliente.setAccount(account);
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

}
