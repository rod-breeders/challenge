package com.baluwo.challenge.app.rest;

import com.baluwo.challenge.domain.model.Product;
import com.baluwo.challenge.domain.model.ProductDto;
import com.baluwo.challenge.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping(value = "/products")
public class ProductController  {

    @Autowired
    private ProductService productService;

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @RequestMapping(value = "/create-product", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Product> create(@RequestBody Product product) {
        try{
        Product createdProduct = productService.createProduct(product);
            LOGGER.log(Level.INFO, "Created Product: " + product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);}
         catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/find-product/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Product> findById(@PathVariable("productId")Long productId) {
        Optional<Product> product = productService.findById(productId);
        return product.map(value -> {
            LOGGER.log(Level.INFO, "Found Product: " + product);
            return new ResponseEntity<>(value, HttpStatus.OK);
        })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(value = "/delete-product/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        LOGGER.log(Level.INFO, "Deleted Product: " + productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/product-user/{productId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Product> update(@RequestBody ProductDto product, @PathVariable("productId") Long productId) {
        product.setId(productId);
        Optional<Product> updatedProduct = productService.update(product);
        return updatedProduct.map(value -> {
            LOGGER.log(Level.INFO, "Product Updated: " + productId);
           return new ResponseEntity<>(value, HttpStatus.OK);
        })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}