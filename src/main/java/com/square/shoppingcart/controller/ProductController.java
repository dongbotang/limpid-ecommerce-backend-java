package com.square.shoppingcart.controller;

import com.square.shoppingcart.model.entity.Product;
import com.square.shoppingcart.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Rest product controller for handling client requests on products
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@RestController
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    public ProductController() {};

    @GetMapping("/items")
    List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/items/{id}")
    public Optional<Product> getById(@PathVariable Long id) throws Exception {
        return productService.findById(id);
    }
}
