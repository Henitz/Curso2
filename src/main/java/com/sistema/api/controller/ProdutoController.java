package com.sistema.api.controller;

import com.sistema.api.builder.ClienteBuilder;
import com.sistema.api.builder.ProdutoBuilder;
import com.sistema.api.builder.ProdutoDtoBuilder;
import com.sistema.api.controller.Response.AccountResponse;
import com.sistema.api.dto.ClienteDto;
import com.sistema.api.dto.ProdutoDto;
import com.sistema.api.home.Main;
import com.sistema.api.model.Account;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Mensagem;
import com.sistema.api.model.Produto;
import com.sistema.api.service.AccountService;
import com.sistema.api.service.PedidoService;
import com.sistema.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        List<Produto> produtos = produtoService.findByAccountId(accountId);
        List<ProdutoDto> produtosDto = new ArrayList<>();

        for (Produto produto : produtos) {

            ProdutoDto produtoDto = new ProdutoDto();
            produtoDto.setCodigo(produto.getCodigo());
            produtoDto.setNome(produto.getNome());
            produtoDto.setDescricao(produto.getDescricao());
            produtoDto.setAtivo(produto.getAtivo());

           produtosDto.add(produtoDto);

        }

        return produtosDto;

    }

    @PostMapping("/{accountId}")
    public ProdutoDto salvar(@PathVariable String accountId, @RequestBody ProdutoDto dto) {

//        Produto produto = new Produto();
//        produto.setNome(dto.getNome());
//        produto.setAtivo(dto.getAtivo());
//        produto.setDescricao(dto.getDescricao());

        Account account = accountService.getAccountByAccountId(accountId);

//        produto.setAccount(account);
        Produto produto = new ProdutoBuilder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .ativo(dto.getAtivo())
            .build();



        Produto produtoReturn = produtoService.salvar(produto);


//        ProdutoDto dtoReturn = new ProdutoDto();
//        dtoReturn.setCodigo(produtoReturn.getCodigo());
//        dtoReturn.setDescricao(produtoReturn.getNome());
//        dtoReturn.setAtivo(true);


        ProdutoDto produtoDto = new ProdutoDtoBuilder()
                .codigo(produtoReturn.getCodigo())
                .nome(produtoReturn.getNome())
                .descricao(produtoReturn.getDescricao())
                .ativo(produtoReturn.getAtivo())
            .build();


        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId(produtoReturn.getAccount().getAccountId());

        return produtoDto;

     }


    @GetMapping("/{codigo}/{accountId}")
    public ProdutoDto um(@PathVariable UUID codigo, @PathVariable String accountId) {


        Produto produtoReturn = produtoService.findByCodigoAndAccountAccountId(codigo, accountId);
        ProdutoDto dtoReturn = new ProdutoDto();
        dtoReturn.setCodigo(produtoReturn.getCodigo());
        dtoReturn.setNome(produtoReturn.getNome());
        dtoReturn.setDescricao(produtoReturn.getDescricao());
        dtoReturn.setAtivo(produtoReturn.getAtivo());

        return dtoReturn;

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
    public Produto alterar(@RequestBody Produto produto, @PathVariable UUID codigo, @PathVariable String accountId) {

        Account account = accountService.getAccountByAccountId(accountId);
        produto.setAccount(account);
        produto.setCodigo(codigo);
        return produtoService.salvar(produto);
    }


}
