package com.sistema.api.controller;

import com.sistema.api.home.MainController;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Mensagem;
import com.sistema.api.repository.ClienteRepository;
import com.sistema.api.repository.PedidoRepository;
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

    public ClienteController(ClienteRepository clienteRepository,
                             PedidoRepository pedidoRepository) {

        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping
    public List<Cliente> todos() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {
        cliente.setAtivo(true);
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public Cliente um(@PathVariable Integer id) {
        return clienteRepository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public Mensagem delete(@PathVariable Integer id) {

        Cliente cliente= new Cliente();
        cliente.setId(id);

        int quantidadeDePedidos = pedidoRepository.pedidosDoClienteEncontrados(cliente);

        logger.info("Id: " + id);
        logger.info("Quantidade de pedidos: " + quantidadeDePedidos);
        Boolean block_delecao = false;
        if(quantidadeDePedidos > 0){
            block_delecao = true;
            return new Mensagem("Preciso bloquear a deleção", block_delecao );
        } else {
           // return new Mensagem("Da pra deletar por que não tem amarrações em uso");
            clienteRepository.deleteById(id);
            return new Mensagem(DELETADO_COM_SUCESSO, block_delecao);

        }



//        if (!clienteRepository.existsById(id)) {
//            return new Mensagem(REGISTRO_NAO_ENCONTRADO);
//        }
//
//        clienteRepository.deleteById(id);
//        return new Mensagem(DELETADO_COM_SUCESSO);
    }

    @PatchMapping("{id}/ativo")
    public Cliente mudarStatus(@PathVariable Integer id) {
        Cliente cliente = clienteRepository.findById(id).get();
        if (cliente.getAtivo()) {
            cliente.setAtivo(false);
        } else {
            cliente.setAtivo(true);
        }
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente alterar(@RequestBody Cliente cliente, @PathVariable int id) {
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

}
