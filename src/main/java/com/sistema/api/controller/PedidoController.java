package com.sistema.api.controller;


import com.sistema.api.builder.*;
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

import java.text.ParseException;
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

//    private String formataDataVolta(LocalDate date){
//
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        //  String formattedString = localDate.format(formatter);
//
////        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
//        return formatador.format(date);
//
//    }

//    private Date formataDataIda(LocalDate date) throws ParseException {
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//      //  String formattedString = localDate.format(formatter);
//
////        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
////        return formatador.parse(date)
//        return dtf.format(date);
//    }

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

//        Pedido pedido = new Pedido();
//        pedido.setData(dto.getData());
//        pedido.setAtivo(dto.getAtivo());

        Pedido pedido = new PedidoBuilder()
                .data(dto.getData())
                .ativo(dto.getAtivo())
                .build();

        Account account = accountService.getAccountByAccountId(accountId);
        pedido.setAccount(account);

        if(!(Objects.isNull(dto.getCliente()) || Objects.isNull(dto.getProduto()))) {

            Cliente cliente = new ClienteBuilder()
                    .id(dto.cliente.getId())
                    .nome(dto.cliente.getNome())
                    .cidade(dto.cliente.getCidade())
                    .estado(dto.cliente.getEstado())
                    .pais(dto.cliente.getPais())
                    .ativo(dto.cliente.getAtivo())
                .build();
            pedido.setCliente(cliente);





//            Cliente cliente = new Cliente();
//            cliente.setId(dto.getCliente().getId());
//            cliente.setNome(dto.getCliente().getNome());
//            cliente.setCidade(dto.getCliente().getCidade());
//            cliente.setEstado(dto.getCliente().getEstado());
//            cliente.setPais(dto.getCliente().getPais());
//            cliente.setAtivo(dto.getCliente().getAtivo());
//            pedido.setCliente(cliente);

            Produto produto = new ProdutoBuilder()
                    .codigo(dto.produto.getCodigo())
                    .nome(dto.produto.getNome())
                    .descricao(dto.produto.getDescricao())
                    .ativo(dto.produto.getAtivo())
                .build();
            pedido. setProduto(produto);




//            Produto produto = new Produto();
//            produto.setCodigo(dto.getProduto().getCodigo());
//            produto.setNome(dto.getProduto().getNome());
//            produto.setAtivo(dto.getProduto().getAtivo());
//            pedido.setProduto(produto);

        }

        Pedido pedidoReturn = pedidoService.save(pedido);

        PedidoDto dtoReturn = new PedidoDtoBuilder()
                .codigo(dto.getCodigo())
                .data(dto.getData())
            .build();




//        PedidoDto dtoReturn = new PedidoDto();
//        dtoReturn.setCodigo(pedidoReturn.getCodigo());
//        dtoReturn.setData(pedidoReturn.getData());


        AccountDto accountDto = new AccountDto();
        accountDto.setId(pedidoReturn.getAccount().getId());

        if(!Objects.isNull(dto.getProduto()) || Objects.isNull(dto.getCliente())) {


            ProdutoDto produtoDto = new ProdutoDtoBuilder()
                    .codigo(produtoService.findByCodigo(dto.produto.getCodigo()).getCodigo())
                    .nome(produtoService.findByCodigo(dto.getProduto().getCodigo()).getNome())
                    .descricao(produtoService.findByCodigo(dto.getProduto().getCodigo()).getDescricao())
                    .ativo(produtoService.findByCodigo(dto.getProduto().getCodigo()).getAtivo())
                .build();
            dtoReturn.setProduto(produtoDto);

            ClienteDto clienteDto = new ClienteDtoBuilder()
                    .id(clienteService.findById(dto.getCliente().getId()).getId())
                    .nome(clienteService.findById(dto.getCliente().getId()).getNome())
                    .cidade(clienteService.findById(dto.getCliente().getId()).getCidade())
                    .estado(clienteService.findById(dto.getCliente().getId()).getEstado())
                    .pais(clienteService.findById(dto.getCliente().getId()).getPais())
                    .ativo(clienteService.findById(dto.getCliente().getId()).getAtivo())
                .build();
            dtoReturn.setCliente(clienteDto);
//            ProdutoDto produtoDto = new ProdutoDto();
//            produtoDto.setCodigo(produtoService.findByCodigo(dto.getProduto().getCodigo()).getCodigo());
//            produtoDto.setNome(produtoService.findByCodigo(dto.getProduto().getCodigo()).getNome());
//            produtoDto.setDescricao(produtoService.findByCodigo(dto.getProduto().getCodigo()).getDescricao());
//            produtoDto.setAtivo(produtoService.findByCodigo(dto.getProduto().getCodigo()).getAtivo());
//            dtoReturn.setProduto(produtoDto);
//
//            ClienteDto clienteDto = new ClienteDto();
//            clienteDto.setId(clienteService.findById(dto.getCliente().getId()).getId());
//            clienteDto.setNome(clienteService.findById(dto.getCliente().getId()).getNome());
//            clienteDto.setCidade(clienteService.findById(dto.getCliente().getId()).getCidade());
//            clienteDto.setEstado(clienteService.findById(dto.getCliente().getId()).getEstado());
//            clienteDto.setPais(clienteService.findById(dto.getCliente().getId()).getPais());
//            clienteDto.setAtivo(clienteService.findById(dto.getCliente().getId()).getAtivo());
//            dtoReturn.setCliente(clienteDto);
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
