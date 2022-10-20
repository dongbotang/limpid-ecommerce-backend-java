package com.square.shoppingcart.model.repository;

import com.square.shoppingcart.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}