package com.screenprog.application.security;

import com.screenprog.application.repo.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    private final AccountRepository accountRepository;

    public SecurityService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean ownsAccount(String username, Long accountNumber) {
        return accountRepository.existsByAccountNumberAndCustomer_CustomerID(accountNumber, Long.valueOf(username));
    }

    public boolean ownsCustomerId(String username, Long customerId){
        return Long.valueOf(username).equals(customerId);
    }
    public boolean ownsUsername(String authUsername, String sentUsername){
        return authUsername.equals(sentUsername);
    }

}
