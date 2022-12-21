package com.sistema.api.controller;

import com.sistema.api.dto.ClienteDto;
import com.sistema.api.home.Main;
import com.sistema.api.model.Account;
import com.sistema.api.model.Cliente;
import com.sistema.api.model.Mensagem;
import com.sistema.api.service.AccountService;
import com.sistema.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return clienteService.findByAccountId(accountId).stream()
                .map(ClienteDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/{accountId}")
    public ClienteDto salvar(@PathVariable String accountId, @RequestBody ClienteDto clienteDto) {
        Account account = accountService.getAccountByAccountId(accountId);
        clienteDto.setAccount(account);
        Cliente cliente = new Cliente(clienteDto);
        return new ClienteDto(clienteService.salvar(cliente));
    }

    @GetMapping("/{id}/{accountId}")
    public ClienteDto um(@PathVariable UUID id, @PathVariable String accountId) {
        return new ClienteDto(clienteService.findByIdAndAccountAccountId(id, accountId));
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
    public Object alterar(@RequestBody ClienteDto clienteDto, @PathVariable UUID id, @PathVariable String accountId) {
        Account account = accountService.getAccountByAccountId(accountId);
        clienteDto.setAccount(account);
        Cliente cliente = new Cliente(clienteDto);
        if(clienteService.findById(id).getAccount().equals(account)){
            return new ClienteDto(clienteService.salvar(cliente));
        }
        return new Mensagem(REGISTRO_NAO_ENCONTRADO);
    }
}
