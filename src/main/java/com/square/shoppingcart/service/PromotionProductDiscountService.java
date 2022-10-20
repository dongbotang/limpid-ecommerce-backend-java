package com.square.shoppingcart.service;

import com.square.shoppingcart.model.entity.Product;
import com.square.shoppingcart.model.entity.Promotion;

import com.square.shoppingcart.model.entity.PromotionProductDiscount;
import com.square.shoppingcart.model.repository.PromotionProductDiscountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Promotion Product discount service
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Service
public class PromotionProductDiscountService {
    private static final Logger log = LoggerFactory.getLogger(PromotionProductDiscountService.class);
    @Autowired
    private PromotionProductDiscountRepository promotionProductDiscountRepository;
    private PromotionProductDiscount promotionProductDiscount;

    public PromotionProductDiscountService() {}

    public Optional<PromotionProductDiscount> findByPromotionAndProduct(Promotion promotion, Product product) {
        log.debug("findByPromotionAndProduct() starts & ends with promotion={}, product={}",
                promotion.toString(), product.getName());
        return promotionProductDiscountRepository.findByPromotionAndTriggerProduct(promotion, product);
    }

    public PromotionProductDiscountService create(Promotion promotion, Product triggerProduct,
                                                   Product discountProduct) {
        log.debug("create() starts with promotion={}, triggerProduct={}, discountProduct={}",
                promotion.toString(), triggerProduct.getName(), discountProduct.getName());
        promotionProductDiscount = new PromotionProductDiscount();
        promotionProductDiscount.setPromotion(promotion);
        promotionProductDiscount.setTriggerProduct(triggerProduct);
        promotionProductDiscount.setDiscountProduct(discountProduct);
        log.debug("create() ends");
        return this;
    }

    public PromotionProductDiscountService setTriggerQuantity(int quantity) {
        log.debug("setTriggerQuantity() starts with quantity={}", quantity);
        promotionProductDiscount.setTriggerQuantity(quantity);
        log.debug("setTriggerQuantity() ends");
        return this;
    }

    public PromotionProductDiscountService setDiscountQuantity(int quantity) {
        log.debug("setDiscountQuantity() starts with quantity={}", quantity);
        promotionProductDiscount.setDiscountQuantity(quantity);
        log.debug("setDiscountQuantity() ends");
        return this;
    }

    public PromotionProductDiscountService setPercentageOff(BigDecimal percentageOff) {
        log.debug("setPercentageOff() starts with percentageOff={}", percentageOff);
        promotionProductDiscount.setPercentageOff(percentageOff);
        log.debug("setPercentageOff() ends");
        return this;
    }

    public PromotionProductDiscount save() {
        log.debug("save() starts");
        try {
            promotionProductDiscount = promotionProductDiscountRepository.save(promotionProductDiscount);
        } catch (Exception e){
            log.debug("caught exception while persist promotionProductDiscount");
            e.printStackTrace();
            return null;
        }
        log.debug("save() ends with returning {}", promotionProductDiscount);
        return promotionProductDiscount;
    }

    protected BigDecimal calculatePrice(BigDecimal productPrice, BigDecimal percentage, BigDecimal percentageOff) {
        log.debug("calculatePrice() starts with productPrice={}, percentage={}, percentageOff={}",
                productPrice, percentage, percentageOff);
        BigDecimal value = productPrice.multiply(percentage.subtract(percentageOff).divide(BigDecimal.valueOf(100)));
        log.debug("calculatePrice() ends with returning {}", value);
        return value;
    }

    protected int calculateQuantity(int orderQuantity, int triggerQuantity, int discountQuantity) {
        log.debug("calculateQuantity() starts with orderQuantity={}, triggerQuantity={}, discountQuantity={}",
                orderQuantity, triggerQuantity, discountQuantity);
        int quantity = discountQuantity * (int) Math.floor((double) orderQuantity / triggerQuantity);
        log.debug("calculateQuantity() ends with returning {}", quantity);
        return quantity;
    }
}
