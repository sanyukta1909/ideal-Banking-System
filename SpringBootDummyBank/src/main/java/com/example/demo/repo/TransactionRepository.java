package com.example.demo.repo;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	    List<Transaction> findByAccountId(Long accountId); 
	    List<Transaction> findByAccountIdOrderByTransactionDateDesc(Long accountId);

}

