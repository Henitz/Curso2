package com.sistema.api.builder;

import com.sistema.api.model.Account;
import com.sistema.api.model.Cliente;

import java.util.UUID;

public class ClienteBuilder {

    private UUID id;
    private String cidade;
    private String estado;
    private String pais;
    private String nome;
    private Integer quantidadeFuncionarios;
    private Boolean ativo;
    private Account account;

    public ClienteBuilder id(UUID id){
        this.id=id;
        return this;
    }

    public ClienteBuilder cidade(String cidade){
        this.cidade=cidade;
        return this;
    }

    public ClienteBuilder estado(String estado){
        this.estado =estado;
        return this;
    }

    public ClienteBuilder pais(String pais){
        this.pais =pais;
        return this;
    }

    public ClienteBuilder nome(String nome){
        this.nome=nome;
        return this;
    }

    public ClienteBuilder quantiadeFuncionarios(Integer quantidadeFuncionarios){
        this.quantidadeFuncionarios=quantidadeFuncionarios;
        return this;
    }

    public ClienteBuilder ativo(Boolean ativo) {
        this.ativo=ativo;
        return this;
    }

    public ClienteBuilder account(Account account){
        this.account=account;
        return this;
    }

    public ClienteBuilder(){ }
    public Cliente build() { return new Cliente(id,nome,cidade,estado,pais,quantidadeFuncionarios,ativo,account); }

}
