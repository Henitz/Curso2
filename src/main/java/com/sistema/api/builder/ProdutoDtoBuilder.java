package com.sistema.api.builder;

import com.sistema.api.dto.ClienteDto;
import com.sistema.api.dto.ProdutoDto;

import java.util.UUID;

public class ProdutoDtoBuilder {

    private UUID codigo;
    private String nome;
    private String descricao;
    private Boolean ativo;

    public ProdutoDtoBuilder codigo(UUID codigo){
        this.codigo=codigo;
        return this;
    }

    public ProdutoDtoBuilder nome(String nome){
        this.nome=nome;
        return this;
    }

    public ProdutoDtoBuilder descricao(String descricao){
        this.descricao=descricao;
        return this;
    }

    public ProdutoDtoBuilder ativo(Boolean ativo){
        this.ativo=ativo;
        return this;
    }

    public ProdutoDtoBuilder(){ }
    public ProdutoDto build() { return new ProdutoDto(codigo,nome,descricao,ativo); }

}
