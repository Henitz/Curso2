package com.sistema.api.model;

public class Mensagem {

    private String titulo;

    Boolean block_delecao;

    public Mensagem(String titulo) {
        this.titulo = titulo;
    }

    public Mensagem(String titulo, Boolean block_delecao) {
        this.titulo = titulo;
        this.block_delecao = block_delecao;
    }

    public Boolean getBlock_delecao() {
        return block_delecao;
    }

    public void setBlock_delecao(Boolean block_delecao) {
        this.block_delecao = block_delecao;
    }

    public String getTitulo() {
        return titulo;
    }
}
