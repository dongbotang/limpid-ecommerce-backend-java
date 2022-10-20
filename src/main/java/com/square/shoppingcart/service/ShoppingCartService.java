package com.square.shoppingcart.service;

import com.square.shoppingcart.model.dto.ShoppingCartItem;
import com.square.shoppingcart.model.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Shopping cart service for all items in shopping cart
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Service
public class ShoppingCartService {
    private static final Logger log = LoggerFactory.getLogger(ShoppingCartService.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private PromotionProductDiscountService promotionProductDiscountService;

    public ShoppingCartService() {}

    public List<OrderProduct> listItems(Long orderId) {
        log.debug("findByOrderId() starts with orderId={}", orderId);
        List<OrderProduct> list = new ArrayList<>();
        Optional<Order> orderOptional = orderService.findById(orderId);
        if (orderOptional.isPresent()) {
            log.debug("order={}", orderOptional.get().toString());
            list = orderProductService.findByOrder(orderOptional.get());
        }
        log.debug("findByOrderId() ends with returning {}", list.toString());
        return list;
    }

    /**
     * Add an item to a shopping cart.
     * If shopping cart doesn't exist, create one and add item to it
     * @param item
     * @return
     */
    public Order addItem(ShoppingCartItem item) {
        log.debug("addItem() starts with item={}", item.toString());
        // Order
        Order order = orderService.findOrCreate(item.getOrderId());
        Optional<Product> productOptional = productService.findById(item.getProductId());
        if (productOptional.isPresent()) {
            // OrderProduct
            OrderProduct orderProduct = orderProductService.updateOrCreate(order, productOptional.get(),
                    productOptional.get().getPrice(), item.getQuantity(), item.getNote());
            // PromotionOrderProduct
            createPromotionOrderProduct(order, orderProduct);
        } else {
            log.error("Product with id={} not found", item.getProductId());
        }
        log.debug("addItem() ends with returning {}", order.toString());
        return order;
    }

    private void createPromotionOrderProduct(Order order, OrderProduct orderProduct) {
        log.debug("createPromotionOrderProduct() starts with order={}, orderProduct={}",
                order.toString(), orderProduct.toString());
        // promotion OrderProduct
        List<Promotion> promotionList = promotionService.findByIsActive(true);
        Promotion promotion;
        PromotionProductDiscount promotionProductDiscount;
        OrderProduct promotionOrderProduct;
        Optional<PromotionProductDiscount> promotionProductDiscountOptional;
        for (int i=0; i<promotionList.size(); i++) {
            promotion = promotionList.get(i);
            log.debug("promotion={}", promotion.toString());
            promotionProductDiscountOptional = promotionProductDiscountService.findByPromotionAndProduct(promotion,
                    orderProduct.getProduct());
            if (promotionProductDiscountOptional.isPresent()) {
                promotionProductDiscount = promotionProductDiscountOptional.get();
                log.debug("promotionProductDiscount={}", promotion.toString());
                if (orderProduct.getQuantity() >= promotionProductDiscount.getTriggerQuantity()) {
                    if (promotionProductDiscount.getDiscountQuantity() == -1) {
                        log.debug("discount on full quantity of the product - create an orderProduct with " +
                                "(a) price=-price*percentageOff%  (b) quantity=productQuantity");
                        promotionOrderProduct = orderProductService.updateOrCreate(order, orderProduct.getProduct(),
                                promotionProductDiscountService.calculatePrice(orderProduct.getProduct().getPrice(),
                                        BigDecimal.valueOf(0.00), promotionProductDiscount.getPercentageOff()),
                                orderProduct.getQuantity(), promotion.getDescription());
                        log.debug("promotionOrderProduct={}", promotionOrderProduct.toString());

                    } else {
                        // Create the discount product and add to the order
                        log.debug("discount on the specified product - create an orderProduct with " +
                                "(a) price=(price*100-percentageOff)% " +
                                "(b) quantity=discountQuantity*Math.floor(productQuantity/triggerQuantity)");
                        promotionOrderProduct = orderProductService.updateOrCreate(order,
                                promotionProductDiscount.getDiscountProduct(),
                                promotionProductDiscountService.calculatePrice(
                                        promotionProductDiscount.getDiscountProduct().getPrice(),
                                        BigDecimal.valueOf(100.00), promotionProductDiscount.getPercentageOff()),
                                promotionProductDiscountService.calculateQuantity(orderProduct.getQuantity(),
                                        promotionProductDiscount.getTriggerQuantity(),
                                        promotionProductDiscount.getDiscountQuantity()),
                                promotion.getDescription());
                        log.debug("promotionOrderProduct={}", promotionOrderProduct.toString());
                    }
                }
            }
        }
        log.debug("createPromotionOrderProduct() ends");
    }
}