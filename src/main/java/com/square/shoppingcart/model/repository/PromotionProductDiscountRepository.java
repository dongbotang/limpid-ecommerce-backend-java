package com.square.shoppingcart.model.repository;

import com.square.shoppingcart.model.entity.Product;
import com.square.shoppingcart.model.entity.Promotion;
import com.square.shoppingcart.model.entity.PromotionProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromotionProductDiscountRepository extends JpaRepository<PromotionProductDiscount, Long> {
    Optional<PromotionProductDiscount> findByPromotionAndTriggerProduct(Promotion promotion, Product triggerProduct);
}