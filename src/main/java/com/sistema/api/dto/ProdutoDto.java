package com.sistema.api.dto;

import com.sistema.api.model.Account;

import java.util.UUID;

public class ProdutoDto {
    private UUID codigo;
    private String nome;
    private String descricao;
    private Boolean ativo;

    public ProdutoDto(){

    }

    public ProdutoDto(UUID codigo, String nome, String descricao, Boolean ativo) {
    }


    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
