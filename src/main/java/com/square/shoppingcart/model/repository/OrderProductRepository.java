package com.square.shoppingcart.model.repository;

import com.square.shoppingcart.model.entity.Order;
import com.square.shoppingcart.model.entity.OrderProduct;
import com.square.shoppingcart.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findByOrder(Order order);
    Optional<OrderProduct> findByOrderAndProductAndPrice(Order order, Product product, BigDecimal price);
}