package com.sistema.api.controller;

import com.sistema.api.dto.GraficoDto;
import com.sistema.api.service.ClienteService;
import com.sistema.api.service.PedidoService;
import com.sistema.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ClienteService clienteService;


    @GetMapping("/totais")
    public List<GraficoDto> getTotais(){

        List<GraficoDto> dados = new ArrayList<>();

        GraficoDto produtos = new GraficoDto();
        produtos.setTotal(produtoService.total());
        produtos.setCategoria("Produtos");


        GraficoDto clientes = new GraficoDto();
        clientes.setTotal(clienteService.total());
        clientes.setCategoria("Clientes");

        GraficoDto pedidos = new GraficoDto();
        pedidos.setTotal(pedidoService.total());
        pedidos.setCategoria("Pedidos");

        dados.add(produtos);
        dados.add(clientes);
        dados.add(pedidos);

        return dados;
    }
}
