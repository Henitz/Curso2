package com.sistema.api.model;

import com.sistema.api.dto.ProdutoDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID codigo;
    private String nome;
    private String descricao;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Produto() {

    }

    public Produto(UUID codigo, String nome, String descricao, Boolean ativo, Account account) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.account = account;
    }

    public Produto(ProdutoDto produtoDto) {
        this.codigo = produtoDto.getCodigo();
        this.nome = produtoDto.getNome();
        this.descricao = produtoDto.getDescricao();
        this.ativo = produtoDto.getAtivo();
        this.account = produtoDto.getAccount();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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


}
