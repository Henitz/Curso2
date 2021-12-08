package com.sistema.api.controller;


import com.sistema.api.home.MainController;
import com.sistema.api.model.Account;
import com.sistema.api.model.Mensagem;
import com.sistema.api.model.Pedido;
import com.sistema.api.repository.PedidoRepository;
import com.sistema.api.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController extends MainController {

    private PedidoRepository pedidoRepository;
    private AccountService accountService;

    public PedidoController(PedidoRepository pedidoRepository,
                            AccountService accountService
    ) {
        this.pedidoRepository = pedidoRepository;
        this.accountService = accountService;

    }

    @GetMapping("/{accountId}")
    public List<Pedido> todos(@PathVariable String accountId) {

        return (List<Pedido>) pedidoRepository.findByAccountAccountId(accountId);

    }

    @GetMapping("/{codigo}/{accountId}")
    public Pedido um(@PathVariable Integer codigo, @PathVariable String accountId) {

        return (Pedido) pedidoRepository.findByCodigoAndAccountAccountId(codigo, accountId);
    }

    @PostMapping("/{accountId}")
    public Pedido salvar(@PathVariable String accountId, @RequestBody Pedido pedido) {

        pedido.setAtivo(true);

        Account account = accountService.getAccountByAccountId(accountId);
        pedido.setAccount(account);

        return pedidoRepository.save(pedido);
    }

    @DeleteMapping("/{codigo}/{accountId}")
    public Mensagem delete(@PathVariable Integer codigo,@PathVariable String accountId) {


        Pedido pedido = new Pedido();
        pedido.setCodigo(codigo);

        Account account = accountService.getAccountByAccountId(accountId);
        pedido.setAccount(account);

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
