package com.baluwo.challenge.domain.service;

import com.baluwo.challenge.domain.model.Customer;
import com.baluwo.challenge.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@Validated
public class CustomerService {

	
	private final static Logger LOGGER =  
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 

	@Autowired
	private CustomerRepository customerRepository;


	public Optional<Customer> findById(Long customerId){
		return customerRepository.findById(customerId);
	}

	public Customer createOrUpdateCustomer(Customer customer){
		return customerRepository.save(customer);
		}

}
