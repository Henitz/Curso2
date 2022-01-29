package com.sistema.api.builder;

import com.sistema.api.dto.PedidoDto;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Pedido;
import com.sistema.api.model.Produto;

import java.time.LocalDate;
import java.util.UUID;

public class PedidoDtoBuilder {

    private UUID codigo;
    private LocalDate data;
    private Boolean ativo;
    private Cliente cliente;
    private Produto produto;

    public PedidoDtoBuilder cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public PedidoDtoBuilder produto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public PedidoDtoBuilder codigo(UUID codigo){
        this.codigo=codigo;
        return this;
    }

    public PedidoDtoBuilder data(LocalDate data){
        this.data=data;
        return this;
    }

    public PedidoDtoBuilder ativo(Boolean ativo){
        this.data=data;
        return this;
    }

    public PedidoDtoBuilder(){ }
    public PedidoDto build() { return new PedidoDto(codigo,data,ativo); }

}
