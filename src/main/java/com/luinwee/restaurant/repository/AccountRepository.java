package com.luinwee.restaurant.repository;


import com.luinwee.restaurant.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByTableIdAndIsActive(Long tableId, boolean isActive);

}

