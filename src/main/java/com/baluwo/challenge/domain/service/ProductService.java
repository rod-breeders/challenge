package com.baluwo.challenge.domain.service;

import com.baluwo.challenge.domain.model.Product;
import com.baluwo.challenge.domain.model.ProductDto;
import com.baluwo.challenge.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Validated
public class ProductService {

	
	private final static Logger LOGGER =  
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 

	@Autowired
	private ProductRepository productRepository;


	public Product createProduct(@Valid Product product) {

		try{
			return productRepository.save(product);
		} catch(Exception e) {
			LOGGER.log(Level.INFO, "Exception: " + e.getMessage());
			throw e;
		}
	}

	public Optional<Product> findById(Long productId){
		return productRepository.findById(productId);
	}

	@Transactional
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	@Transactional
	public Optional<Product> update(@Valid ProductDto product) {
		Optional<Product> oldProduct = productRepository.findById(product.getId());


		if(oldProduct.isPresent()){
			//this is not the best solution, but I was having issues with the mapstruct and gave up on using it.
			if(product.getPrice() != null){oldProduct.get().setPrice(product.getPrice());}
			if(product.getDescription() != null){oldProduct.get().setDescription(product.getDescription());}
			if(product.getName() != null){oldProduct.get().setName(product.getName());}
			return Optional.of(productRepository.save(oldProduct.get()));
		} else {
			LOGGER.log(Level.INFO, "Product Doesn't exist: " + product.getId());
			return Optional.empty();
		}
	}
}
