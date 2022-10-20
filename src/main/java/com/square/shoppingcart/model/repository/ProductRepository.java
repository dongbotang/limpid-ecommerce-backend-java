package com.square.shoppingcart.model.repository;

import com.square.shoppingcart.model.entity.Product;
import com.square.shoppingcart.model.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByProductCategory(ProductCategory productCategory);
}
