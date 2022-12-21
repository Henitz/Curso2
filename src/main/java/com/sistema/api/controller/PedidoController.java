package com.sistema.api.controller;


import com.sistema.api.controller.Response.AccountResponse;
import com.sistema.api.dto.AccountDto;
import com.sistema.api.dto.ClienteDto;
import com.sistema.api.dto.PedidoDto;
import com.sistema.api.dto.ProdutoDto;
import com.sistema.api.home.Main;
import com.sistema.api.model.*;
import com.sistema.api.service.AccountService;
import com.sistema.api.service.ClienteService;
import com.sistema.api.service.PedidoService;
import com.sistema.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController extends Main {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}")
    public List<PedidoDto> todos(@PathVariable String accountId) {

        List<Pedido> pedidos = pedidoService.findByAccountId(accountId);
        List<PedidoDto> pedidosDto = new ArrayList<>();

        for(Pedido pedido : pedidos) {

            PedidoDto pedidoDto = new PedidoDto();
            pedidoDto.setCodigo(pedido.getCodigo());
            pedidoDto.setData(pedido.getData());
            pedidoDto.setAtivo(pedido.getAtivo());

            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setId(pedido.getCliente().getId());
            clienteDto.setNome(pedido.getCliente().getNome());
            clienteDto.setCidade(pedido.getCliente().getCidade());
            clienteDto.setEstado(pedido.getCliente().getEstado());
            clienteDto.setPais(pedido.getCliente().getPais());
            clienteDto.setAtivo(pedido.getCliente().getAtivo());
            pedidoDto.setCliente(clienteDto);

            ProdutoDto produtoDto = new ProdutoDto();
            produtoDto.setCodigo(pedido.getProduto().getCodigo());
            produtoDto.setNome(pedido.getProduto().getNome());
            produtoDto.setDescricao(pedido.getProduto().getDescricao());
            produtoDto.setAtivo(pedido.getProduto().getAtivo());
            pedidoDto.setProduto(produtoDto);

            pedidosDto.add(pedidoDto);
        }
        return pedidosDto;

    }

    @GetMapping("/{codigo}/{accountId}")
    public PedidoDto um(@PathVariable UUID codigo, @PathVariable String accountId) {

        Pedido pedidoSaved = pedidoService.findByCodigoAndAccountAccountId(codigo, accountId);
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setCodigo(pedidoSaved.getCodigo());
        pedidoDto.setData(pedidoSaved.getData());
        pedidoDto.setAtivo(pedidoSaved.getAtivo());

        Cliente clienteSaved = clienteService.findByIdAndAccountAccountId(pedidoSaved.getCliente().getId(), accountId);
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(clienteSaved.getId());
        clienteDto.setNome(clienteSaved.getNome());
        clienteDto.setCidade(clienteSaved.getCidade());
        clienteDto.setEstado(clienteSaved.getEstado());
        clienteDto.setPais(clienteSaved.getPais());
        clienteDto.setAtivo(clienteSaved.getAtivo());

        Produto produtoSaved = produtoService.findByCodigoAndAccountAccountId(pedidoSaved.getProduto().getCodigo(), accountId);
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setCodigo(produtoSaved.getCodigo());
        produtoDto.setNome(produtoSaved.getNome());
        produtoDto.setDescricao(produtoSaved.getDescricao());
        produtoDto.setAtivo(produtoSaved.getAtivo());

        return pedidoDto;

    }

    @PostMapping("/{accountId}")
    public PedidoDto save(@PathVariable String accountId, @RequestBody PedidoDto dto)  {

        Pedido pedido = new Pedido();
        pedido.setData(dto.getData());
        pedido.setAtivo(dto.getAtivo());

        Account account = accountService.getAccountByAccountId(accountId);
        pedido.setAccount(account);

        if(!(Objects.isNull(dto.getCliente()) || Objects.isNull(dto.getProduto()))) {

            Cliente cliente = new Cliente(dto.getCliente());
            pedido.setCliente(cliente);

            Produto produto = new Produto(dto.getProduto());
            pedido.setProduto(produto);
        }

        Pedido pedidoReturn = pedidoService.save(pedido);

        PedidoDto dtoReturn = new PedidoDto();
        dtoReturn.setCodigo(pedidoReturn.getCodigo());
        dtoReturn.setData(pedidoReturn.getData());

        AccountDto accountDto = new AccountDto();
        accountDto.setId(pedidoReturn.getAccount().getId());

        if(!Objects.isNull(dto.getProduto()) || Objects.isNull(dto.getCliente())) {
            dtoReturn.setCliente(new ClienteDto(pedidoReturn.getCliente()));
            dtoReturn.setProduto(new ProdutoDto(pedidoReturn.getProduto()));
        }

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId(pedido.getAccount().getAccountId());

        return dtoReturn;
    }

    @DeleteMapping("/{codigo}/{accountId}")
    public Mensagem delete(@PathVariable UUID codigo, @PathVariable String accountId) {

        Pedido pedido = new Pedido();
        pedido.setCodigo(codigo);

        Account account = accountService.getAccountByAccountId(accountId);
        pedido.setAccount(account);

        if (!pedidoService.existsByCodigo(codigo)) {
            return new Mensagem(REGISTRO_NAO_ENCONTRADO);
        }
        pedidoService.deleteByCodigo(codigo);
        return new Mensagem(DELETADO_COM_SUCESSO);
    }

    @PatchMapping("{codigo}/ativo")
    public Pedido mudarStatus(@PathVariable UUID codigo, @PathVariable String accountId) {

        Pedido pedido = pedidoService.findByCodigoAndAccountAccountId(codigo, accountId);

        if (pedido.getAtivo()) {
            pedido.setAtivo(false);
        } else {
            pedido.setAtivo(true);
        }
        return pedidoService.save(pedido);
    }

    @PutMapping("/{codigo}/{accountId}")
    public PedidoDto alterar(@RequestBody Pedido dto, @PathVariable UUID codigo, @PathVariable String accountId) {

        Pedido pedido = new Pedido();
        pedido.setCodigo(codigo);
        pedido.setData(dto.getData());
        pedido.setAtivo(true);

        Cliente cliente = new Cliente();
        cliente.setId(dto.getCliente().getId());
        cliente.setAtivo(true);

        Produto produto = new Produto();
        produto.setCodigo(dto.getProduto().getCodigo());
        produto.setAtivo(true);

        pedido.setCliente(cliente);
        pedido.setProduto(produto);

        Account account = accountService.getAccountByAccountId(accountId);
        pedido.setAccount(account);

        PedidoDto dtoReturn = new PedidoDto();

        if(pedidoService.findByCodigoAndAccountAccountId(codigo, accountId).getCodigo().equals(account)) {

            Pedido pedidoReturn = pedidoService.save(pedido);

            dtoReturn.setCodigo(pedidoReturn.getCodigo());
            dtoReturn.setData(pedidoReturn.getData());

            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setId(pedidoReturn.getCliente().getId());
            clienteDto.setNome(pedidoReturn.getCliente().getNome());
            clienteDto.setCidade(pedidoReturn.getCliente().getCidade());
            clienteDto.setEstado(pedidoReturn.getCliente().getEstado());
            clienteDto.setPais(pedidoReturn.getCliente().getPais());
            clienteDto.setAtivo(pedidoReturn.getCliente().getAtivo());

            dtoReturn.setCliente(clienteDto);

            ProdutoDto produtoDto = new ProdutoDto();
            produtoDto.setCodigo(pedidoReturn.getProduto().getCodigo());
            produtoDto.setNome(pedidoReturn.getProduto().getNome());
            produtoDto.setDescricao(pedidoReturn.getProduto().getDescricao());
            produtoDto.setAtivo(pedidoReturn.getProduto().getAtivo());

            dtoReturn.setProduto(produtoDto);

        }
        return dtoReturn;
    }

}
