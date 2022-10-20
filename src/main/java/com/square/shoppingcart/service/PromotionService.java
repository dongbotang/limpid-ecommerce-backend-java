package com.square.shoppingcart.service;

import com.square.shoppingcart.model.entity.Promotion;
import com.square.shoppingcart.model.repository.PromotionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Promotion service
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Service
public class PromotionService {
    private static final Logger log = LoggerFactory.getLogger(PromotionService.class);
    @Autowired
    PromotionRepository promotionRepository;

    public Optional<Promotion> findById(Long id) {
        return promotionRepository.findById(id);
    }

    public List<Promotion> findByIsActive(Boolean isActive) {
        return promotionRepository.findByIsActive(isActive);
    }

    public Promotion create(String description, Boolean isActive) {
        log.debug("create() starts with description={}, isActive={}", description, isActive);
        Promotion promotion = new Promotion();
        promotion.setDescription(description);
        promotion.setIsActive(isActive);
        try {
            promotion = promotionRepository.save(promotion);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        log.debug("create() ends with returning Promotion {}", promotion.toString());
        return promotion;
    }
}
