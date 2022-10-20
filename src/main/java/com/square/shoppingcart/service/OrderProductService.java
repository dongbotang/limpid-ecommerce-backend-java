package com.square.shoppingcart.service;

import com.square.shoppingcart.model.entity.Order;
import com.square.shoppingcart.model.entity.OrderProduct;
import com.square.shoppingcart.model.entity.Product;
import com.square.shoppingcart.model.repository.OrderProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Order product service
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Service
public class OrderProductService {
    private static final Logger log = LoggerFactory.getLogger(OrderProductService.class);
    @Autowired
    private OrderProductRepository orderProductRepository;

    public Optional<OrderProduct> findByOrderAndProductAndPrice(Order order, Product product, BigDecimal price) {
        log.debug("findByOrderAndProductAndPrice() starts & ends with order={}, product={}, price={}",
                order.toString(), product.getName(), price);
        return orderProductRepository.findByOrderAndProductAndPrice(order, product, price);
    }

    public List<OrderProduct> findByOrder(Order order) {
        log.debug("findByOrder() starts & ends with order={}", order.toString());
        return orderProductRepository.findByOrder(order);
    }

    public OrderProduct updateOrCreate(Order order, Product product, BigDecimal price, int quantity, String note) {
        log.debug("updateOrCreate() starts with order={}, product={}, price={}, quantity={}, note={}",
                order.toString(), product.getName(), price, quantity, note);
        OrderProduct orderProduct;
        Optional<OrderProduct> orderProductOptional = findByOrderAndProductAndPrice(order, product, price);

        if (orderProductOptional.isPresent()) {
            orderProduct = orderProductOptional.get();
            log.debug("update orderProduct={}", orderProduct.toString());
        } else {
            orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProduct.setPrice(price);
            log.debug("create orderProduct={}", orderProduct.toString());
        }
        orderProduct.setQuantity(quantity);
        orderProduct.setNote(note);
        try {
            orderProduct = orderProductRepository.save(orderProduct);
        } catch (Exception e) {
            log.error("caught exception while persist orderProduct={}", orderProduct.toString());
            e.printStackTrace();
        }
        log.debug("updateOrCreate() ends with returning {}", orderProduct.toString());
        return orderProduct;
    }
}
