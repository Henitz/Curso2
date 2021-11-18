package com.sistema.api.service.impl;

import com.sistema.api.controller.request.AccountLoginRequest;
import com.sistema.api.model.Account;
import com.sistema.api.repository.AccountRepository;
import com.sistema.api.service.AccountService;
import com.sistema.api.shared.Utils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final Utils utils;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, Utils utils, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.utils = utils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account save(AccountLoginRequest accountLoginRequest){
        Account account = new Account();
        account.setEmail(accountLoginRequest.getEmail());
        account.setEncryptedPassword(passwordEncoder.encode(accountLoginRequest.getPassword()));
        account.setAccountId(utils.generateAccountId(30));
        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            throw new UsernameNotFoundException(email);
        }

        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public Account getAccountByAccountId(String accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            throw new UsernameNotFoundException(accountId);
        }

        return account;
    }

    @Override
    public String getAccountByEmail(String email) {
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            throw new UsernameNotFoundException(email);
        }

        return account.getAccountId();
    }
}