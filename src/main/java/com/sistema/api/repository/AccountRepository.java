package com.sistema.api.repository;

import com.sistema.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
    Account findByAccountId(String accountId);
    Account getAccountByAccountId(String accountId);
}
