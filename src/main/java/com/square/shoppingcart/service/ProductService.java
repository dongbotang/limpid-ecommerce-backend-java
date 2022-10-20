package com.square.shoppingcart.service;

import com.square.shoppingcart.model.entity.Product;
import com.square.shoppingcart.model.entity.ProductCategory;
import com.square.shoppingcart.model.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Product service
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;

    public ProductService() {}

    public Optional<Product> findById(Long id) {
        log.debug("findById() starts & ends with id={}", id);
        return productRepository.findById(id);
    }

    public List<Product> findByName(String name) {
        log.debug("findByName() starts & ends with name={}", name);
        return productRepository.findByName(name);
    }

    public List<Product> findByCategory(ProductCategory category) {
        log.debug("findAll() starts & ends with category={}", category);
        return productRepository.findByProductCategory(category);
    }

    public List<Product> findAll() {
        log.debug("findAll() starts & ends");
        return productRepository.findAll();
    }

    public Product create(String name, String sku, BigDecimal price, Integer quantityInStock,
                          Integer quantityReserved, String description, ProductCategory productCategory) {
        log.debug("create() starts with name={}, sku={}, price={}, quantityInStock={}, quantityReserved={}, " +
                        "description={}, productCategory={}",
                name, sku, price, quantityInStock, quantityReserved, description, productCategory.toString());
        Product product = new Product();
        product.setName(name);
        product.setSku(sku);
        product.setPrice(price);
        product.setQuantityInStock(quantityInStock);
        product.setQuantityReserved(quantityReserved);
        product.setDescription(description);
        product.setProductCategory(productCategory);
        try {
            product = productRepository.save(product);
        } catch (Exception e){
            log.debug("caught exception while persist product");
            e.printStackTrace();
            return null;
        }
        log.debug("create() ends with returning Product {}", product.toString());
        return product;
    }
}
