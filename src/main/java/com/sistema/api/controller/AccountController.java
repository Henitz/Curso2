package com.sistema.api.controller;

import com.sistema.api.controller.request.AccountLoginRequest;
import com.sistema.api.dto.AccountDto;
import com.sistema.api.model.Account;
import com.sistema.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@CrossOrigin("*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public AccountDto save(@RequestBody AccountLoginRequest request) {

        Account accountReturn = accountService.save(request);
        AccountDto dtoReturn = new AccountDto();
        dtoReturn.setId(accountReturn.getId());
        dtoReturn.setAccountId(accountReturn.getAccountId());
        dtoReturn.setEmail(accountReturn.getEmail());

        return dtoReturn;
    }
}

