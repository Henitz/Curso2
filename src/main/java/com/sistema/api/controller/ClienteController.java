package com.sistema.api.controller;

import com.sistema.api.builder.ClienteBuilder;
import com.sistema.api.builder.ClienteDtoBuilder;
import com.sistema.api.controller.Response.AccountResponse;
import com.sistema.api.dto.ClienteDto;
import com.sistema.api.home.Main;
import com.sistema.api.model.Account;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Mensagem;
import com.sistema.api.service.AccountService;
import com.sistema.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController extends Main {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}")
    public List<ClienteDto> todos(@PathVariable String accountId) {
        List<Cliente> clientes = clienteService.findByAccountId(accountId);
        List<ClienteDto> clientesDto = new ArrayList<>();
        for (Cliente cliente : clientes) {

            ClienteDto clienteDto = new ClienteDtoBuilder()
                    .id(cliente.getId())
                    .nome(cliente.getNome())
                    .cidade(cliente.getCidade())
                    .estado(cliente.getEstado())
                    .pais(cliente.getPais())
                    .quantidadeFuncionarios(cliente.getQuantidadeFuncionarios())
                    .ativo(cliente.getAtivo())
                .build();

//            ClienteDto clienteDto = new ClienteDto();
//            clienteDto.setId(cliente.getId());
//            clienteDto.setNome(cliente.getNome());
//            clienteDto.setAtivo(cliente.getAtivo());
//            clienteDto.setCidade(cliente.getCidade());
//            clienteDto.setEstado(cliente.getEstado());
//            clienteDto.setPais(cliente.getPais());
//            clienteDto.setQuantidadeFuncionarios(cliente.getQuantidadeFuncionarios());
//            clientesDto.add(clienteDto);
        }
        return clientesDto;
    }

    @PostMapping("/{accountId}")
    public ClienteDto salvar(@PathVariable String accountId, @RequestBody ClienteDto dto) {
        Account account = accountService.getAccountByAccountId(accountId);
//        Cliente cliente = new Cliente();
//        cliente.setNome(dto.getNome());
//        cliente.setAtivo(dto.getAtivo());
//        cliente.setCidade(dto.getCidade());
//        cliente.setEstado(dto.getEstado());
//        cliente.setPais(dto.getPais());
//        cliente.setQuantidadeFuncionarios(dto.getQuantidadeFuncionarios());
//        cliente.setAccount(account);

        Cliente cliente = new ClienteBuilder()
                    .nome(dto.getNome())
                    .cidade(dto.getCidade())
                    .estado(dto.getEstado())
                    .pais(dto.getPais())
                    .quantidadeFuncionarios(dto.getQuantidadeFuncionarios())
                    .ativo(dto.getAtivo())
                    .account(account)
                .build();

        Cliente clienteReturn = clienteService.salvar(cliente);

        ClienteDto clienteDto = new ClienteDtoBuilder()
                    .id(clienteReturn.getId())
                    .nome(dto.getNome())
                    .cidade(dto.getCidade())
                    .estado(dto.getEstado())
                    .pais(dto.getPais())
                    .quantidadeFuncionarios(dto.getQuantidadeFuncionarios())
                    .ativo(dto.getAtivo())
                    .account(account)
                .build();

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccountId(clienteReturn.getAccount().getAccountId());

        return clienteDto;

    }

    @GetMapping("/{id}/{accountId}")
    public ClienteDto um(@PathVariable UUID id, @PathVariable String accountId) {

        Cliente clienteReturn = clienteService.findByIdAndAccountAccountId(id, accountId);
        ClienteDto dtoReturn = new ClienteDto();
        dtoReturn.setId(clienteReturn.getId());
        dtoReturn.setNome(clienteReturn.getNome());
        dtoReturn.setAtivo(clienteReturn.getAtivo());
        dtoReturn.setCidade(clienteReturn.getCidade());
        dtoReturn.setEstado(clienteReturn.getEstado());
        dtoReturn.setPais(clienteReturn.getPais());
        dtoReturn.setQuantidadeFuncionarios(clienteReturn.getQuantidadeFuncionarios());


        return dtoReturn;



    }

    @DeleteMapping("/{id}/{accountId}")
    public Mensagem delete(@PathVariable UUID id, @PathVariable String accountId) {


        if(clienteService.findById(id).getAccount().equals(accountService.getAccountByAccountId(accountId))) {
            clienteService.delete(id, accountId);
            return new Mensagem(DELETADO_COM_SUCESSO);
        }

        return new Mensagem(REGISTRO_NAO_ENCONTRADO);
    }

    @PatchMapping("{id}/ativo/{accountId}")
    public Cliente mudarStatus(@PathVariable UUID id, @PathVariable String accountId) {


        Cliente cliente = clienteService.findByIdAndAccountAccountId(id, accountId);




        if (cliente.getAtivo()) {
            cliente.setAtivo(false);
        } else {
            cliente.setAtivo(true);
        }
        return clienteService.salvar(cliente);
    }

    @PutMapping("/{id}/{accountId}")
    public ClienteDto alterar(@RequestBody ClienteDto dto, @PathVariable UUID id, @PathVariable String accountId) {

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(dto.getNome());
        cliente.setAtivo(dto.getAtivo());
        cliente.setCidade(dto.getCidade());
        cliente.setEstado(dto.getEstado());
        cliente.setPais(dto.getPais());
        cliente.setQuantidadeFuncionarios(dto.getQuantidadeFuncionarios());

        Account account = accountService.getAccountByAccountId(accountId);
        cliente.setAccount(account);

        ClienteDto dtoReturn = new ClienteDto();
        if(clienteService.findById(id).getAccount().equals(account)){
            Cliente clienteReturn = clienteService.salvar(cliente);
            dtoReturn.setId(clienteReturn.getId());
            dtoReturn.setNome(clienteReturn.getNome());
            dtoReturn.setAtivo(clienteReturn.getAtivo());
            dtoReturn.setCidade(clienteReturn.getCidade());
            dtoReturn.setEstado(clienteReturn.getEstado());
            dtoReturn.setPais(clienteReturn.getPais());
            dtoReturn.setQuantidadeFuncionarios(clienteReturn.getQuantidadeFuncionarios());

        }

        return dtoReturn;

    }

}
