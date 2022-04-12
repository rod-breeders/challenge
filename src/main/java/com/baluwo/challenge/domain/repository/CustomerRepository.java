package com.baluwo.challenge.domain.repository;

import com.baluwo.challenge.domain.model.Customer;
import com.baluwo.challenge.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findById(Long customerId);

	@Transactional
	Customer save(Customer product);

	@Transactional
	void deleteById(Long id);
}