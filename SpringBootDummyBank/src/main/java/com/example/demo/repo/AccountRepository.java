package com.example.demo.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	 Optional<Account> findByAccountNumber(String accountNumber);
}


