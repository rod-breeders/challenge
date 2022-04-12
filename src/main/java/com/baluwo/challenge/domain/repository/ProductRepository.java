package com.baluwo.challenge.domain.repository;

import com.baluwo.challenge.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findById(Long productId);

	@Transactional
	Product save(Product product);

	@Transactional
	void deleteById(Long id);
}