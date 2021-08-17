package com.sistema.api.controller;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Mensagem;
import com.sistema.api.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {


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
            return clienteRepository.save(cliente);
        }

        @GetMapping("/{id}")
        public Cliente um(@PathVariable Integer id) {
            return clienteRepository.findById(id).get();
        }

        @DeleteMapping("/{id}")
        public Mensagem delete(@PathVariable Integer id) {
            if (!clienteRepository.existsById(id)) {
                return new Mensagem("Cliente não existe");
            }

            clienteRepository.deleteById(id);
            return new Mensagem("Deletado com sucesso");
        }

        @PutMapping("/{id}")
        public Cliente alterar(@RequestBody Cliente cliente, @PathVariable int id) {
             cliente.setId(id);
        return clienteRepository.save(cliente);
    }

}
