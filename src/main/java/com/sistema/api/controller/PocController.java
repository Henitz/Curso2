package com.sistema.api.controller;

import com.sistema.api.model.Conta;
import com.sistema.api.model.Tipo;
import com.sistema.api.model.Titular;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poc")
public class PocController {
    @GetMapping
    public Conta pegarContaCorrente(){
        Conta contacorrente = new Conta();
        Titular titular = new Titular();
        //contacorrente.setAgencia("567");
        contacorrente.setNumeroDaConta("1234-9");
        contacorrente.setTipo(Tipo.CONTA_CORRENTE);
        titular.setNome("Joao da Silva");
        titular.setCpf("123456789-89");
        contacorrente.setTitular(titular);

        return contacorrente;
    }

}
