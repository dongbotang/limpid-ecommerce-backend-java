package com.square.shoppingcart.service;

import com.square.shoppingcart.model.entity.Order;
import com.square.shoppingcart.model.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Order service
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final String ORDER_STATUS_DRAFT = "draft";
    @Autowired
    private OrderRepository orderRepository;

    public Optional<Order> findById(Long id) {
        log.debug("findById() starts & ends with id={}", id);
        return orderRepository.findById(id);
    }

    public Order findOrCreate(long orderId) {
        log.debug("findOrCreate() starts with orderId={}", orderId);
        Order order;
        Optional<Order> orderOptional = findById(orderId);
        if (orderOptional.isPresent()) {
            order = orderOptional.get();
        } else {
            order = new Order();
            order.setStatus(ORDER_STATUS_DRAFT);
            try {
                order = orderRepository.save(order);
            } catch (Exception e) {
                log.error("caught exception when persist order");
                e.printStackTrace();
                return null;
            }
        }
        log.debug("findOrCreate() ends with returning {}", order.toString());
        return order;
    }
}
