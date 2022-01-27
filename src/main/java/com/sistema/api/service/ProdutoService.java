package com.sistema.api.service;


import com.sistema.api.model.Produto;
import com.sistema.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;




    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }


    public List<Produto> findByAccountId(String accountId) {
        return  produtoRepository.findByAccountAccountId(accountId);
    }


    public Produto findByCodigoAndAccountAccountId(UUID codigo, String accountId) {
        return  produtoRepository.findByCodigoAndAccountAccountId(codigo, accountId);
    }

    public int pedidosDoProdutoEncontrados( Produto produto){
        return produtoRepository.total();
    };

    public void deleteByCodigo(UUID codigo) {
    }


    public Produto findByCodigo(UUID codigo) {
        return  produtoRepository.findByCodigo(codigo);
    }

    public int total() {
        return produtoRepository.total();
    }
}
