package com.sistema.api.controller;

import com.sistema.api.dto.ProdutoDto;
import com.sistema.api.home.Main;
import com.sistema.api.model.Account;
import com.sistema.api.model.Mensagem;
import com.sistema.api.model.Produto;
import com.sistema.api.service.AccountService;
import com.sistema.api.service.PedidoService;
import com.sistema.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController extends Main {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{accountId}")
    public List<ProdutoDto> todos(@PathVariable String accountId) {
        return produtoService.findByAccountId(accountId).stream()
                .map(ProdutoDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/{accountId}")
    public ProdutoDto salvar(@PathVariable String accountId, @RequestBody ProdutoDto produtoDto) {
        Account account = accountService.getAccountByAccountId(accountId);
        produtoDto.setAccount(account);
        Produto produto = new Produto(produtoDto);
        return new ProdutoDto(produtoService.salvar(produto));
    }


    @GetMapping("/{codigo}/{accountId}")
    public ProdutoDto um(@PathVariable UUID codigo, @PathVariable String accountId) {
        return new ProdutoDto(produtoService.findByCodigoAndAccountAccountId(codigo, accountId));
    }

    @DeleteMapping("/{codigo}/{accountId}")
    public Mensagem delete(@PathVariable UUID codigo, @PathVariable String accountId) {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        Account account = accountService.getAccountByAccountId(accountId);
        produto.setAccount(account);
        int quantidadeDeProdutos =
                pedidoService.pedidosDoProdutoEncontrados(produto);
        Boolean block_delecao = false;
        if (quantidadeDeProdutos > 0 ){
            block_delecao = true;
            return new Mensagem("Preciso bloquear a deleção", block_delecao );
        } else {
            produtoService.deleteByCodigo(codigo);
            return new Mensagem(DELETADO_COM_SUCESSO, block_delecao);
        }
    }

    @PatchMapping("{codigo}/ativo/{accountId}")
    public Produto mudarStatus(@PathVariable UUID codigo, @PathVariable String accountId) {

        Produto produto = produtoService.findByCodigoAndAccountAccountId(codigo, accountId);

        if (produto.getAtivo()) {
            produto.setAtivo(false);
        } else {
            produto.setAtivo(true);
        }
        return produtoService.salvar(produto);
    }

    @PutMapping("/{codigo}/{accountId}")
    public Object alterar(@RequestBody ProdutoDto produtoDto, @PathVariable UUID codigo, @PathVariable String accountId) {
        Account account = accountService.getAccountByAccountId(accountId);
        produtoDto.setAccount(account);
        Produto produto = new Produto(produtoDto);
        if(produtoService.findByCodigo(codigo).getAccount().equals(account)){
            return new ProdutoDto(produtoService.salvar(produto));
        }
        return new Mensagem(REGISTRO_NAO_ENCONTRADO);
    }
}
