package com.example.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    
//    @NotBlank(message = "Customer name is required")
//    @Pattern(regexp = "^[A-Za-z ]+$",message = "Customer should not be created")
    @Column(name = "customer_name")
    private String customerName;
//    
//    @NotBlank(message = "Email is required")
//    @Pattern(regexp = "^[a-z0-9._%+-]+@gmail\\.com$",message = "Email must be lowercase, no spaces, and end with @gmail.com")
	private String email;
    
    @NotBlank(message = "Mobile number is required")
    @Pattern( regexp = "^[0-9]{10}$",message = "Mobile number must be 10 digits")
    private String mobile;

    @CreationTimestamp
    @Column(name = "created_date",updatable = false)	
    private LocalDateTime createdDate;
    
 
    public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long customerId, String customerName, String email, String mobile, LocalDateTime createdDate) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.mobile = mobile;
		this.createdDate = createdDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	

}
