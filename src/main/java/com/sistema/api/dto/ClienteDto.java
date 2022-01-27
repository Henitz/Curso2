package com.sistema.api.dto;

import com.sistema.api.model.Account;

import java.util.UUID;

public class ClienteDto {

    private UUID id;
    private String nome;
    private Integer quantidadeFuncionarios;
    private String cidade;
    private String estado;
    private String pais;
    private Boolean ativo;

    public ClienteDto(){}
    public ClienteDto(UUID id, String nome, String cidade, String estado, String pais, Integer quantidadeFuncionarios, Boolean ativo, Account account) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.quantidadeFuncionarios = quantidadeFuncionarios;
        this.ativo = ativo;
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
}
