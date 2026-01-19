package com.example.demo.services;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.exceptions.InsufficientBalanceException;
import com.example.demo.exceptions.InvalidRequestException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.repo.AccountRepository;
import com.example.demo.repo.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransactionRepository transactionRepo;
    

    private static final Logger log =
            LoggerFactory.getLogger(TransactionServiceImpl.class);

    // Deposit transaction
    @Override
    @Transactional(dontRollbackOn = {
            InvalidRequestException.class
    })
    public String deposit(String accountNumber, BigDecimal amount) {
    	
    	log.info("Deposit initiated for accountNumber={}, amount={}",
                accountNumber, amount);

        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));

        BigDecimal currentBalance =
                account.getBalance() == null ? BigDecimal.ZERO : account.getBalance();

        // Account INACTIVE
        if (!"ACTIVE".equalsIgnoreCase(account.getStatus())) {

            saveFailedDepositTx(
                    account.getAccountId(),
                    amount,
                    currentBalance,
                    "Account is not ACTIVE"
            );

            throw new InvalidRequestException(
                    "Account is not ACTIVE transaction should not be allowed"
            );
        }

        // Invalid amount 
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {

            saveFailedDepositTx(
                    account.getAccountId(),
                    amount,
                    currentBalance,
                    "Amount must be greater than zero"
            );

            throw new InvalidRequestException("Amount must be greater than zero");
        }

     
        BigDecimal newBalance = currentBalance.add(amount);
        account.setBalance(newBalance);
        accountRepo.save(account);

        Transaction tx = new Transaction();
        tx.setAccountId(account.getAccountId());
        tx.setTransactionType("DEPOSIT");
        tx.setAmount(amount);
        tx.setBalance(newBalance);
        tx.setTransactionDate(LocalDateTime.now());
        tx.setStatus("SUCCESS");
        tx.setRemarks("Deposit successful");

        transactionRepo.save(tx);
        log.info("Deposit successful for accountNumber={}", accountNumber);

        return "Deposit Successful. New Balance = " + newBalance;
    }
		    
		    private void saveFailedDepositTx(Long accountId,
		            BigDecimal amount,
		            BigDecimal balance,
		            String remarks) {
		
		Transaction tx = new Transaction();
		tx.setAccountId(accountId);
		tx.setTransactionType("DEPOSIT");
		tx.setAmount(amount);
		tx.setBalance(balance);
		tx.setStatus("FAILED");
		tx.setTransactionDate(LocalDateTime.now());
		tx.setRemarks(remarks);
		
		transactionRepo.save(tx);
		}



		    //  withdraw transaction
    @Override
    @Transactional(dontRollbackOn = {
            InvalidRequestException.class,
            InsufficientBalanceException.class
    })
    public String withdraw(String accountNumber, BigDecimal amount) {
    
    	
        log.info("Withdraw initiated: accountNumber={}, amount={}",
                accountNumber, amount);

        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() ->{
                	log.error("Withdraw failed: account {} not found", accountNumber);
                       return  new ResourceNotFoundException("Account not found");
                        });

        
        if (!"ACTIVE".equalsIgnoreCase(account.getStatus())) {
        	 log.error("Withdraw failed: invalid amount {} for account {}",
                     amount, accountNumber);
            throw new InvalidRequestException("Account is not ACTIVE");
        }

        
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidRequestException("Amount must be greater than zero");
        }

        BigDecimal currentBalance =
                account.getBalance() == null ? BigDecimal.ZERO : account.getBalance();
        if (!"ACTIVE".equalsIgnoreCase(account.getStatus())) {

            saveFailedWithdrawTx(
                    account.getAccountId(),
                    amount,
                    currentBalance,
                    "Account is not ACTIVE"
            );

            throw new InvalidRequestException("Account is not ACTIVE");
        }


        if (currentBalance.compareTo(amount) < 0) {
        	
        	log.error("Withdraw failed: insufficient balance. account={}, balance={}, requested={}",
                    accountNumber, currentBalance, amount);
            saveFailedWithdrawTx(
                    account.getAccountId(),
                    amount,
                    currentBalance,
                    "Insufficient balance"
            );

            throw new InsufficientBalanceException("Insufficient balance");
        }

    
        if (currentBalance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

    
        BigDecimal newBalance = currentBalance.subtract(amount);
        account.setBalance(newBalance);
        accountRepo.save(account);

 
        Transaction tx = new Transaction();
        tx.setAccountId(account.getAccountId());
        tx.setTransactionType("WITHDRAW");
        tx.setAmount(amount);
        tx.setBalance(newBalance);  
        tx.setTransactionDate(LocalDateTime.now());
        tx.setStatus("SUCCESS");
        tx.setRemarks("Withdrawal successful");

        transactionRepo.save(tx);
        
        log.info("Withdraw successful: accountNumber={}, withdrawnAmount={}, remainingBalance={}",
                accountNumber, amount, newBalance);
      

        return "Withdrawal Successful. Remaining Balance = " + newBalance;
    }
    
			    private void saveFailedWithdrawTx(Long accountId,
			            BigDecimal amount,
			            BigDecimal balance,
			            String remarks) {
			
			Transaction tx = new Transaction();
			tx.setAccountId(accountId);	
			tx.setTransactionType("WITHDRAW");
			tx.setAmount(amount);
			tx.setBalance(balance);
			tx.setStatus("FAILED");
			tx.setTransactionDate(LocalDateTime.now());
			tx.setRemarks(remarks);
			
			transactionRepo.save(tx);
			}

    
    
			    //    Transfer transaction
    
    @Override
    @Transactional(
        rollbackOn = Exception.class,
        dontRollbackOn = {
            InvalidRequestException.class,
            InsufficientBalanceException.class
        }
    )
    public String transfer(String fromAccNo, String toAccNo, BigDecimal amount) {
    	
    	   log.info("Transfer initiated: fromAcc={}, toAcc={}, amount={}",
    	            fromAccNo, toAccNo, amount);

        Account fromAcc = accountRepo.findByAccountNumber(fromAccNo)
                .orElseThrow(() ->{
                	 log.error("Transfer failed: source account {} not found", fromAccNo);
                        return new ResourceNotFoundException("Source account not found");
                        });

        Account toAcc = accountRepo.findByAccountNumber(toAccNo)
                .orElseThrow(() ->{
                	 log.error("Transfer failed: destination account {} not found", toAccNo);
                        return new ResourceNotFoundException("Destination account not found");
                } );

  
        if (!"ACTIVE".equalsIgnoreCase(fromAcc.getStatus()) ||
            !"ACTIVE".equalsIgnoreCase(toAcc.getStatus())) {
        	
        	  log.error("Transfer failed: one or both accounts inactive. fromAccStatus={}, toAccStatus={}",
                      fromAcc.getStatus(), toAcc.getStatus());

            saveFailedTx(fromAcc.getAccountId(),
                "TRANSFER",
                amount,
                fromAcc.getBalance(),
                toAccNo,
                "One or both accounts inactive"
            );

            throw new InvalidRequestException("Both accounts must be ACTIVE");
        }

        BigDecimal fromBalance =
                fromAcc.getBalance() == null ? BigDecimal.ZERO : fromAcc.getBalance();
        
        if (fromBalance.compareTo(amount) < 0) {
        	
        	  log.error("Transfer failed: insufficient balance. fromAcc={}, balance={}, requested={}",
                      fromAccNo, fromBalance, amount);

            Transaction failedTx = new Transaction();
            failedTx.setAccountId(fromAcc.getAccountId());
            failedTx.setTransactionType("TRANSFER");
            failedTx.setAmount(amount);
            failedTx.setBalance(fromBalance);
            failedTx.setReferenceAccount(toAccNo);
            failedTx.setStatus("FAILED");
            failedTx.setTransactionDate(LocalDateTime.now());
            failedTx.setRemarks("Insufficient balance");

            transactionRepo.save(failedTx);

            throw new InsufficientBalanceException("Insufficient balance");
        }

        // Debit source
        BigDecimal fromNewBalance = fromBalance.subtract(amount);
        fromAcc.setBalance(fromNewBalance);
        accountRepo.save(fromAcc);

        // Credit destination
        BigDecimal toNewBalance = toAcc.getBalance().add(amount);
        toAcc.setBalance(toNewBalance);
        accountRepo.save(toAcc);

      // Debit transaction
      Transaction debitTx = new Transaction();
      debitTx.setAccountId(fromAcc.getAccountId());
      debitTx.setTransactionType("TRANSFER");
      debitTx.setAmount(amount);
      debitTx.setBalance(fromNewBalance);  
      debitTx.setReferenceAccount(toAccNo);
      debitTx.setStatus("SUCCESS");
      debitTx.setTransactionDate(LocalDateTime.now());
      debitTx.setRemarks("Amount debited");
      transactionRepo.save(debitTx);

      // Credit transaction
      Transaction creditTx = new Transaction();
      creditTx.setAccountId(toAcc.getAccountId());
      creditTx.setTransactionType("TRANSFER");
      creditTx.setAmount(amount);
      creditTx.setBalance(toNewBalance);   
      creditTx.setReferenceAccount(fromAccNo);
      creditTx.setStatus("SUCCESS");
      creditTx.setTransactionDate(LocalDateTime.now());
      creditTx.setRemarks("Amount credited");
      transactionRepo.save(creditTx);
      
      log.info("Transfer successful: fromAcc={}, toAcc={}, amount={}, fromBalance={}, toBalance={}",
              fromAccNo, toAccNo, amount, fromNewBalance, toNewBalance);

      return "Transfer Successful. From Balance = " + fromNewBalance +
             ", To Balance = " + toNewBalance;

     
    }

    private void saveFailedTx(Long accountId, String string, BigDecimal amount, BigDecimal balance, String toAccNo,
			String string2) {

        Transaction tx = new Transaction();
        tx.setAccountId(accountId);
        tx.setTransactionType("TRANSFER");
        tx.setAmount(amount);
        tx.setBalance(balance);
        tx.setReferenceAccount(toAccNo);
        tx.setStatus("FAILED");
        tx.setTransactionDate(LocalDateTime.now());
        tx.setRemarks("Insufficient balance");
        
        

        transactionRepo.save(tx);
		
	}

	//    transaction history
    @Override
    public List<Transaction> getTransactionHistory(String accountNumber) {

    
        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));

      
        return transactionRepo.findByAccountIdOrderByTransactionDateDesc(
                account.getAccountId()
        );
    }


}