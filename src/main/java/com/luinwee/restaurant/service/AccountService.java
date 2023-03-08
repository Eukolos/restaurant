package com.luinwee.restaurant.service;

import com.luinwee.restaurant.dto.AccountDto;
import com.luinwee.restaurant.dto.ProductDto;
import com.luinwee.restaurant.model.Account;
import com.luinwee.restaurant.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public AccountDto getAccount(Long accountId) {
        return AccountDto.toDto(repository.findById(accountId).orElseThrow());
    }

    public List<AccountDto> getAccountList() {
        return AccountDto.toDtoList(repository.findAll());
    }

    public AccountDto createAccount(AccountDto accountDto) {
        return AccountDto.toDto(repository.save(AccountDto.toModel(accountDto)));
    }

    public AccountDto updateAccount(AccountDto accountDto) {
        Account account = repository.findById(accountDto.id()).orElseThrow();
        return AccountDto.toDto(repository.save(
                new Account(
                        account.getId(),
                        accountDto.totalPrice(),
                        ProductDto.toModelList(accountDto.products()),
                        accountDto.createdAt(),
                        accountDto.updatedAt()
                )
        ));
    }

    public void deleteAccount(Long accountId){
        repository.deleteById(accountId);
    }


}
