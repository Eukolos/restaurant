package com.luinwee.restaurant.service;

import com.luinwee.restaurant.dto.AccountDto;
import com.luinwee.restaurant.dto.OrderDto;
import com.luinwee.restaurant.model.Account;
import com.luinwee.restaurant.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
    protected Account produceAccount(Account account) {
        return repository.save(account);
    }

    public AccountDto updateAccount(AccountDto accountDto) {
        Account account = repository.findById(accountDto.id()).orElseThrow();
        return AccountDto.toDto(repository.save(
                new Account(
                        account.getId(),
                        accountDto.totalPrice(),
                        OrderDto.toModelList(accountDto.orders()),
                        accountDto.createdAt(),
                        accountDto.updatedAt()
                )
        ));
    }

    public void deleteAccount(Long accountId){
        repository.deleteById(accountId);
    }


}
