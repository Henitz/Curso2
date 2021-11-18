package com.sistema.api.controller;


import com.sistema.api.home.MainController;
import com.sistema.api.model.Mensagem;
import com.sistema.api.model.Pedido;
import com.sistema.api.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController extends MainController {

    private PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping
    public List<Pedido> todos() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public Pedido um(@PathVariable Integer codigo) {
        return pedidoRepository.findById(codigo).get();
    }

    @PostMapping
    public Pedido salvar(@RequestBody Pedido pedido) {

        pedido.setAtivo(true);
        return pedidoRepository.save(pedido);
    }

    @DeleteMapping("/{codigo}")
    public Mensagem delete(@PathVariable Integer codigo) {
        if (!pedidoRepository.existsById(codigo)) {
            return new Mensagem(REGISTRO_NAO_ENCONTRADO);
        }
        pedidoRepository.deleteById(codigo);
        return new Mensagem(DELETADO_COM_SUCESSO);
    }

    @PatchMapping("{codigo}/ativo")
    public Pedido mudarStatus(@PathVariable Integer codigo) {
        Pedido pedido = pedidoRepository.findById(codigo).get();
        if (pedido.getAtivo()) {
            pedido.setAtivo(false);
        } else {
            pedido.setAtivo(true);
        }
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{codigo}")
    public Pedido alterar(@RequestBody Pedido pedido, @PathVariable int codigo) {
        pedido.setCodigo(codigo);
        return pedidoRepository.save(pedido);
    }

}
