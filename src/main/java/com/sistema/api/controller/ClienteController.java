package com.sistema.api.controller;

import com.sistema.api.model.Cliente;
import com.sistema.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        public Cliente um(@PathVariable Integer id) { return clienteRepository.findById(id).get(); }


}
