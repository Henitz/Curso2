package com.sistema.api.controller;

import com.sistema.api.home.MainController;
import com.sistema.api.model.Mensagem;
import com.sistema.api.model.Produto;
import com.sistema.api.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController extends MainController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Produto> todos() {
        return (List<Produto>) produtoRepository.findAll();
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        produto.setAtivo(true);
        return produtoRepository.save(produto);
    }


    @GetMapping("/{codigo}")
    public Produto um(@PathVariable Integer codigo) {
        return produtoRepository.findById(codigo).get();
    }

    @DeleteMapping("/{codigo}")
    public Mensagem delete(@PathVariable Integer codigo) {
        if (!produtoRepository.existsById(codigo)) {
            return new Mensagem(REGISTRO_NAO_ENCONTRADO);
        }
        produtoRepository.deleteById(codigo);
        return new Mensagem(DELETADO_COM_SUCESSO);
    }

    @PatchMapping("{codigo}/ativo")
    public Produto mudarStatus(@PathVariable Integer codigo) {
        Produto produto = produtoRepository.findById(codigo).get();
        if (produto.getAtivo()) {
            produto.setAtivo(false);
        } else {
            produto.setAtivo(true);
        }
        return produtoRepository.save(produto);
    }

    @PutMapping("/{codigo}")
    public Produto alterar(@RequestBody Produto produto, @PathVariable int codigo) {
        produto.setCodigo(codigo);
        return produtoRepository.save(produto);
    }


}
