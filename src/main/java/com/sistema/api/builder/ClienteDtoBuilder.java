package com.sistema.api.builder;

import com.sistema.api.dto.ClienteDto;
import com.sistema.api.model.Account;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Produto;

import java.util.UUID;

public class ClienteDtoBuilder {

    private UUID id;
    private String cidade;
    private String estado;
    private String pais;
    private String nome;
    private Integer quantidadeFuncionarios;
    private Boolean ativo;
    private Account account;

    private Cliente cliente;
    private Produto produto;

    public ClienteDtoBuilder cliente(Cliente cliente) {
        this.cliente=cliente;
        return this;
    }

    public ClienteDtoBuilder produto(Produto produto) {
        this.produto=produto;
        return this;
    }

    public ClienteDtoBuilder id(UUID id){
        this.id=id;
        return this;
    }

    public ClienteDtoBuilder cidade(String cidade){
        this.cidade=cidade;
        return this;
    }

    public ClienteDtoBuilder estado(String estado){
        this.estado =estado;
        return this;
    }

    public ClienteDtoBuilder pais(String pais){
        this.pais =pais;
        return this;
    }

    public ClienteDtoBuilder nome(String nome){
        this.nome=nome;
        return this;
    }

    public ClienteDtoBuilder quantiadeFuncionarios(Integer quantidadeFuncionarios){
        this.quantidadeFuncionarios=quantidadeFuncionarios;
        return this;
    }

    public ClienteDtoBuilder ativo(Boolean ativo) {
        this.ativo=ativo;
        return this;
    }

    public ClienteDtoBuilder account(Account account){
        this.account=account;
        return this;
    }

    public ClienteDtoBuilder(){ }
    public ClienteDto build() { return new ClienteDto(id,nome,cidade,estado,pais,quantidadeFuncionarios,ativo,account,cliente,produto); }
}
