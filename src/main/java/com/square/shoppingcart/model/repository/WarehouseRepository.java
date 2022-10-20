package com.square.shoppingcart.model.repository;

import com.square.shoppingcart.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}