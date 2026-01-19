package com.example.demo.services;

import java.math.BigDecimal;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.repo.AccountRepository;
import com.example.demo.repo.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private CustomerRepository customerRepo;
    
    private static final Logger log =
            LoggerFactory.getLogger(AccountServiceImpl.class);

    public Account createAccount(Long customerId, String accountType, BigDecimal balance) {


        log.info("Creating account for customerId={}, accountType={}, initialBalance={}",
                customerId, accountType, balance);
        customerRepo.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));

      
        Account acc = new Account();
        acc.setCustomerId(customerId);
        acc.setAccountType(accountType.toUpperCase());
        acc.setAccountNumber("ACC" + System.currentTimeMillis());
        acc.setBalance(balance);
        acc.setStatus("ACTIVE"); 
        acc.setCreatedDate(LocalDateTime.now());
        
        log.info("Account {} created successfully", acc.getAccountNumber());

        return accountRepo.save(acc);
    }

    @Override
    public BigDecimal getBalance(String accountNumber) {
        Account acc = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                new ResourceNotFoundException("Account not found"));

        return acc.getBalance();
    }
}


