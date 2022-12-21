package com.sistema.api.dto;

import com.sistema.api.model.Account;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Produto;

import java.util.UUID;

public class ClienteDto {

    private UUID id;
    private String nome;
    private Integer quantidadeFuncionarios;
    private String cidade;
    private String estado;
    private String pais;
    private Boolean ativo;
    private Account account;

    public ClienteDto(){}
    public ClienteDto(UUID id, String nome, String cidade, String estado, String pais, Integer quantidadeFuncionarios, Boolean ativo, Account account) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.quantidadeFuncionarios = quantidadeFuncionarios;
        this.ativo = ativo;
        this.account = account;
    }

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cidade = cliente.getCidade();
        this.estado = cliente.getEstado();
        this.pais = cliente.getPais();
        this.quantidadeFuncionarios = cliente.getQuantidadeFuncionarios();
        this.ativo = cliente.getAtivo();
        this.account = cliente.getAccount() ;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeFuncionarios() {
        return quantidadeFuncionarios;
    }

    public void setQuantidadeFuncionarios(Integer quantidadeFuncionarios) {
        this.quantidadeFuncionarios = quantidadeFuncionarios;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
