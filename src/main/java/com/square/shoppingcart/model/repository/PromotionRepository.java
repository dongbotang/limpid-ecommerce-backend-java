package com.square.shoppingcart.model.repository;

import com.square.shoppingcart.model.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByIsActive(boolean isActive);
}