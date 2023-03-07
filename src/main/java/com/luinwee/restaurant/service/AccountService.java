package com.luinwee.restaurant.service;

import com.luinwee.restaurant.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository repository;
    private final AccountService accountService;

    public AccountService(AccountRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }


}
