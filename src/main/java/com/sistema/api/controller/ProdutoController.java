package com.sistema.api.controller;

import com.sistema.api.home.MainController;
import com.sistema.api.model.Account;
import com.sistema.api.model.Mensagem;
import com.sistema.api.model.Produto;
import com.sistema.api.repository.ProdutoRepository;
import com.sistema.api.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController extends MainController {

    private ProdutoRepository produtoRepository;
    private AccountService accountService;

    public ProdutoController(ProdutoRepository produtoRepository,
                             AccountService accountService
    ) {
        this.produtoRepository = produtoRepository;
        this.accountService = accountService;
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
    public Produto um(@PathVariable Integer codigo, @PathVariable String accountId) {

        return produtoRepository.findByCodigoAndAccountAccountId(codigo, accountId);

    }

    @DeleteMapping("/{codigo}")
    public Mensagem delete(@PathVariable Integer codigo) {
        if (!produtoRepository.existsById(codigo)) {
            return new Mensagem(REGISTRO_NAO_ENCONTRADO);
        }
        produtoRepository.deleteById(codigo);
        return new Mensagem(DELETADO_COM_SUCESSO);
    }

    @PatchMapping("{codigo}/ativo/{accountId}")
    public Produto mudarStatus(@PathVariable Integer codigo, @PathVariable String accountId) {

        Produto produto = produtoRepository.findByCodigoAndAccountAccountId(codigo, accountId);


        if (produto.getAtivo()) {
            produto.setAtivo(false);
        } else {
            produto.setAtivo(true);
        }
        return produtoRepository.save(produto);
    }

    @PutMapping("/{codigo}/{accountId}")
    public Produto alterar(@RequestBody Produto produto, @PathVariable int codigo, @PathVariable String accountId) {

        Account account = accountService.getAccountByAccountId(accountId);
        produto.setAccount(account);
        produto.setCodigo(codigo);
        return produtoRepository.save(produto);
    }


}
