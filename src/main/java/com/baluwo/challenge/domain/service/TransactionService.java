package com.baluwo.challenge.domain.service;

import com.baluwo.challenge.domain.model.Customer;
import com.baluwo.challenge.domain.model.Product;
import com.baluwo.challenge.domain.model.Transaction;
import com.baluwo.challenge.domain.model.TransactionContentDTO;
import com.baluwo.challenge.domain.model.TransactionItem;
import com.baluwo.challenge.domain.repository.TransactionItemRepository;
import com.baluwo.challenge.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.math.BigDecimal;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Validated
public class TransactionService {

	
	private final static Logger LOGGER =  
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionItemRepository transactionItemRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerService customerService;


	@Transactional
	public Optional<Transaction> buyProduct(Long customerId, ArrayList<TransactionContentDTO> products) {

		BigDecimal orderTotal = new BigDecimal(0);
		ArrayList<TransactionItem> transactionItems = new ArrayList<TransactionItem>();
		for (TransactionContentDTO product : products) {
			Optional<Product> completeProduct = productService.findById(product.getProductId());
			if(completeProduct.isPresent()){
				//I tried to implement the builder pattern here but for some reason I could not getting working
				orderTotal = orderTotal.add(completeProduct.get().getPrice().multiply(new BigDecimal(product.getQuantity())));
				TransactionItem transactionItem = new TransactionItem();
				transactionItem.setProduct(completeProduct.get());
				transactionItem.setPrice(completeProduct.get().getPrice().multiply(new BigDecimal(product.getQuantity())));
				transactionItem.setQuantity(product.getQuantity());
				transactionItems.add(new TransactionItem());


			}

		}
		Optional<Customer> customer = customerService.findById(customerId);
		if(customer.isPresent()){
			//I tried to implement the builder pattern here but for some reason I could not getting working
			Transaction transaction = new Transaction();
			transaction.setOrderTotal(orderTotal);
			transaction.setCustomer(customer.get());
			transaction.setApproved(false);
			Optional<Transaction> result = Optional.of(transactionRepository.save(transaction));
			for(TransactionItem transactionItem: transactionItems){
				transactionItem.setTransaction(result.get());
				transactionItemRepository.save(transactionItem);
				LOGGER.log(Level.INFO, "Created the transaction Item:" + transactionItem);
			}
			return Optional.of(transactionRepository.save(transaction));
		}
		return Optional.empty();
	}


	@Transactional
	public void approveTransaction(Long transactionId){
		Optional<Transaction> transaction = transactionRepository.findById(transactionId);
		if(transaction.isPresent() && !transaction.get().isApproved()){
			Optional<Customer> customer = customerService.findById(transaction.get().getCustomer().getId());
			if(customer.isPresent()){
				BigDecimal newBalance = customer.get().getBalance().subtract(transaction.get().getOrderTotal());
				if(newBalance.compareTo(new BigDecimal(0))>-1){
					customer.get().setBalance(newBalance);
					customerService.createOrUpdateCustomer(customer.get());
					transaction.get().setApproved(true);
					transactionRepository.save(transaction.get());
					LOGGER.log(Level.INFO, "Transaction Approved for customer:" + customer.get().getId());
					LOGGER.log(Level.INFO, "New balance for customer is:" + customer.get().getBalance());
				}
			}
		}
	}
}

