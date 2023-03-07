package com.luinwee.restaurant.repository;


import com.luinwee.restaurant.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}

