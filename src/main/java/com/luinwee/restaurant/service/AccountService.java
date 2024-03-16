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

    public Account getAccount(Long accountId) {
        return repository.findById(accountId).orElseThrow();
    }

    public List<AccountDto> getAccountList() {
        return AccountDto.toDtoList(repository.findAll());
    }

//    public AccountDto createAccount(AccountDto accountDto) {
//        return AccountDto.toDto(repository.save(AccountDto.toModel(accountDto)));
//    }
    public Account produceAccount(Account account) {
        return repository.save(account);
    }

    public AccountDto getAccountByTableIdAndIsActive(Long tableId, boolean isActive){
        Account account = repository.findAccountByTableIdAndIsActive(tableId, isActive).orElse(new Account());
        if (account.getId() == null) {
            return new AccountDto(
                    0L,
                    0,
                    false,
                    List.of()
            );
        }
        return AccountDto.toDto(account);
    }

    public Account updateAccount(Account account) {
        Account accountFromRepo = repository.findById(account.getId()).orElseThrow();
        return repository.save(
                new Account(
                        accountFromRepo.getId(),
                        account.getTotalPrice(),
                        account.getIsActive(),
                        account.getOrders(),
                        account.getTable()
                )
        );
    }

    public void deleteAccount(Long accountId){
        repository.deleteById(accountId);
    }


}
