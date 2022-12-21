package com.sistema.api.dto;

import com.sistema.api.model.Account;
import com.sistema.api.model.Produto;

import java.util.UUID;

public class ProdutoDto {
    private UUID codigo;
    private String nome;
    private String descricao;
    private Boolean ativo;
    private Account account;

    public ProdutoDto(){ }

    public ProdutoDto(UUID codigo, String nome, String descricao, Boolean ativo) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.account = account;
    }

    public ProdutoDto(Produto produto){
        this.codigo = produto.getCodigo();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.ativo = produto.getAtivo();
        this.account = produto.getAccount();
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
