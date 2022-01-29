package com.sistema.api.builder;

import com.sistema.api.model.Cliente;
import com.sistema.api.model.Produto;

import java.util.UUID;

public class ProdutoBuilder {
    private UUID codigo;
    private String nome;
    private String descricao;
    private Boolean ativo;


    public ProdutoBuilder codigo(UUID codigo){
        this.codigo = codigo;
        return this;
    }

    public ProdutoBuilder nome(String nome){
        this.nome = nome;
        return this;
    }

    public ProdutoBuilder descricao(String descricao){
        this.descricao = descricao;
        return this;
    }

    public ProdutoBuilder ativo(Boolean ativo){
        this.ativo = ativo;
        return this;
    }

    public ProdutoBuilder(){ }
    public Produto build() { return new Produto(codigo,nome,descricao,ativo); }

}
