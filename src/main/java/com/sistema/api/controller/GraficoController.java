package com.sistema.api.controller;

import com.sistema.api.dto.GraficoDto;
import com.sistema.api.repository.ClienteRepository;
import com.sistema.api.repository.PedidoRepository;
import com.sistema.api.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/graficos")
@CrossOrigin("*")
public class GraficoController {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    public GraficoController(ProdutoRepository produtoRepository, ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/totais")
    public List<GraficoDto> getTotais(){

        List<GraficoDto> dados = new ArrayList<>();

        GraficoDto produtos = new GraficoDto();
        produtos.setTotal(produtoRepository.total());
        produtos.setCategoria("Produtos");


        GraficoDto clientes = new GraficoDto();
        clientes.setTotal(clienteRepository.total());
        clientes.setCategoria("Clientes");

        GraficoDto pedidos = new GraficoDto();
        pedidos.setTotal(pedidoRepository.total());
        pedidos.setCategoria("Pedidos");

        dados.add(produtos);
        dados.add(clientes);
        dados.add(pedidos);

        return dados;
    }
}
