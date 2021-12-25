package com.sistema.api.controller;

import com.sistema.api.home.MainController;
import com.sistema.api.model.Account;
import com.sistema.api.model.Mensagem;
import com.sistema.api.model.Produto;
import com.sistema.api.repository.PedidoRepository;
import com.sistema.api.repository.ProdutoRepository;
import com.sistema.api.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController extends MainController {

    private ProdutoRepository produtoRepository;
    private AccountService accountService;
    private PedidoRepository pedidoRepository;

    public ProdutoController(ProdutoRepository produtoRepository,
                             AccountService accountService,
                             PedidoRepository pedidoRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.accountService = accountService;
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/{accountId}")
    public List<Produto> todos(@PathVariable String accountId) {

        return (List<Produto>) produtoRepository.findByAccountAccountId(accountId);
    }

    @PostMapping("/{accountId}")
    public Produto salvar(@PathVariable String accountId, @RequestBody Produto produto) {
        produto.setAtivo(true);

        Account account = accountService.getAccountByAccountId(accountId);
        produto.setAccount(account);

        return produtoRepository.save(produto);
    }


    @GetMapping("/{codigo}/{accountId}")
    public Produto um(@PathVariable UUID codigo, @PathVariable String accountId) {

        return produtoRepository.findByCodigoAndAccountAccountId(codigo, accountId);

    }

    @DeleteMapping("/{codigo}/{accountId}")
    public Mensagem delete(@PathVariable UUID codigo, @PathVariable String accountId) {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        Account account = accountService.getAccountByAccountId(accountId);
        produto.setAccount(account);
        int quantidadeDeProdutos =
                pedidoRepository.pedidosDoProdutoEncontrados(produto);
        Boolean block_delecao = false;
        if (quantidadeDeProdutos > 0 ){
            block_delecao = true;
            return new Mensagem("Preciso bloquear a deleção", block_delecao );
        } else {
            produtoRepository.deleteById(codigo);
            return new Mensagem(DELETADO_COM_SUCESSO, block_delecao);
        }
    }

    @PatchMapping("{codigo}/ativo/{accountId}")
    public Produto mudarStatus(@PathVariable UUID codigo, @PathVariable String accountId) {

        Produto produto = produtoRepository.findByCodigoAndAccountAccountId(codigo, accountId);


        if (produto.getAtivo()) {
            produto.setAtivo(false);
        } else {
            produto.setAtivo(true);
        }
        return produtoRepository.save(produto);
    }

    @PutMapping("/{codigo}/{accountId}")
    public Produto alterar(@RequestBody Produto produto, @PathVariable UUID codigo, @PathVariable String accountId) {

        Account account = accountService.getAccountByAccountId(accountId);
        produto.setAccount(account);
        produto.setCodigo(codigo);
        return produtoRepository.save(produto);
    }


}
