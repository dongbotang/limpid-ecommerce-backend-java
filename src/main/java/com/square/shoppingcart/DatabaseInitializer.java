package com.square.shoppingcart;

import com.square.shoppingcart.model.entity.Product;
import com.square.shoppingcart.model.entity.ProductCategory;
import com.square.shoppingcart.model.entity.Promotion;
import com.square.shoppingcart.service.ProductCategoryService;
import com.square.shoppingcart.service.ProductService;
import com.square.shoppingcart.service.PromotionProductDiscountService;
import com.square.shoppingcart.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Database initializer
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Configuration
public class DatabaseInitializer {
    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private PromotionProductDiscountService promotionProductDiscountService;

    @Bean
    CommandLineRunner initializeDatabase() {
        return args -> {
            log.debug("initializeDatabase() starts");
            initProductCategory();
            initProduct();
            initPromotion();
            log.debug("initializeDatabase() ends");
        };
    }

    private void initProductCategory() throws Exception {
        log.debug("initProductCategory() starts");
        ProductCategory parent = productCategoryService.create("Electronics",
                "All Electronics products").save();
        List<String> list = new ArrayList<>();
        list.add("Smart Home");
        list.add("Laptops");
        list.add("Speakers");
        list.add("Hardware");
        for (String name : list) {
            productCategoryService.create(name, name + " products").setParent(parent).save();
        }
        log.debug("initProductCategory() Ends");
    }

    private void initProduct() throws Exception {
        log.debug("initProduct() starts");
        productService.create("Google Home","120P90", BigDecimal.valueOf(49.99),
                10, 0, "Description of Google Home",
                productCategoryService.findByName("Smart Home").get());

        productService.create("MacBook Pro","43N23P", BigDecimal.valueOf(5399.99),
                5, 0, "Description of MacBook Pro",
                productCategoryService.findByName("Laptops").get());

        productService.create("Alexa Speaker","A304SD", BigDecimal.valueOf(109.50),
                10, 0, "Description of Alexa Speaker",
                productCategoryService.findByName("Speakers").get());

        productService.create("Raspberry Pi B","43N23P", BigDecimal.valueOf(30.00),
                2, 0, "Description of Raspberry Pi B",
                productCategoryService.findByName("Hardware").get());
        log.debug("initProduct() ends");
    }

    public void initPromotion() throws Exception {
        log.debug("initPromotionTable() starts");
        // Promotion for Google Homes
        Promotion promotion = promotionService.create("Buy 3 Google Homes for the price of 2", true);
        List<Product> list = productService.findByName("Google Home");
        promotionProductDiscountService.create(promotion, list.get(0), list.get(0))
                .setTriggerQuantity(2).setDiscountQuantity(1).save();

        // Promotion for MacBook Pro
        promotion = promotionService.create("Each sale of a MacBook Pro comes with a free Raspberry Pi B", true);
        promotionProductDiscountService.create(promotion, productService.findByName("MacBook Pro").get(0),
                        productService.findByName("Raspberry Pi B").get(0))
                .setTriggerQuantity(1).setDiscountQuantity(1).save();

        // Promotion for Alexa Speaker
        promotion = promotionService.create("Buying more than 3 Alexa Speakers will have a 10% discount on all Alexa speakers", true);
        list = productService.findByName("Alexa Speaker");
        promotionProductDiscountService.create(promotion, list.get(0), list.get(0))
                .setTriggerQuantity(3).setPercentageOff(BigDecimal.valueOf(10.00)).save();
        log.debug("initPromotion() ends");
    }
}
