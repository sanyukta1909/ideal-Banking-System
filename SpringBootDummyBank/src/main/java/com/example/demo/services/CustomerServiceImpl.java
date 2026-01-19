package com.example.demo.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger log =
 	        LoggerFactory.getLogger(CustomerServiceImpl.class);

	 	@Autowired
	    private CustomerRepository custRepo;

	 	@Override
	 	public Customer save(Customer customer) {

	 	    log.info("Creating customer with email: {}", customer.getEmail());

	 	    return custRepo.save(customer);
	 	}
}

