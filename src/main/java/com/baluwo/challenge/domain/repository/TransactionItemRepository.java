package com.baluwo.challenge.domain.repository;

import com.baluwo.challenge.domain.model.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {
    @Transactional
    TransactionItem save(TransactionItem transactionItem);
}