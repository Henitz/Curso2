package com.sistema.api.builder;

import com.sistema.api.model.Cliente;
import com.sistema.api.model.Pedido;
import com.sistema.api.model.Produto;

import java.time.LocalDate;
import java.util.UUID;

public class PedidoBuilder {

    private UUID codigo;
    private LocalDate data;
    private Boolean ativo;

    private Cliente cliente;
    private Produto produto;

    public PedidoBuilder produto(Produto produto){
        this.produto = produto;
        return this;
    }

    public PedidoBuilder cliente(Cliente cliente){
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder codigo(UUID codigo){
        this.codigo = codigo;
        return this;
    }

    public PedidoBuilder data(LocalDate data){
        this.data = data;
        return this;
    }

    public PedidoBuilder ativo(Boolean ativo){
        this.ativo = ativo;
        return this;
    }

    public PedidoBuilder(){ }
    public Pedido build() { return new Pedido(codigo,data,ativo,cliente,produto); }

}
