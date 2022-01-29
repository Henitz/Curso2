package com.sistema.api.dto;

import com.sistema.api.model.Cliente;
import com.sistema.api.model.Produto;

import java.time.LocalDate;
import java.util.UUID;

public class PedidoDto {
    private UUID codigo;
    private LocalDate data;
    private Boolean ativo;

    public ClienteDto cliente;
    public ProdutoDto produto;

    public PedidoDto(){

    }



    public PedidoDto(UUID codigo, LocalDate data, Boolean ativo) {
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public ProdutoDto getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDto produto) {
        this.produto = produto;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
