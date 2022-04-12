package com.baluwo.challenge.app.rest;

import com.baluwo.challenge.domain.model.Customer;
import com.baluwo.challenge.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @RequestMapping(value = "/create-customer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        try{
        Customer createdCustomer = customerService.createOrUpdateCustomer(customer);
            LOGGER.log(Level.INFO, "Created Customer: " + customer);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);}
         catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}