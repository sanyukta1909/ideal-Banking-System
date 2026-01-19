package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    
    @NotNull(message = "Customer ID is required")
    @Column(name = "customer_id",nullable = false)
    private Long customerId;
    
    @Column(name = "account_number",unique = true, updatable = false)
    private String accountNumber;
    
    @NotBlank(message = "Account type is required")
    @Pattern(regexp = "SAVINGS|CURRENT",message = "Account type must be SAVINGS or CURRENT")
    @Column(name="account_type")
    private String accountType;
    
    
    @NotNull(message = "Initial balance is required")
    @DecimalMin(value = "0.0",inclusive = true,message = "Initial balance cannot be negative")
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
    
    @NotBlank(message = "Account status is required")
    @Pattern(regexp = "ACTIVE|INACTIVE",message = "Account status must be ACTIVE or INACTIVE")
    private String status;
    
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now(); 
    public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public Account(Long accountId, Long customerId, String accountNumber, String accountType, BigDecimal balance,
			String status) {
		super();
		this.accountId = accountId;
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.status = status;
	}

	
    public Account() {}

    // getters and setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
