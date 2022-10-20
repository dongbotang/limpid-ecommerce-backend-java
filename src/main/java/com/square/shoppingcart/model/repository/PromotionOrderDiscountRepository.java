package com.square.shoppingcart.model.repository;

import com.square.shoppingcart.model.entity.PromotionOrderDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionOrderDiscountRepository extends JpaRepository<PromotionOrderDiscount, Integer> {
}