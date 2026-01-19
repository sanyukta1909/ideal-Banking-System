package com.example.demo.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "transaction_type")
    private String transactionType; // DEPOSIT, WITHDRAW, TRANSFER

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

	private BigDecimal amount;

    @Column(name = "reference_account")
    private String referenceAccount;

    @CreationTimestamp
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    private String status; //SUCCESS / FAILED

    private String remarks;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(Long transactionId, Long accountId, String transactionType, BigDecimal amount,
			String referenceAccount, LocalDateTime transactionDate, String status, String remarks) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.referenceAccount = referenceAccount;
		this.transactionDate = transactionDate;
		this.status = status;
		this.remarks = remarks;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getReferenceAccount() {
		return referenceAccount;
	}

	public void setReferenceAccount(String referenceAccount) {
		this.referenceAccount = referenceAccount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	 public BigDecimal getBalance() {
			return balance;
		}

		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}


}

