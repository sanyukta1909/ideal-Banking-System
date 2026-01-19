
package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Transaction;
import com.example.demo.services.TransactionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	
	@Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public String deposit(@RequestBody Map<String, Object> req) {
        return transactionService.deposit(
                req.get("accountNumber").toString(),
                new BigDecimal(req.get("amount").toString()));
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestBody Map<String, Object> req) {
        return transactionService.withdraw(
                req.get("accountNumber").toString(),
                new BigDecimal(req.get("amount").toString()));
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody Map<String, Object> req) {
        return transactionService.transfer(
                req.get("fromAccount").toString(),
                req.get("toAccount").toString(),
                new BigDecimal(req.get("amount").toString()));
    }
    
    @GetMapping("/{accountNumber}")
    public List<Transaction> getTransactionHistory(@PathVariable String accountNumber) {

        return transactionService.getTransactionHistory(accountNumber);
    }

   
}

