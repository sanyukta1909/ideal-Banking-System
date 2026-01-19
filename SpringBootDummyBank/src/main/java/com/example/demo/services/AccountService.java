package com.example.demo.services;

import java.math.BigDecimal;

import com.example.demo.model.Account;

public interface AccountService {

    Account createAccount(Long customerId, String accountType,BigDecimal balance);

	BigDecimal getBalance(String accountNumber);

}