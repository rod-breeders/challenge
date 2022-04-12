package com.baluwo.challenge.domain.repository;

import com.baluwo.challenge.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Transactional
	Transaction save(Transaction transaction);
}