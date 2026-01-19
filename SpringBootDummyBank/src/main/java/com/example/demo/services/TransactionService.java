package com.example.demo.services;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.model.Transaction;

public interface TransactionService {

    String deposit(String accountNumber, BigDecimal amount);

    String withdraw(String accountNumber, BigDecimal amount);

    String transfer(String fromAccount, String toAccount, BigDecimal amount);
    List<Transaction> getTransactionHistory(String accountNumber);

}