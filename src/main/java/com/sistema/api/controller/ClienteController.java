package com.sistema.api.controller;

import com.sistema.api.home.MainController;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Mensagem;
import com.sistema.api.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController extends MainController {

    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
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
        if (!clienteRepository.existsById(id)) {
            return new Mensagem(REGISTRO_NAO_ENCONTRADO);
        }

        clienteRepository.deleteById(id);
        return new Mensagem(DELETADO_COM_SUCESSO);
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
