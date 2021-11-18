package com.sistema.api.service;

import com.sistema.api.controller.request.AccountLoginRequest;
import com.sistema.api.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface AccountService extends UserDetailsService {
    Account save(AccountLoginRequest accountLoginRequest);
    Account getAccountByAccountId(String id);
    String getAccountByEmail(String email);
}
