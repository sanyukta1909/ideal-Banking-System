package com.example.demo.controller;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.repo.AccountRepository;
import com.example.demo.services.AccountService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	 	@Autowired
	    private AccountService accountService;
	 	
	 	@Autowired
	 	private AccountRepository accountRepo;

	    @PostMapping
	    public Map<String, String> createAccount(@RequestBody Map<String, Object> request) {
	    	
	    	 if (!request.containsKey("customerId") || !request.containsKey("accountType")) {
	    	        throw new RuntimeException("customerId or accountType missing");
	    	    }
	        Long customerId = Long.valueOf(request.get("customerId").toString());
	        String accountType = request.get("accountType").toString();
	        
	        BigDecimal balance = BigDecimal.ZERO;
	        if (request.containsKey("balance")) {
	            balance = new BigDecimal(request.get("balance").toString());
	        }
	        
	        Account account = accountService.createAccount(customerId, accountType,balance);

	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Account is created successfully");
	        response.put("accountNumber", account.getAccountNumber());
	        response.put("status", account.getStatus());

	        return response;
	    }
	    
	    @GetMapping("/{accountNumber}/balance")
	    public BigDecimal getBalance(@PathVariable String accountNumber) {

	        Account account = accountRepo.findByAccountNumber(accountNumber)
	                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

	        return account.getBalance();
	    }



}
