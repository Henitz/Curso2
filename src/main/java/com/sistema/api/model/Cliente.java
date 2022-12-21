package com.sistema.api.model;

import com.sistema.api.dto.ClienteDto;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String cidade;
    private String estado;
    private String pais;
    private String nome;
    private  Integer quantidadeFuncionarios;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Cliente(){}
    public Cliente(UUID id, String nome, String cidade, String estado, String pais, Integer quantidadeFuncionarios, Boolean ativo, Account account) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.quantidadeFuncionarios = quantidadeFuncionarios;
        this.ativo = ativo;
        this.account = account;
    }

    public Cliente(ClienteDto clienteDto){
        this.id = clienteDto.getId();
        this.nome = clienteDto.getNome();
        this.cidade = clienteDto.getCidade();
        this.estado = clienteDto.getEstado();
        this.pais = clienteDto.getPais();
        this.quantidadeFuncionarios = clienteDto.getQuantidadeFuncionarios();
        this.ativo = clienteDto.getAtivo();
        this.account = clienteDto.getAccount();
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
}
