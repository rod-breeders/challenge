package com.baluwo.challenge.app.rest;

import com.baluwo.challenge.domain.model.Product;
import com.baluwo.challenge.domain.model.TransactionContentDTO;
import com.baluwo.challenge.domain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @RequestMapping(value = "/{customerId}/buy-products", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Product> buyProduct(@PathVariable("customerId")Long customerId, @RequestBody ArrayList<TransactionContentDTO> products) {
        transactionService.buyProduct(customerId, products);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/approve/{transactionId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> approveTransaction(@PathVariable("transactionId")Long transactionId) {
        transactionService.approveTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
