package com.square.shoppingcart.service;

import com.square.shoppingcart.model.entity.ProductCategory;
import com.square.shoppingcart.model.repository.ProductCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Product category service
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Service
public class ProductCategoryService {
    private static final Logger log = LoggerFactory.getLogger(ProductCategoryService.class);
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    private ProductCategory productCategory;

    public ProductCategoryService() {}

    public Optional<ProductCategory> findById(Integer id) {
        log.debug("findById() starts & ends with id={}", id);
        return productCategoryRepository.findById(id);
    }

    public Optional<ProductCategory> findByName(String name) {
        log.debug("findByName() starts & ends with name={}", name);
        return productCategoryRepository.findByName(name);
    }

    public ProductCategoryService create(String name, String description) {
        log.debug("create() starts with name={}, description={}", name, description);
        productCategory = new ProductCategory();
        productCategory.setName(name);
        productCategory.setDescription(description);
        productCategory = productCategoryRepository.save(productCategory);
        try {
            productCategory = productCategoryRepository.save(productCategory);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        log.debug("create() ends with returning ProductCategory {}", productCategory.toString());
        return this;
    }

    public ProductCategoryService setParent(ProductCategory parent) {
        log.debug("setParent() starts with parent={}", parent.toString());
        productCategory.setParent(parent);
        return this;
    }

    public ProductCategory save() {
        log.debug("create() starts");
        try {
            productCategory = productCategoryRepository.save(productCategory);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        log.debug("create() ends with returning {}", productCategory.toString());
        return productCategory;
    }
}
